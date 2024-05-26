package ru.netology.test;

import lombok.Value;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.SQLHelper.cleanDatabase;

public class CreditCardTest {

    PaymentPage paymentPage;

    @AfterAll
    static void tearDownAll() {
        cleanDatabase();
    }

    @BeforeEach
    void setUp() {
        var pageUrl = System.getProperty("page.url");
        paymentPage = open(pageUrl, PaymentPage.class);
    }

    @Test
    @DisplayName("Успешная оплата кредитной картой с валидными данными")
    void shouldSuccessfulPayWithCreditCard() {
        var cardInfo = new DataHelper().getValidCardInfo("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifySuccessMessage();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("Оплата кредитной картой с пустым номером карты")
    void shouldPayWithEmptyCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCardNumber();
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата кредитной картой с 0 номером карты")
    void shouldPayWithZeroCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNullCardNumber();
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата кредитной картой с пустым месяцем")
    void shouldPayWithEmptyMonth() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyMonth("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormatMonth();
    }

    @Test
    @DisplayName("Оплата кредитной картой с пустым годом")
    void shouldPayWithEmptyYear() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyYear("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата кредитной картой с пустым именем владельца")
    void shouldPayWithEmptyName() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyName("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyNullName();
    }

    @Test
    @DisplayName("Оплата кредитной картой с невалидным именем владельца")
    void shouldPayWithInvalidName() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNumberAndSymBolName("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyNullName();
    }

    @Test
    @DisplayName("Оплата кредитной картой с пустым CVC")
    void shouldPayWithEmptyCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCvc("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата кредитной картой с нулевым CVC")
    void shouldPayWithZeroCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNullCvc("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата кредитной картой с невалидным номером карты")
    void shouldPayWithInvalidCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCardNumber();
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата кредитной картой с невалидным месяцем")
    void shouldPayWithInvalidMonth() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidMonth("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormatMonth();
    }

    @Test
    @DisplayName("Оплата кредитной картой с невалидным годом")
    void shouldPayWithInvalidYear() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidYear("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormatYear();
    }

    @Test
    @DisplayName("Оплата кредитной картой с невалидным CVC")
    void shouldPayWithInvalidCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCvc("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата отклоненной кредитной картой")
    void shouldPayWithDeclinedCard() {
        var cardInfo = new DataHelper().getInValidCardInfo("declined");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyErrorMessage();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }
}