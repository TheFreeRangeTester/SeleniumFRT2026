package steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.URI;
import java.util.List;

import driver.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.PaymentsPage;

public class PaymentsSteps {

    private PaymentsPage paymentsPage;
    private List<String> visibleStatuses;

    @Dado("estoy en la página de pagos")
    public void openPaymentsPage() {
        paymentsPage = new PaymentsPage(DriverManager.getDriver());
        paymentsPage.open();
    }

    @Cuando("filtro los pagos por estado {string}")
    public void filterPaymentsByStatus(String status) {
        paymentsPage.filterByStatus(status);
        visibleStatuses = paymentsPage.visibleStatuses();
    }

    @Entonces("el filtro de pagos ofrece los estados {string}")
    public void verifyPaymentStatusOptions(String expectedOptions) {
        assertEquals(
                paymentsPage.statusFilterOptions(),
                List.of(expectedOptions.split(", ")),
                "El filtro de pagos no ofrece los estados esperados"
        );
    }

    @Entonces("veo pagos y todos tienen el estado {string}")
    public void verifyVisiblePaymentsHaveStatus(String expectedStatus) {
        assertFalse(visibleStatuses.isEmpty(), "El filtro no devolvió pagos");
        assertTrue(
                visibleStatuses.stream().allMatch(expectedStatus::equals),
                "Apareció un pago con un estado distinto de " + expectedStatus
        );
        assertTrue(
                paymentsPage.visiblePaymentCount() > 0,
                "El filtro no devolvió filas de pagos"
        );
    }

    @Entonces("el enlace para crear un pago apunta a {string}")
    public void verifyNewPaymentLink(String expectedPath) {
        assertEquals(
                URI.create(paymentsPage.newPaymentUrl()).getPath(),
                expectedPath,
                "La ruta de Nuevo pago es incorrecta"
        );
    }
}