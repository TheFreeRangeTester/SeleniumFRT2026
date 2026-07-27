package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Administra una instancia de WebDriver por hilo de ejecución.
 *
 * <p>Los hooks de Cucumber son responsables de iniciar y cerrar el navegador.
 * Las páginas solamente solicitan el driver activo.</p>
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverManager() {
        // Esta clase solamente expone operaciones estáticas.
    }

    public static void startDriver() {
        if (DRIVER.get() != null) {
            throw new IllegalStateException("Ya existe un WebDriver activo para este escenario.");
        }

        // Selenium Manager resuelve ChromeDriver automáticamente.
        DRIVER.set(new ChromeDriver());
    }

    public static WebDriver getDriver() {
        WebDriver driver = DRIVER.get();

        if (driver == null) {
            throw new IllegalStateException(
                    "No hay un WebDriver activo. El escenario debe ejecutarse mediante los hooks de Cucumber."
            );
        }

        return driver;
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();

        try {
            if (driver != null) {
                driver.quit();
            }
        } finally {
            DRIVER.remove();
        }
    }
}
