package com.homework.homework.api.fuel.entity;

import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.util.Date;

public class Fuel implements EntityInterface {
    private long id;
    private String fuelType;
    private Money pricePerLiter;
    private Volume volume;
    private Date date;

    public Fuel( String fuelType, Money pricePerLiter, Volume volume, Date date) {
        this.fuelType = fuelType;
        this.pricePerLiter = pricePerLiter;
        this.volume = volume;
        this.date = date;
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

    public Date getDate() {
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

    public void setDate(Date date) {
        this.date = date;
    }
}
