import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.PayBlock;
import pages.PaymentFrame;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static HomePage homePage;

    private static final String BASE_URL = "https://www.mts.by/";
    private static final String MORE_INFO_PATH = "/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";

    private static final String PHONE = "297777777";
    private static final String SUM = "10";

    @BeforeAll
    static void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        homePage = new HomePage(driver, wait).open(BASE_URL);
    }

    @AfterAll
    static void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    @Order(1)
    void testBlockTitle() {
        assertEquals("Онлайн пополнение без комиссии", homePage.payBlock().getTitle(), "Заголовок блока некорректен");
    }

    @Test
    @Order(2)
    void testPaymentLogosPresent() {
        PayBlock pay = homePage.payBlock();
        pay.activateTab("Услуги связи");
        List<WebElement> logos = pay.getVisibleLogos();

        assertFalse(logos.isEmpty(), "В блоке оплаты нет видимых логотипов");
        String src = logos.get(0).getAttribute("src");
        assertNotNull(src, "src у первого логотипа — null");
        assertFalse(src.trim().isEmpty(), "src у первого логотипа пустой");
    }

    @Test
    @Order(3)
    void testMoreAboutServiceLink() {
        PayBlock pay = homePage.payBlock();
        WebElement link = pay.getMoreInfoLink();
        assertTrue(link.isDisplayed(), "Ссылка 'Подробнее о сервисе' не видна");

        String originalWindow = driver.getWindowHandle();
        Set<String> before = driver.getWindowHandles();
        wait.until(ExpectedConditions.elementToBeClickable(link)).click();

        boolean newTabOpened = false;
        try {
            newTabOpened = wait.until(d -> d.getWindowHandles().size() > before.size());
        } catch (TimeoutException ignored) {
        }

        if (newTabOpened) {
            Set<String> after = driver.getWindowHandles();
            after.removeAll(before);
            driver.switchTo().window(after.iterator().next());
            wait.until(d -> d.getCurrentUrl().contains(MORE_INFO_PATH));
            assertTrue(driver.getCurrentUrl().contains(MORE_INFO_PATH));
            driver.close();
            driver.switchTo().window(originalWindow);
        } else {
            wait.until(d -> d.getCurrentUrl().contains(MORE_INFO_PATH));
            assertTrue(driver.getCurrentUrl().contains(MORE_INFO_PATH));
            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pay-section")));
        }
    }

    @Test
    @Order(4)
    void testContinueButtonWithPhoneNumber() {
        PaymentFrame frame = homePage.payBlock().activateTab("Услуги связи").fillPhone(PHONE).fillSum(SUM).clickContinue();

        assertTrue(frame.getAmountText() != null, "Фрейм оплаты не загрузился");
        frame.close();
    }

    @Test
    @Order(5)
    void testCommunicationServiceLabels() {
        List<String> labels = homePage.payBlock().activateTab("Услуги связи").getFieldLabels();

        System.out.println("[Услуги связи] labels: " + labels);
        assertAll("Надписи полей 'Услуги связи'", () -> assertTrue(labels.stream().anyMatch(l -> l.contains("375") || l.contains("телефона")), "Нет поля номера телефона. Получено: " + labels), () -> assertTrue(labels.stream().anyMatch(l -> l.contains("Руб") || l.contains("умм")), "Нет поля суммы. Получено: " + labels));
    }

    @Test
    @Order(6)
    void testHomeInternetLabels() {
        List<String> labels = homePage.payBlock().activateTab("Домашний интернет").getFieldLabels();

        System.out.println("[Домашний интернет] labels: " + labels);
        assertAll("Надписи полей 'Домашний интернет'", () -> assertTrue(labels.stream().anyMatch(l -> l.contains("375") || l.contains("абонент") || l.contains("телефона")), "Нет поля номера абонента. Получено: " + labels), () -> assertTrue(labels.stream().anyMatch(l -> l.contains("Руб") || l.contains("умм")), "Нет поля суммы. Получено: " + labels));
    }

    @Test
    @Order(7)
    void testInstallmentLabels() {
        List<String> labels = homePage.payBlock().activateTab("Рассрочка").getFieldLabels();

        System.out.println("[Рассрочка] labels: " + labels);
        assertAll("Надписи полей 'Рассрочка'", () -> assertTrue(labels.contains("Номер телефона"), "Нет поля номера телефона. Получено: " + labels), () -> assertTrue(labels.contains("Сумма"), "Нет поля суммы. Получено: " + labels));
    }

    @Test
    @Order(8)
    void testDebtLabels() {
        List<String> labels = homePage.payBlock().activateTab("Задолженность").getFieldLabels();

        System.out.println("[Задолженность] labels: " + labels);
        assertAll("Надписи полей 'Задолженность'", () -> assertTrue(labels.contains("Номер телефона"), "Нет поля номера телефона. Получено: " + labels), () -> assertTrue(labels.contains("Сумма"), "Нет поля суммы. Получено: " + labels));
    }

    @Test
    @Order(9)
    void testPaymentFrameDetails() {
        PaymentFrame frame = homePage.payBlock().activateTab("Услуги связи").fillPhone(PHONE).fillSum(SUM).clickContinue();

        String amountText = frame.getAmountText();
        assertTrue(amountText.contains(SUM), "Сумма в окне оплаты некорректна. Ожидается «" + SUM + "», получено: «" + amountText + "»");

        String btnText = frame.getSubmitButtonText();
        assertTrue(btnText.contains(SUM), "Кнопка оплаты не содержит сумму. Ожидается «" + SUM + "», получено: «" + btnText + "»");

        String phoneText = frame.getPhoneText();
        assertTrue(phoneText.contains(PHONE), "Телефон в окне оплаты некорректен. Ожидается «" + PHONE + "», получено: «" + phoneText + "»");

        List<String> cardLabels = frame.getCardFieldLabels();
        System.out.println("[Frame] поля карты: " + cardLabels);
        assertFalse(cardLabels.isEmpty(), "В окне оплаты нет полей для ввода данных карты");

        List<WebElement> icons = frame.getPaymentIcons();
        assertFalse(icons.isEmpty(), "Иконки платёжных систем (Visa/MC/Белкарт) в окне оплаты не найдены");

        frame.close();
    }
}
