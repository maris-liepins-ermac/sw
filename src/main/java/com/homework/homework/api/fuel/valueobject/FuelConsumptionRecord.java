package com.homework.homework.api.fuel.valueobject;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.model.Volume;

import java.time.LocalDate;

public class FuelConsumptionRecord {
    private final String fuelType;
    private final Volume volume;
    private final LocalDate date;
    private final Money pricePerLiter;
    private final Money totalPrice;
    private final long driverId;

    public FuelConsumptionRecord(
            String fuelType,
            Volume volume,
            LocalDate date,
            Money pricePerLiter,
            Money totalPrice,
            long driverId
    )
    {
        this.fuelType = fuelType;
        this.volume = volume;
        this.date = date;
        this.pricePerLiter = pricePerLiter;
        this.totalPrice = totalPrice;
        this.driverId = driverId;
    }


    public static FuelConsumptionRecord buildFromFuelEntity(Fuel entity)
    {
        return new FuelConsumptionRecord(
                entity.getFuelType(),
                entity.getVolume(),
                entity.getDate(),
                entity.getPricePerLiter(),
                entity.totalCost(),
                entity.getDriverId()
        );
    }

    public String getFuelType() {
        return fuelType;
    }

    public Volume getVolume() {
        return volume;
    }

    public Money getPricePerLiter() {
        return pricePerLiter;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public long getDriverId() {
        return driverId;
    }

    public LocalDate getDate() {
        return date;
    }
}
