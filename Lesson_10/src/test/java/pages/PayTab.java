package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class PayTab {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebElement payRoot;

    PayTab(WebDriver driver, WebDriverWait wait, WebElement payRoot) {
        this.driver = driver;
        this.wait = wait;
        this.payRoot = payRoot;

        wait.until(d -> !getVisibleInputs().isEmpty());
    }

    private List<WebElement> getVisibleInputs() {
        return payRoot.findElements(By.cssSelector("input")).stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
    }

    public List<String> getFieldLabels() {
        return getVisibleInputs().stream().map(e -> {
            String ph = e.getAttribute("placeholder");
            if (ph != null && !ph.isBlank()) return ph;
            String al = e.getAttribute("aria-label");
            return (al != null) ? al : "";
        }).filter(s -> !s.isBlank()).collect(Collectors.toList());
    }

    private WebElement phoneField() {

        return getVisibleInputs().stream().filter(e -> {
            String ph = e.getAttribute("placeholder");
            String al = e.getAttribute("aria-label");
            return "Номер телефона".equals(ph) || "+375".equals(al);
        }).findFirst().orElseThrow(() -> new AssertionError("Поле номера телефона не найдено"));
    }

    private WebElement sumField() {
        WebElement phone = phoneField();
        WebElement form = phone.findElement(By.xpath("./ancestor::form[1]"));
        String phoneAl = phone.getAttribute("aria-label");
        String phonePh = phone.getAttribute("placeholder");

        return form.findElements(By.cssSelector("input")).stream().filter(WebElement::isDisplayed).filter(e -> {
            String al = e.getAttribute("aria-label");
            String ph = e.getAttribute("placeholder");

            return !"+375".equals(al) && !"Номер телефона".equals(ph) && !"E-mail для отправки чека".equals(al) && !"E-mail для отправки чека".equals(ph);
        }).findFirst().orElseThrow(() -> new AssertionError("Поле суммы не найдено в форме"));
    }

    public PayTab fillPhone(String phone) {
        WebElement field = phoneField();
        field.clear();
        field.sendKeys(phone);
        return this;
    }

    public PayTab fillSum(String sum) {
        WebElement field = sumField();
        field.clear();
        field.sendKeys(sum);
        return this;
    }

    public PaymentFrame clickContinue() {
        WebElement form = phoneField().findElement(By.xpath("./ancestor::form[1]"));
        WebElement btn = form.findElement(By.xpath(".//button[normalize-space(.)='Продолжить']"));

        wait.until(ExpectedConditions.elementToBeClickable(btn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.cssSelector("iframe.payment-widget-iframe, " + "iframe[src*='bepaid'], iframe[src*='payment'], iframe[src*='webpay']")));
        return new PaymentFrame(driver, wait);
    }


}
