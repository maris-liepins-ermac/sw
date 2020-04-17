package com.homework.homework.api.money.model;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.enums.FuelTypeEnum;
import com.homework.homework.api.money.enums.CurrencyEnum;
import com.homework.homework.api.volume.enums.VolumeTypeEnum;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.validation.exception.ValidationFailedException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {
    @Test
    public void addMoneyValidTest() {
        Money left = new Money(123, CurrencyEnum.EUR);
        Money right = new Money(100, CurrencyEnum.EUR);

        Money result = Money.add(left, right);
        assertEquals(result.getAmount(), 223);
    }
}