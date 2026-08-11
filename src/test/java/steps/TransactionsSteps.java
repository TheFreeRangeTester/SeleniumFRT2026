package steps;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.List;

import driver.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.TransactionsPage;

public class TransactionsSteps {

    private TransactionsPage transactionsPage;
    private List<String> visibleDirections;
    private List<String> visibleDescriptions;

    @Dado("está en la página de movimientos")
    public void openTransactionsPage() {
        transactionsPage = new TransactionsPage(DriverManager.getDriver());
        transactionsPage.open();
    }

    @Cuando("filtra los movimientos por {string}")
    public void filterTransactionsByDirection(String direction) {
        transactionsPage.filterByDirection(direction);
    }

    @Cuando("busca el texto {string}")
    public void searchTransactions(String text) {
        transactionsPage.search(text);
    }

    @Entonces("ve al menos un resultado")
    public void verifyAtLeastOneResult() {
        assertTrue(
                transactionsPage.visibleRowCount() > 0,
                "El filtro no devolvió movimientos"
        );

        visibleDirections = transactionsPage.visibleDirections();
        visibleDescriptions = transactionsPage.visibleDescriptions();
        assertFalse(visibleDirections.isEmpty(), "El filtro no devolvió movimientos");
        assertFalse(visibleDescriptions.isEmpty(), "No se encontraron descripciones visibles");
    }

    @Entonces("todos los resultados visibles son salidas")
    public void verifyAllVisibleResultsAreOutgoing() {
        assertTrue(
                visibleDirections.stream().allMatch("Salida"::equals),
                "Apareció un movimiento que no es saliente"
        );
    }

    @Entonces("todas las descripciones visibles contienen {string}")
    public void verifyAllVisibleDescriptionsContain(String text) {
        assertTrue(
                visibleDescriptions.stream().allMatch(description -> description.contains(text)),
                "Apareció una descripción que no coincide con la búsqueda"
        );
    }
}
