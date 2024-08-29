import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class webForm {
    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\\\chromedriver-win64\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
    }

    @Test
    private void checkBlockTitle() {
        By blockTitleLocator = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2");
        String title = driver.findElement(blockTitleLocator).getText();
        assertEquals(title, "Онлайн пополнение\n" +
                "без комиссии");
    }
    @Test
    private void checkLogos() {
        By visaLogoLocator = By.xpath("//img[@alt='Visa']");
        assertTrue(driver.findElements(visaLogoLocator).size() > 0);
    }
    @Test
    private void checkServiceLink() {
        By serviceLinkLocator = By.linkText("Подробнее о сервисе");
        driver.findElement(serviceLinkLocator).click();
    }
    @Test
    private void checkPlaceholderTotal() {
        By placeholderLocator = By.className("total_rub");
        for (WebElement element : driver.findElements(placeholderLocator)) {
            String placeholderText = element.getAttribute("placeholder");
            if (placeholderText.contains("Сумма")) {
                break;
            } else {
                Assert.fail("Ожидаемый текст placeholder не найден");
            }
        }
    }
    @Test
    private void checkPlaceholderPhone() {
        By placeholderLocator = By.className("phone");
        for (WebElement element : driver.findElements(placeholderLocator)) {
            String placeholderText = element.getAttribute("placeholder");
            if (placeholderText.contains("Номер телефона")) {
                break;
            } else {
                Assert.fail("Ожидаемый текст placeholder не найден");
            }
        }
    }
    @Test
    private void checkPlaceholderEmail() {
        By placeholderLocator = By.className("email");
        for (WebElement element : driver.findElements(placeholderLocator)) {
            String placeholderText = element.getAttribute("placeholder");
            if (placeholderText.contains("E-mail для отправки чека")) {
                break;
            } else {
                Assert.fail("Ожидаемый текст placeholder не найден");
            }
        }
    }
    @Test
    private void fillFormAndClickContinue() {
        By rechargeFormLocator = By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section");
        driver.findElement(rechargeFormLocator).findElement(By.xpath("//*[@id=\"connection-phone\"]")).sendKeys("297777777");
        driver.findElement(rechargeFormLocator).findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button")).click();
        driver.findElement(rechargeFormLocator).findElement(By.xpath("//*[@id=\"pay-connection\"]/button")).click();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}

// Все тесты выполняет, но я не понимаю как мне написать проверки для фрейма