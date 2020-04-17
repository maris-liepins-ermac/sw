package com.homework.homework.api.fuel.service;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.valueobject.FuelConsumptionRecord;
import com.homework.homework.api.fuel.valueobject.FuelGroupedByFuelType;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.util.ArrayList;
import java.util.HashMap;

public class FuelConsumptionByMonthService {
    private HashMap<String, FuelGroupedByFuelType> groupedFuels;

    public FuelConsumptionByMonthService() {
        this.groupedFuels = new HashMap<String, FuelGroupedByFuelType>();
    }

    public HashMap<String, FuelGroupedByFuelType> calculate(ArrayList<EntityInterface> entities) {
        for (EntityInterface entity : entities) {
            Fuel castedEntity = (Fuel) entity;
            if (groupedFuels.containsKey(castedEntity.getFuelType())) {
                this.recalculateGroupObject(castedEntity);
            } else {
                this.calculateGroupObject(castedEntity);
            }
        }
        return this.groupedFuels;
    }

    private void recalculateGroupObject(Fuel entity) {
        FuelGroupedByFuelType groupedFuel = groupedFuels.get(entity.getFuelType());
        Volume totalVolume = Volume.add(groupedFuel.getVolume(), entity.getVolume());
        Money totalAmount = Money.add(groupedFuel.getTotalPrice(), entity.totalCost());
        Long averageAmount = totalAmount.getAmount() / (groupedFuel.getAmountOfObjects() + 1);

        FuelGroupedByFuelType newGroupedFuel = new FuelGroupedByFuelType(
                entity.getFuelType(),
                totalVolume,
                new Money(averageAmount, totalAmount.getCurrency()),
                totalAmount,
                groupedFuel.getAmountOfObjects() + 1
        );

        groupedFuels.replace(entity.getFuelType(), newGroupedFuel);
    }

    private void calculateGroupObject(Fuel entity) {
        FuelGroupedByFuelType groupedFuel = new FuelGroupedByFuelType(
                entity.getFuelType(),
                entity.getVolume(),
                entity.totalCost(),
                entity.totalCost(),
                1
        );

        groupedFuels.put(entity.getFuelType(), groupedFuel);

    }

}
