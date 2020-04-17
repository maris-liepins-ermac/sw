package com.homework.homework.api.money.model;

import java.math.BigDecimal;

public class Money {
    private final long amount;
    private final String currency;
    public static final long MONEY_MAX_BOUND = 99999999;
    public static final long MONEY_MIN_BOUND = 0;

    public Money(long amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public long getAmount() {
        return this.amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getFormatted() {
        BigDecimal payment = new BigDecimal(this.amount).movePointLeft(2);
        return String.format("%s %s",payment.toString(), this.currency);
    }

    public static Money add(Money left, Money right) {
        return new Money(left.amount + right.amount, left.getCurrency());
    }
}
