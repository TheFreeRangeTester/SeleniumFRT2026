package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * Page Object del perfil de BuggyBank.
 *
 * <p>Expone el estado visible de la página; las expectativas pertenecen a los
 * steps que verifican cada escenario.</p>
 */
public class ProfilePage extends BasePage {

    private static final String DEFAULT_BASE_URL = "https://buggybank-seven.vercel.app";

    private static final By NAME = By.id("fullName");
    private static final By EMAIL = By.id("email");
    private static final By PHONE = By.id("phone");
    private static final By LANGUAGE = By.id("language");
    private static final By SAVE_BUTTON =
            By.xpath("//button[normalize-space()='Guardar cambios']");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        navigateTo(baseUrl + "/profile");
    }

    public String name() {
        return find(NAME).getAttribute("value");
    }

    public String email() {
        return find(EMAIL).getAttribute("value");
    }

    public String phone() {
        return find(PHONE).getAttribute("value");
    }

    public String language() {
        return new Select(find(LANGUAGE))
                .getFirstSelectedOption()
                .getText();
    }

    public boolean isEmailEditable() {
        return find(EMAIL).isEnabled();
    }

    public boolean isSaveEnabled() {
        return find(SAVE_BUTTON).isEnabled();
    }
}
