package steps;

import static org.testng.Assert.assertTrue;

import driver.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    @Dado("que estoy en la página de login de BuggyBank")
    public void openLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.open();
    }

    @Cuando("inicio sesión con el email {string} y la contraseña {string}")
    public void login(String email, String password) {
        loginPage.loginAs(email, password);
    }

    @Entonces("veo el dashboard de {string}")
    public void verifyDashboard(String name) {
        assertTrue(
                loginPage.isDashboardDisplayedFor(name),
                "No se mostró el dashboard esperado para " + name
        );
    }
}
