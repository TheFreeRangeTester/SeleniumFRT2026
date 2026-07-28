package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object del flujo de acceso a BuggyBank.
 */
public class LoginPage extends BasePage {

    private static final String DEFAULT_BASE_URL = "https://buggybank-seven.vercel.app";

    private static final By EMAIL_INPUT = By.cssSelector("[data-testid='login-email']");
    private static final By PASSWORD_INPUT = By.cssSelector("[data-testid='login-password']");
    private static final By LOGIN_BUTTON = By.cssSelector("[data-testid='login-submit']");
    private static final By DASHBOARD_GREETING =
            By.xpath("//main//h3[starts-with(normalize-space(), 'Hola, ')]");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        navigateTo(baseUrl + "/login");
    }

    public void loginAs(String email, String password) {
        type(EMAIL_INPUT, email);
        type(PASSWORD_INPUT, password);
        click(LOGIN_BUTTON);
    }

    public boolean isDashboardDisplayedFor(String name) {
        String greeting = getText(DASHBOARD_GREETING);
        return getCurrentUrl().endsWith("/dashboard")
                && greeting.equals("Hola, " + name);
    }
}
