package com.homework.homework.api.money.enums;

public class CurrencyEnum {
    public static final String EUR = "EUR";

    public static boolean isPartOfEnum(String value) {
        return (
            EUR.equals(value)
        );
    }
}
