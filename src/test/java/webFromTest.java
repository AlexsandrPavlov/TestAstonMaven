import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.testng.Assert.*;


public class webFromTest {
    WebDriver driver;

    @org.testng.annotations.Test
    public void testOnlineRechargeBlock() {

        System.setProperty("webdriver.chrome.driver", "C:\\\\chromedriver-win64\\\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        String onlineRechargeText = driver.findElement(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2")).getText();
        assertNotNull(onlineRechargeText);
        assertEquals(onlineRechargeText, "Онлайн пополнение\n" +
                "без комиссии");
        driver.quit();
    }

    @org.testng.annotations.Test
    public void testLogo() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        By LogoLocator;
        LogoLocator = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul");
        boolean isLogoPresent = driver.findElements(LogoLocator).size() > 0;
        assertTrue(isLogoPresent);
        driver.quit();
    }
    @org.testng.annotations.Test
    public void testInfo(){
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
       By moreInfoLinkLocator = By.linkText("Подробнее о сервисе");
       driver.findElement(moreInfoLinkLocator).click();
       String titleOfNewPage = driver.getTitle();
       assertEquals(titleOfNewPage, "Порядок оплаты и безопасность интернет платежей");
        driver.quit();
    }
    @org.testng.annotations.Test
    public void testPayment(){
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        By phoneNumberFieldLocator = By.id("connection-phone");
        By cashSumLocator = By.xpath("//*[@id=\"connection-sum\"]");
        By continueButtonLocator = By.xpath("//*[@id=\"pay-connection\"]/button");
        driver.findElement(phoneNumberFieldLocator).sendKeys("297777777");
        driver.findElement(cashSumLocator).sendKeys("12");
        driver.findElement(continueButtonLocator).click();
        String titleAfterContinueClick = driver.getTitle();
        assertEquals(titleAfterContinueClick, "Оплата: Услуги связи Номер:375297777777");
        driver.quit();
    }
}






