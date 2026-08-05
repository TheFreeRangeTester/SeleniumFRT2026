package pages;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Operaciones comunes para los Page Objects del proyecto.
 *
 * <p>Cada página concreta define sus locators como {@link By} y expresa acciones
 * del negocio utilizando estos métodos reutilizables.</p>
 */
public abstract class BasePage {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    protected final WebDriver driver;
    private final WebDriverWait wait;

    protected BasePage(WebDriver driver) {
        this.driver = Objects.requireNonNull(driver, "El WebDriver no puede ser null.");
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator)
                .stream()
                .filter(WebElement::isDisplayed)
                .toList();
    }

    protected List<WebElement> findAllPresent(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected <T> T waitUntil(Function<WebDriver, T> condition) {
        return wait.until(condition);
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected void selectByVisibleText(By locator, String text) {
        new Select(find(locator)).selectByVisibleText(text);
    }

    protected void selectByValue(By locator, String value) {
        new Select(find(locator)).selectByValue(value);
    }

    protected void selectByIndex(By locator, int index) {
        new Select(find(locator)).selectByIndex(index);
    }

    protected List<String> getDropdownOptions(By locator) {
        return new Select(find(locator))
                .getOptions()
                .stream()
                .map(WebElement::getText)
                .toList();
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected void waitForUrlEndingWith(String path) {
        wait.until(currentDriver -> currentDriver.getCurrentUrl().endsWith(path));
    }

    protected void waitForUrl(String url) {
        wait.until(ExpectedConditions.urlToBe(url));
    }
}
