package com.homework.homework.api.volume.model;

import com.homework.homework.api.money.model.Money;

import java.math.BigDecimal;

public class Volume {
    private final long amount;
    private final String unitOfMeasurement;

    public static final long VOLUME_MAX_BOUND = 99999999;
    public static final long VOLUME_MIN_BOUND = 0;

    public Volume(long amount, String unitOfMeasurement) {
        this.amount = amount;
        this.unitOfMeasurement = unitOfMeasurement;
    }

    public long getAmount() {
        return this.amount;
    }

    public String getUnitOfMeasurement() {
        return this.unitOfMeasurement;
    }

    public String getFormatted() {
        BigDecimal payment = new BigDecimal(this.amount).movePointLeft(1);
        return String.format("%s %s", payment.toString(), this.unitOfMeasurement);
    }

    public static Volume add(Volume left, Volume right) {
        return new Volume(left.amount + right.amount, left.getUnitOfMeasurement());
    }
}
