package steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.util.List;
import java.util.Map;

import components.NavigationComponent;
import driver.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import models.NavigationExpectation;

public class NavigationSteps {

    private static final String DEFAULT_BASE_URL = "https://buggybank-seven.vercel.app";

    private final List<NavigationExpectation> expectations = List.of(
            new NavigationExpectation(
                    "Dashboard", "/dashboard", "Saldo total disponible"
            ),
            new NavigationExpectation(
                    "Transferencias", "/transfers", "Nueva transferencia"
            ),
            new NavigationExpectation(
                    "Movimientos", "/transactions", "Historial de movimientos"
            ),
            new NavigationExpectation(
                    "Desafíos", "/challenges", "Laboratorio de Desafíos"
            ),
            new NavigationExpectation(
                    "Perfil", "/profile", "Perfil y preferencias"
            )
    );

    private NavigationComponent navigation;
    private Map<String, String> links;

    @Cuando("recorre las secciones principales")
    public void collectMainNavigation() {
        navigation = new NavigationComponent(DriverManager.getDriver());
        links = navigation.linkUrlsByLabel();
    }

    @Entonces("cada sección muestra su URL y contenido esperado")
    public void verifyMainSections() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);

        for (NavigationExpectation expected : expectations) {
            String url = links.get(expected.label());

            assertNotNull(url, "Falta el link " + expected.label());
            assertEquals(
                    URI.create(url).getPath(),
                    expected.path(),
                    "Ruta incorrecta para " + expected.label()
            );

            navigation.visit(baseUrl + expected.path());

            assertTrue(
                    navigation.pageContains(expected.expectedText()),
                    "No apareció el contenido de " + expected.label()
            );
        }
    }
}
