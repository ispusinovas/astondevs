package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class PayBlock {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebElement root;

    private static final By MORE_INFO_LINK = By.cssSelector("a[href$='/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/']");

    PayBlock(WebDriver driver, WebDriverWait wait, WebElement root) {
        this.driver = driver;
        this.wait = wait;
        this.root = root;
    }

    public String getTitle() {
        WebElement h2 = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(root, By.xpath(".//h2[normalize-space(.)='Онлайн пополнение без комиссии']"))).get(0);
        return h2.getText().replace("\n", " ").trim();
    }

    public List<WebElement> getVisibleLogos() {
        return root.findElements(By.cssSelector("img")).stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
    }

    public WebElement getMoreInfoLink() {
        return root.findElement(MORE_INFO_LINK);
    }

    public PayTab activateTab(String tabName) {
        WebElement tab = root.findElements(By.xpath(".//*[normalize-space(.)='" + tabName + "']")).stream().filter(WebElement::isDisplayed).findFirst().orElseThrow(() -> new AssertionError("Таб не найден в #pay-section: " + tabName));

        wait.until(ExpectedConditions.elementToBeClickable(tab)).click();
        return new PayTab(driver, wait, root);
    }
}
