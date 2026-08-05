package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object de la pantalla de transferencias de BuggyBank.
 */
public class TransferPage extends BasePage {

    private static final String DEFAULT_BASE_URL = "https://buggybank-seven.vercel.app";

    private static final By ORIGIN_ACCOUNT =
            By.cssSelector("[data-testid='transfer-from']");
    private static final By DESTINATION_ACCOUNT =
            By.cssSelector("[data-testid='transfer-to']");
    private static final By AMOUNT =
            By.cssSelector("[data-testid='transfer-amount']");
    private static final By NOTE =
            By.cssSelector("[data-testid='transfer-note']");
    private static final By SUBMIT =
            By.cssSelector("[data-testid='transfer-submit']");
    private static final By DESTINATION_ERROR = By.xpath(
            "//*[@data-testid='transfer-to']/parent::label/following-sibling::p"
    );

    public TransferPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        navigateTo(baseUrl + "/transfers");
    }

    public void selectOriginAccountByIndex(int index) {
        selectByIndex(ORIGIN_ACCOUNT, index);
    }

    public void selectDestinationAccountByIndex(int index) {
        selectByIndex(DESTINATION_ACCOUNT, index);
    }

    /**
     * El índice es intencional: el objetivo es elegir la misma opción en ambos controles.
     */
    public void chooseSameAccountInBothDropdowns() {
        selectByIndex(ORIGIN_ACCOUNT, 0);
        selectByIndex(DESTINATION_ACCOUNT, 0);
    }

    /**
     * Variante menos acoplada al orden de las opciones.
     */
    public void chooseSameAccountInBothDropdownsByValue(String value) {
        String accountId = new Select(find(ORIGIN_ACCOUNT))
                .getFirstSelectedOption()
                .getAttribute(value);

        selectByValue(DESTINATION_ACCOUNT, accountId);
    }

    public void completeTransfer(String amount, String note) {
        type(AMOUNT, amount);
        type(NOTE, note);
    }

    public void submit() {
        click(SUBMIT);
    }

    public String destinationError() {
        return getText(DESTINATION_ERROR);
    }

    public boolean isOriginAccountSelected(int index) {
        return isOptionSelected(ORIGIN_ACCOUNT, index);
    }

    public boolean isDestinationAccountSelected(int index) {
        return isOptionSelected(DESTINATION_ACCOUNT, index);
    }

    private boolean isOptionSelected(By locator, int index) {
        Select dropdown = new Select(find(locator));
        return dropdown.getOptions().get(index).isSelected();
    }
}
