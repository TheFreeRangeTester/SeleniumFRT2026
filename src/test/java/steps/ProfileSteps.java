package steps;

import driver.DriverManager;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.testng.asserts.SoftAssert;
import static org.testng.Assert.assertEquals;
import pages.ProfilePage;
import support.TestUser;

public class ProfileSteps {

    private ProfilePage profilePage;

    @Cuando("abre su perfil")
    public void openProfile() {
        profilePage = new ProfilePage(DriverManager.getDriver());
        profilePage.open();
    }

    @Entonces("el perfil contiene los datos sembrados del usuario")
    public void verifySeededProfile() {
        SoftAssert softly = new SoftAssert();

        softly.assertEquals(
                profilePage.name(),
                "Temporary Tester",
                "Nombre inesperado"
        );
        softly.assertEquals(
                profilePage.email(),
                TestUser.EMAIL,
                "Email inesperado"
        );
        softly.assertEquals(
                profilePage.phone(),
                "+64 21 000 0000",
                "Teléfono inesperado"
        );
        softly.assertEquals(
                profilePage.language(),
                "Español (NZ)",
                "Idioma inesperado"
        );
        softly.assertFalse(
                profilePage.isEmailEditable(),
                "El email debería estar deshabilitado"
        );
        softly.assertTrue(
                profilePage.isSaveEnabled(),
                "Guardar cambios debería estar habilitado"
        );

        softly.assertAll();
    }
}
