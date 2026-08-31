package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object de la pantalla de pagos de BuggyBank.
 */
public class PaymentsPage extends BasePage {

    private static final String DEFAULT_BASE_URL = "https://buggybank-seven.vercel.app";

    private static final By PAGE_TITLE = By.xpath("//main//h3[normalize-space()='Pagos']");
    private static final By STATUS_FILTER =
            By.cssSelector("[data-testid='payments-status-filter']");
    private static final By NEW_PAYMENT_LINK =
            By.cssSelector("[data-testid='payments-new-link']");
    private static final By PAYMENT_ROWS = By.cssSelector("[data-testid='payment-row']");
    private static final By PAYMENT_STATUSES =
            By.cssSelector("[data-testid='payment-status']");

    public PaymentsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL);
        navigateTo(baseUrl + "/payments");
        find(PAGE_TITLE);
        find(STATUS_FILTER);
    }

    public List<String> statusFilterOptions() {
        return getDropdownOptions(STATUS_FILTER);
    }

    public void filterByStatus(String status) {
        selectByVisibleText(STATUS_FILTER, status);
        waitUntil(ignored -> !visibleStatuses().isEmpty());
    }

    public List<String> visibleStatuses() {
        return findAll(PAYMENT_STATUSES).stream()
                .map(WebElement::getText)
                .toList();
    }

    public int visiblePaymentCount() {
        return findAll(PAYMENT_ROWS).size();
    }

    public String newPaymentUrl() {
        return find(NEW_PAYMENT_LINK).getAttribute("href");
    }
}