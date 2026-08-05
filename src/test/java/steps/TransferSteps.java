package steps;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import driver.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.TransferPage;

public class TransferSteps {

    private static final int FIRST_OPTION = 0;
    private static final int SECOND_OPTION = 1;

    private TransferPage transferPage;

    @Dado("estoy en la página de transferencias")
    public void openTransfersPage() {
        transferPage = new TransferPage(DriverManager.getDriver());
        transferPage.open();
    }

    @Cuando("selecciono la segunda opción del dropdown de cuenta origen")
    public void selectSecondOriginAccount() {
        transferPage.selectOriginAccountByIndex(SECOND_OPTION);
    }

    @Cuando("selecciono la primera opción del dropdown de cuenta destino")
    public void selectFirstDestinationAccount() {
        transferPage.selectDestinationAccountByIndex(FIRST_OPTION);
    }

    @Cuando("elijo la misma cuenta como origen y destino")
    public void chooseSameAccountInBothDropdowns() {
        transferPage.chooseSameAccountInBothDropdowns();
    }

    @Cuando("completo la transferencia con el monto {string} y la descripción {string}")
    public void completeTransfer(String amount, String note) {
        transferPage.completeTransfer(amount, note);
    }

    @Cuando("intento enviar la transferencia")
    public void submitTransfer() {
        transferPage.submit();
    }

    @Entonces("la segunda opción queda seleccionada como cuenta origen")
    public void verifySecondOriginAccount() {
        assertTrue(
                transferPage.isOriginAccountSelected(SECOND_OPTION),
                "La segunda opción no quedó seleccionada como cuenta origen."
        );
    }

    @Entonces("la primera opción queda seleccionada como cuenta destino")
    public void verifyFirstDestinationAccount() {
        assertTrue(
                transferPage.isDestinationAccountSelected(FIRST_OPTION),
                "La primera opción no quedó seleccionada como cuenta destino."
        );
    }

    @Entonces("veo el error de cuenta destino {string}")
    public void verifyDestinationError(String expectedMessage) {
        assertEquals(
                transferPage.destinationError(),
                expectedMessage,
                "BuggyBank no mostró la validación esperada"
        );
    }
}