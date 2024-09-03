import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PageTest extends WebDriver {

    @Test
    @ExtendWith(AllureJunit5.class)
    public void testBlockTitle() {
        TopUpPage topUpPage = new TopUpPage(driver);
        String title = topUpPage.getBlockTitleText();
        assertEquals("Онлайн пополнение\n" +
                "без комиссии", title, "Название блока не соответствует ожидаемому");
    }

    @Test
    @ExtendWith(AllureJunit5.class)
    public void testPaymentSystemLogos() {
        TopUpPage topUpPage = new TopUpPage(driver);
        List<WebElement> logos = topUpPage.getPaymentLogos();
        assertTrue(logos.size() > 0, "Логотипы платежных систем отсутствуют в блоке");
    }

    @Test
    @ExtendWith(AllureJunit5.class)
    public void testServiceDetailsLink() {
        TopUpPage topUpPage = new TopUpPage(driver);
        topUpPage.clickDetailsLink();
        assertTrue(driver.getCurrentUrl().contains("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/"), "Переход по ссылке 'Подробнее о сервисе' не произошел");
    }

    @Test
    @ExtendWith(AllureJunit5.class)
    public void testFormPlaceholders() {
        TopUpPage topUpPage = new TopUpPage(driver);


        By phoneInputLocator = By.id("connection-phone");
        By amountInputLocator = By.id("connection-sum");
        By creditCardInputLocator = By.id("cc-number");

        assertEquals("Номер телефона", topUpPage.getPlaceholderText(phoneInputLocator), "Неверный текст в placeholder для поля ввода номера телефона");
        assertEquals("Сумма", topUpPage.getPlaceholderText(amountInputLocator), "Неверный текст в placeholder для поля ввода суммы");
        assertEquals("Введите номер карты", topUpPage.getPlaceholderText(creditCardInputLocator), "Неверный текст в placeholder для поля ввода реквизитов карты");
    }

    @Test
    @ExtendWith(AllureJunit5.class)
    public void testServiceFormSubmission() {
        TopUpPage topUpPage = new TopUpPage(driver);


        topUpPage.enterPhoneNumber("297777777");
        topUpPage.selectServiceType();
        topUpPage.clickContinueButton();


        assertTrue(topUpPage.isAmountInputDisplayed(), "Поле ввода суммы не отображается");
        assertTrue(topUpPage.isCreditCardInputDisplayed(), "Поле ввода карты не отображается");


        assertEquals("297777777", driver.findElement(By.id("summary-phone-number")).getText(), "Неверный номер телефона в диалоговом окне");
        assertEquals("10 BYN", driver.findElement(By.id("summary-amount")).getText(), "Неверная сумма в диалоговом окне");

        List<WebElement> paymentIcons = topUpPage.getPaymentIcons();
        assertTrue(paymentIcons.size() > 0, "Иконки платёжных систем отсутствуют в диалоговом окне");
    }
}