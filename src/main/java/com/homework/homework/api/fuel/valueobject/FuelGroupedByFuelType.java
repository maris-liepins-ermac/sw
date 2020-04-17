package com.homework.homework.api.fuel.valueobject;

import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.model.Volume;

public class FuelGroupedByFuelType {
    private final String fuelType;
    private final Volume volume;
    private final Money averagePrice;
    private final Money totalPrice;
    private final Integer amountOfObjects;

    public FuelGroupedByFuelType(
            String fuelType,
            Volume volume,
            Money averagePrice,
            Money totalPrice,
            Integer amountOfObjects)
    {
        this.fuelType = fuelType;
        this.volume = volume;
        this.averagePrice = averagePrice;
        this.totalPrice = totalPrice;
        this.amountOfObjects = amountOfObjects;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Volume getVolume() {
        return volume;
    }

    public Money getAveragePrice() {
        return averagePrice;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Integer getAmountOfObjects() {
        return amountOfObjects;
    }
}
