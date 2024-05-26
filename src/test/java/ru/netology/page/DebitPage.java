package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {

    private final SelenideElement debitHead = $(byText("Оплата по карте"));
    private final SelenideElement cardsNumberInput = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthInput = $("[placeholder='08']");
    private final SelenideElement yearInput = $("[placeholder='22']");
    private ElementsCollection input = $$(".input__control");
    private final SelenideElement nameInput = input.get(3);
    private final SelenideElement cvcInput = $("[placeholder='999']");
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement successMessage = $(".notification_status_ok");
    private final SelenideElement errorMessage = $(".notification_status_error");
    private final SelenideElement invalidFormat = $(".input_invalid .input__sub");
    private final SelenideElement errorMessageForInvalidMonth = $(".input-group__input-case_invalid .input__sub");
    private final SelenideElement errorMessageForInvalidYear = $(byText("Истёк срок действия карты"));
    private final SelenideElement errorMessageForNullName = $(byText("Поле обязательно для заполнения"));

    public DebitPage() {
        debitHead.shouldBe(visible);
    }

    public void enterCardData(DataHelper.CardInfo cardInfo) {
        cardsNumberInput.setValue(cardInfo.getCardNumber());
        monthInput.setValue(cardInfo.getMonth());
        yearInput.setValue(cardInfo.getYear());
        nameInput.setValue(cardInfo.getName());
        cvcInput.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    public void verifySuccessMessage() {
        successMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyErrorMessage() {
        errorMessage.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyInvalidFormatMonth() {
        errorMessageForInvalidMonth.shouldBe(visible);
    }

    public void verifyInvalidFormatYear() {
        errorMessageForInvalidYear.shouldBe(visible);
    }

    public void verifyNullName() {
        errorMessageForNullName.shouldBe(visible);
    }

    public void verifyInvalidFormat() {
        invalidFormat.shouldBe(visible);
    }
}