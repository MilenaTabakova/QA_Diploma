package ru.netology.data;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    private static final Faker faker = new Faker(new Locale("en"));
    private static String getCardNumberApproved(String card) {
        if (card.equalsIgnoreCase("approved")) {
            return "4444 4444 4444 4441";
        } else return card;
    }
    private static String getCardNumberDeclined(String card) {
        if (card.equalsIgnoreCase("declined")) {
            return "4444 4444 4444 4442";
        } else return card;
    }
    private static String getMonth(int qMonth) {
        var format = DateTimeFormatter.ofPattern("MM");
        var month = LocalDate.now().plusMonths(qMonth).format(format);
        return month;
    }

    private static String getYear(int qYear) {
        var format = DateTimeFormatter.ofPattern("yy");
        var year = LocalDate.now().plusYears(qYear).format(format);
        return year;
    }

    private static String generateRandomName(String locale) {
        var faker = new Faker(new Locale(locale));
        var name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    private static String generateRandomCvc() {
        var cvc = Integer.toString(faker.number().numberBetween(100, 999));
        return cvc;
    }

    public CardInfo getValidCardInfo(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public CardInfo getInValidCardInfo(String card) {
        return new CardInfo(
                getCardNumberDeclined(card),
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                generateRandomCvc());
    }
    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String name;
        String cvc;
    }

    private static String generateInvalidCardNumber() {
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en"), new RandomService());
        var cardNumber = fakeValuesService.numerify("1234 4567 7891 2345");
        return cardNumber;
    }

    private static String generateInvalidMonth() {
        var month = Integer.toString(faker.number().numberBetween(13, 99));
        return month;
    }

    private static String generateOneNumber() {
        var oneNumber = Integer.toString(faker.number().numberBetween(0, 9));
        return oneNumber;
    }

    private static String generateInvalidYear() {
        var year = Integer.toString(faker.number().numberBetween(10, 22));
        return year;
    }

    public static CardInfo getInvalidCardInfoWithEmptyCardNumber() {
        return new CardInfo(
                " ",
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithEmptyMonth(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                " ",
                getYear(2),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithEmptyYear(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                " ",
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithEmptyName(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                getYear(2),
                " ",
                generateRandomCvc());
    }
    public static CardInfo getInvalidCardInfoWithNullCardNumber() {
        return new CardInfo(
                "0000 0000 0000 0000",
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                generateRandomCvc());
    }
    public static CardInfo getInvalidCardInfoWithNumberAndSymBolName(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                getYear(2),
                "777@#$%&",
                generateRandomCvc());
    }
    public static CardInfo getInvalidCardInfoWithEmptyCvc(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                " ");
    }
    public static CardInfo getInvalidCardInfoWithNullCvc(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                "000");
    }
    public static CardInfo getInvalidCardInfoWithInvalidCardNumber() {
        return new CardInfo(
                generateInvalidCardNumber(),
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithInvalidMonth(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                generateInvalidMonth(),
                getYear(2),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithInvalidYear(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                generateInvalidYear(),
                generateRandomName("en"),
                generateRandomCvc());
    }

    public static CardInfo getInvalidCardInfoWithInvalidCvc(String card) {
        return new CardInfo(
                getCardNumberApproved(card),
                getMonth(5),
                getYear(2),
                generateRandomName("en"),
                generateOneNumber());
    }
}