package com.homework.homework.api.fuel.service;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.money.enums.CurrencyEnum;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.util.ArrayList;

public class CalculateTotalSpentAmountService {

    public static Money calculate(ArrayList<EntityInterface> entities) {
        Money totalAmount = new Money(0, CurrencyEnum.EUR);
        for (EntityInterface entity : entities) {
            Fuel castedEntity = (Fuel) entity;
            totalAmount = Money.add(totalAmount, castedEntity.totalCost());
        }

        return totalAmount;
    }
}
