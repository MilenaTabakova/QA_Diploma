package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {

    private final SelenideElement debitButton = $("button:nth-child(3)");
    //private final SelenideElement debitButton = $("button");
    private final SelenideElement creditButton = $(byText("Купить в кредит"));

    public DebitPage debitPayment(DataHelper.CardInfo info) {
        debitButton.click();
        return new DebitPage();
    }

    public CreditPage creditPayment(DataHelper.CardInfo info) {
        creditButton.click();
        return new CreditPage();
    }
}