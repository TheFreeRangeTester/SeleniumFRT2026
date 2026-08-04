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
