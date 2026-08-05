package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object de los filtros y resultados visibles de movimientos.
 */
public class TransactionsPage extends BasePage {

    private static final String DEFAULT_BASE_URL = "https://buggybank-seven.vercel.app";

    private static final By DIRECTION_FILTER =
            By.cssSelector("[data-testid='transactions-direction']");
    private static final By SEARCH_INPUT =
            By.cssSelector("[data-testid='transactions-search']");
    private static final By ROWS = By.cssSelector("tbody tr");
    private static final By DIRECTION_BADGES =
            By.cssSelector("tbody tr td:nth-child(2) span");
    private static final By DESCRIPTION_CELLS =
            By.cssSelector("tbody tr td:nth-child(6)");

    public TransactionsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        navigateTo(baseUrl + "/transactions");
        find(DIRECTION_FILTER);
    }

    public void filterByDirection(String direction) {
        selectByVisibleText(DIRECTION_FILTER, direction);
    }

    public void search(String text) {
        type(SEARCH_INPUT, text);
        waitUntil(ignored -> {
            List<String> descriptions = visibleDescriptions();
            return !descriptions.isEmpty()
                    && descriptions.stream().allMatch(description -> description.contains(text));
        });
    }

    public int visibleRowCount() {
        return findAll(ROWS).size();
    }

    public List<String> visibleDescriptions() {
        return findAll(DESCRIPTION_CELLS).stream()
                .map(WebElement::getText)
                .toList();
    }

    public List<String> visibleDirections() {
        return findAll(DIRECTION_BADGES).stream()
                .map(WebElement::getText)
                .toList();
    }
}
