package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SetupCheckTest {

    @Test
    public void abreChromeConSeleniumManager() {
        WebDriver driver = null;

        try {
            // No se configura ChromeDriver: Selenium Manager lo resuelve automáticamente.
            driver = new ChromeDriver();
            driver.get("data:text/html,<title>Starter listo</title><h1>Entorno configurado</h1>");

            Assert.assertEquals(
                    driver.getTitle(),
                    "Starter listo",
                    "Chrome abrió, pero no pudo cargar el contenido local de comprobación."
            );
        } catch (Exception exception) {
            throw new AssertionError(
                    "No se pudo abrir Chrome. Verificá que Chrome esté instalado y que Selenium Manager "
                            + "pueda descargar o encontrar ChromeDriver. Detalle: " + exception.getMessage(),
                    exception
            );
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
