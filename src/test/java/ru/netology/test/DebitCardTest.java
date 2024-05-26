package ru.netology.test;

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

public class DebitCardTest {
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
    @DisplayName("Успешная оплата дебетовой картой с валидными данными")
    void shouldSuccessfulPayWithDebitCard() {
        var cardInfo = new DataHelper().getValidCardInfo("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifySuccessMessage();
        assertEquals("APPROVED", SQLHelper.getDebitStatus());
    }

    @Test
    @DisplayName("Оплата дебетовой картой с пустым номером карты")
    void shouldPayWithEmptyCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCardNumber();
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с 0 номером карты")
    void shouldPayWithZeroCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNullCardNumber();
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с пустым месяцем")
    void shouldPayWithEmptyMonth() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyMonth("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormatMonth();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с пустым годом")
    void shouldPayWithEmptyYear() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyYear("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с пустым именем владельца")
    void shouldPayWithEmptyName() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyName("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyNullName();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с невалидным именем владельца")
    void shouldPayWithInvalidName() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNumberAndSymBolName("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyNullName();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с пустым CVC")
    void shouldPayWithEmptyCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithEmptyCvc("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с нулевым CVC")
    void shouldPayWithZeroCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithNullCvc("approved");
        var creditPage = paymentPage.creditPayment(cardInfo);
        creditPage.enterCardData(cardInfo);
        creditPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с невалидным номером карты")
    void shouldPayWithInvalidCardNumber() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCardNumber();
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с невалидным месяцем")
    void shouldPayWithInvalidMonth() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidMonth("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormatMonth();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с невалидным годом")
    void shouldPayWithInvalidYear() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidYear("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormatYear();
    }

    @Test
    @DisplayName("Оплата дебетовой картой с невалидным CVC")
    void shouldPayWithInvalidCvc() {
        var cardInfo = DataHelper.getInvalidCardInfoWithInvalidCvc("approved");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyInvalidFormat();
    }

    @Test
    @DisplayName("Оплата отклоненной дебетовой картой")
    void shouldPayWithDeclinedCard() {
        var cardInfo = new DataHelper().getInValidCardInfo("declined");
        var debitPage = paymentPage.debitPayment(cardInfo);
        debitPage.enterCardData(cardInfo);
        debitPage.verifyErrorMessage();
        assertEquals("DECLINED", SQLHelper.getDebitStatus());
    }
}