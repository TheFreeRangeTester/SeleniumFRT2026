package components;

import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pages.BasePage;

/**
 * Componente reutilizable de la navegación principal de BuggyBank.
 */
public class NavigationComponent extends BasePage {

    private static final By NAVIGATION_LINKS = By.cssSelector("aside nav a");

    public NavigationComponent(WebDriver driver) {
        super(driver);
    }

    public Map<String, String> linkUrlsByLabel() {
        return findAllPresent(NAVIGATION_LINKS).stream()
                .collect(Collectors.toMap(
                        WebElement::getText,
                        element -> element.getAttribute("href")
                ));
    }

    public void visit(String url) {
        navigateTo(url);
        waitForUrl(url);
    }

    public boolean pageContains(String text) {
        return waitUntil(currentDriver -> currentDriver.getPageSource().contains(text));
    }
}
