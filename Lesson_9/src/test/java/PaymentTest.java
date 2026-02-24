import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

    private static final String BASE_URL = "https://www.mts.by/";
    private static final String MORE_INFO_PATH = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

    private static final By PAY_SECTION = By.id("pay-section");
    private static final By COOKIE_AGREE = By.id("cookie-agree");

    @BeforeAll
    static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        driver.get(BASE_URL);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(COOKIE_AGREE)).click();
        } catch (Exception ignored) {
        }
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) driver.quit();
    }

    private WebElement paySection() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_SECTION));
    }

    private WebElement firstVisible(WebElement root, By locator, String error) {
        return root.findElements(locator).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new AssertionError(error));
    }

    private void activateServicesTab(WebElement pay) {
        WebElement tab = pay.findElements(By.xpath(".//*[normalize-space(.)='Услуги связи']")).stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElseThrow(() -> new AssertionError("Не найден таб 'Услуги связи' в #pay-section"));

        wait.until(ExpectedConditions.elementToBeClickable(tab)).click();

        wait.until(d -> pay.findElements(By.cssSelector("input[placeholder='Номер телефона']")).stream()
                .anyMatch(WebElement::isDisplayed));
    }

    @Test
    @Order(1)
    void testBlockTitle() {
        WebElement pay = paySection();

        WebElement title = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(
                pay, By.xpath(".//h2[normalize-space(.)='Онлайн пополнение без комиссии']")
        )).get(0);

        String actual = title.getText().replace("\n", " ").trim();
        assertEquals("Онлайн пополнение без комиссии", actual);
    }

    @Test
    @Order(2)
    void testPaymentLogosPresent() {
        WebElement pay = paySection();
        activateServicesTab(pay);

        List<WebElement> visibleImgs = pay.findElements(By.cssSelector("img")).stream()
                .filter(WebElement::isDisplayed)
                .collect(Collectors.toList());

        assertFalse(visibleImgs.isEmpty(), "В блоке 'Онлайн пополнение' нет видимых логотипов (img)");

        String src = visibleImgs.get(0).getAttribute("src");
        assertNotNull(src, "src у логотипа null");
        assertFalse(src.trim().isEmpty(), "src у логотипа пустой");
    }


    @Test
    @Order(3)
    void testMoreAboutServiceLink() {
        WebElement pay = paySection();

        WebElement link = pay.findElement(By.cssSelector("a[href$='" + MORE_INFO_PATH + "']"));
        assertTrue(link.isDisplayed(), "Ссылка 'Подробнее о сервисе' не видна");

        String originalWindow = driver.getWindowHandle();
        Set<String> before = driver.getWindowHandles();

        wait.until(ExpectedConditions.elementToBeClickable(link)).click();

        Boolean opened = null;
        try {
            opened = wait.until(d -> d.getWindowHandles().size() > before.size());
        } catch (TimeoutException ignored) {
        }

        if (Boolean.TRUE.equals(opened)) {
            Set<String> after = driver.getWindowHandles();
            after.removeAll(before);
            String newWindow = after.iterator().next();

            driver.switchTo().window(newWindow);
            wait.until(d -> d.getCurrentUrl().contains(MORE_INFO_PATH));
            assertTrue(driver.getCurrentUrl().contains(MORE_INFO_PATH));

            driver.close();
            driver.switchTo().window(originalWindow);
            return;
        }

        wait.until(d -> d.getCurrentUrl().contains(MORE_INFO_PATH));
        assertTrue(driver.getCurrentUrl().contains(MORE_INFO_PATH));

        driver.navigate().back();
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_SECTION));
    }

    @Test
    @Order(4)
    void testContinueButtonWithPhoneNumber() {
        WebElement pay = paySection();
        activateServicesTab(pay);

        WebElement phoneField = firstVisible(
                pay,
                By.cssSelector("input[placeholder='Номер телефона']"),
                "Не найдено видимое поле 'Номер телефона' в #pay-section"
        );

        WebElement serviceForm = phoneField.findElement(By.xpath("./ancestor::form[1]"));

        phoneField.clear();
        phoneField.sendKeys("297777777");

        WebElement sumField = serviceForm.findElements(By.cssSelector("input[type='text'], input[type='number']")).stream()
                .filter(WebElement::isDisplayed)
                .filter(e -> !"Номер телефона".equals(e.getAttribute("placeholder")))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Не найдено поле суммы в форме 'Услуги связи'"));

        sumField.clear();
        sumField.sendKeys("10");

        WebElement continueBtn = serviceForm.findElement(By.xpath(".//button[normalize-space(.)='Продолжить']"));
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.cssSelector("iframe[src*='payment'], iframe[src*='bepaid'], iframe[src*='webpay']")
        ));

        WebElement frameBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        assertTrue(frameBody.isDisplayed(), "Фрейм оплаты не загрузился");

        driver.switchTo().defaultContent();
    }
}
