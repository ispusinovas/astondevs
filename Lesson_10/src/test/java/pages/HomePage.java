package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By COOKIE_BTN = By.id("cookie-agree");
    private static final By PAY_SECTION = By.id("pay-section");

    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public HomePage open(String url) {
        driver.get(url);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BTN)).click();
        } catch (Exception ignored) {
        }
        return this;
    }

    public PayBlock payBlock() {
        return new PayBlock(driver, wait, wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_SECTION)));
    }
}
