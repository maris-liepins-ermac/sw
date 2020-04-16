package com.homework.homework.api.fuel.entity;

import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.time.LocalDate;

public class Fuel implements EntityInterface {
    private long id = -1;
    private String fuelType;
    private Money pricePerLiter;
    private Volume volume;
    private LocalDate date;
    private long driverId;

    public Fuel(String fuelType, Money pricePerLiter, Volume volume, LocalDate date, long driverId) {
        this.fuelType = fuelType;
        this.pricePerLiter = pricePerLiter;
        this.volume = volume;
        this.date = date;
        this.driverId = driverId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public Money getPricePerLiter() {
        return pricePerLiter;
    }

    public Volume getVolume() {
        return volume;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setPricePerLiter(Money pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getDriverId() {
        return driverId;
    }

    public void setDriverId(long driverId) {
        this.driverId = driverId;
    }

    public Money totalCost() {
        return new Money(
            (this.pricePerLiter.getAmount() * this.volume.getAmount()),
            this.pricePerLiter.getCurrency()
        );
    }
}
