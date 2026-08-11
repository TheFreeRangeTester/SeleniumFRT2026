package steps;

import static org.testng.Assert.assertTrue;

import driver.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import pages.LoginPage;
import support.TestUser;

public class LoginSteps {

    private LoginPage loginPage;

    @Dado("que estoy en la página de login de BuggyBank")
    public void openLoginPage() {
        loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.open();
    }

    @Cuando("inicio sesión con el usuario de prueba")
    public void loginWithTestUser() {
        loginPage.loginAs(TestUser.EMAIL, TestUser.PASSWORD);
    }

    @Dado("que el usuario de prueba inició sesión en BuggyBank")
    public void testUserIsLoggedIn() {
        openLoginPage();
        loginWithTestUser();
    }

    @Entonces("veo el dashboard de {string}")
    public void verifyDashboard(String name) {
        assertTrue(
                loginPage.isDashboardDisplayedFor(name),
                "No se mostró el dashboard esperado para " + name
        );
    }
}
