package com.homework.homework.api.volume.model;

import java.math.BigDecimal;

public class Volume {
    private final long amount;
    private final String unitOfMeasurement;

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
        return String.format(payment.toString(), this.unitOfMeasurement);
    }
}
