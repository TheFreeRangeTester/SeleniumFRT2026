package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "classpath:features",
        glue = {"steps", "hooks"},
        plugin = {
                "pretty",
                "html:build/reports/cucumber/cucumber.html",
                "json:build/reports/cucumber/cucumber.json",
                "junit:build/test-results/cucumber/cucumber.xml"
        },
        monochrome = true
)
public class RunCucumberTest extends AbstractTestNGCucumberTests {
}
