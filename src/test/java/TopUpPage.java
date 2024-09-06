import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TopUpPage {
    private WebDriver driver;

    // Локаторы для элементов страницы
    private By blockTitle = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2");
    private By paymentLogos = By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[2]/ul");
    private By detailsLink = By.cssSelector("#pay-section > div > div > div.col-12.col-xl-8 > section > div > a");
    private By phoneNumberInput = By.id("connection-phone");
    private By serviceTypeOption = By.id("select__wrapper");
    private By continueButton = By.cssSelector("#pay-connection > button");
    private By successMessage = By.id("success-message");
    private By amountInput = By.id("connection-sum");
    private By creditCardInput = By.id("cc-number");
    private By paymentIcons = By.id("visa_id"); // сайт опять висит, поставил предположительный селектор

    public TopUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getBlockTitleText() {
        return driver.findElement(blockTitle).getText();
    }

    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogos);
    }

    public void clickDetailsLink() {
        driver.findElement(detailsLink);
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }

    public void selectServiceType() {
        driver.findElement(serviceTypeOption).click();
    }

    public void clickContinueButton() {
        driver.findElement(continueButton).click();
    }

    public String getSuccessMessageText() {
        return driver.findElement(successMessage).getText();
    }

    public boolean isAmountInputDisplayed() {
        return driver.findElement(amountInput).isDisplayed();
    }

    public boolean isCreditCardInputDisplayed() {
        return driver.findElement(creditCardInput).isDisplayed();
    }

    public List<WebElement> getPaymentIcons() {
        return driver.findElements(paymentIcons);
    }

    public String getPlaceholderText(By locator) {
        return driver.findElement(locator).getAttribute("placeholder");
    }
}