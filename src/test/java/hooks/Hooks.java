package hooks;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void startBrowser() {
        DriverManager.startDriver();
    }

    @After
    public void closeBrowser(Scenario scenario) {
        try {
            if (scenario.isFailed() && DriverManager.hasDriver()) {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver())
                        .getScreenshotAs(OutputType.BYTES);

                scenario.attach(screenshot, "image/png", "Estado del navegador al fallar");
            }
        } finally {
            DriverManager.quitDriver();
        }
    }
}
