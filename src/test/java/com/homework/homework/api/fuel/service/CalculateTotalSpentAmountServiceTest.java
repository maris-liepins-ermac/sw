package com.homework.homework.api.fuel.service;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.enums.FuelTypeEnum;
import com.homework.homework.api.money.enums.CurrencyEnum;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.enums.VolumeTypeEnum;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.storage.interfaces.EntityInterface;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CalculateTotalSpentAmountServiceTest {

    private final long moneyAmount = 123;
    private final long volumeAmount = 123;

    private long totalAmount;


    public ArrayList<EntityInterface> generateEntities()
    {
        ArrayList<EntityInterface> entities = new  ArrayList<>();

        Money pricePerLiter = new Money(moneyAmount, CurrencyEnum.EUR);
        Volume volume = new Volume(volumeAmount, VolumeTypeEnum.LITER);
        LocalDate date =  LocalDate.now();

        Fuel fuel1 = new Fuel(
                FuelTypeEnum.TYPE_95,
                pricePerLiter,
                volume,
                date,
                12345
        );

        Fuel fuel2 = new Fuel(
                FuelTypeEnum.TYPE_95,
                pricePerLiter,
                volume,
                date,
                12346
        );

        entities.add(fuel1);
        entities.add(fuel2);

        this.totalAmount = Money.add(fuel1.totalCost(), fuel2.totalCost()).getAmount();

        return entities;
    }

    @Test
    public void calculateValidEntitiesTest() {
        ArrayList<EntityInterface> entities = this.generateEntities();
        Money calculatedResult = CalculateTotalSpentAmountService.calculate(entities);
        assertEquals(calculatedResult.getAmount(), this.totalAmount);

    }
}