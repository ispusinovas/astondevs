package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class PaymentFrame {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By AMOUNT_LABEL = By.cssSelector(".pay-description__cost, [class*='amount'], [class*='cost']");
    private static final By PHONE_LABEL = By.cssSelector(".pay-description__text, [class*='description'], [class*='phone']");
    private static final By SUBMIT_BTN = By.cssSelector("button[type='submit'], .btn-submit, [class*='submit']");
    private static final By PAYMENT_ICONS = By.cssSelector("img[src*='visa'], img[src*='master'], img[src*='belcard']," + " img[src*='mir'], .cards-icons img, [class*='payment-icon'] img");

    PaymentFrame(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
    }

    public String getAmountText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT_LABEL)).getText().trim();
    }

    public String getPhoneText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_LABEL)).getText().trim();
    }

    public String getSubmitButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(SUBMIT_BTN)).getText().trim();
    }

    public List<String> getCardFieldLabels() {
        return driver.findElements(By.cssSelector("input")).stream().filter(WebElement::isDisplayed).map(e -> {
            String ph = e.getAttribute("placeholder");
            if (ph != null && !ph.isBlank()) return ph;
            String al = e.getAttribute("aria-label");
            return (al != null) ? al : "";
        }).filter(s -> !s.isBlank()).collect(Collectors.toList());
    }

    public List<WebElement> getPaymentIcons() {
        return driver.findElements(PAYMENT_ICONS).stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
    }

    public void close() {
        driver.switchTo().defaultContent();
    }
}
