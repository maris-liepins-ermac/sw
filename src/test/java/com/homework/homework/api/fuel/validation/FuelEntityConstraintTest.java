package com.homework.homework.api.fuel.validation;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.enums.FuelTypeEnum;
import com.homework.homework.api.money.enums.CurrencyEnum;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.enums.VolumeTypeEnum;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.validation.exception.ValidationFailedException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FuelEntityConstraintTest {

    private final FuelEntityConstraint fuelEntityConstraint;

    public FuelEntityConstraintTest() {
        this.fuelEntityConstraint = new FuelEntityConstraint();
    }

    @Test
    public void validFuelEntityTest() {

        Money pricePerLiter = new Money(123, CurrencyEnum.EUR);
        Volume volume = new Volume(123, VolumeTypeEnum.LITER);
        LocalDate date = LocalDate.now();

        Fuel fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
        } catch (ValidationFailedException exception) {
            fail("Exception not expected here");
        }
    }

    @Test
    public void wrongFuelTypeTest() {

        Money pricePerLiter = new Money(123, CurrencyEnum.EUR);
        Volume volume = new Volume(123, VolumeTypeEnum.LITER);
        LocalDate date = LocalDate.now();

        Fuel fuel = new Fuel(
            "ImSoWrong",
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Invalid fuel type.");
        }
    }

    @Test
    public void notValidPricePerLiterTest() {

        Volume volume = new Volume(123, VolumeTypeEnum.LITER);
        LocalDate date = LocalDate.now();

        Fuel fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            null,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Record without price.");
        }

        Money pricePerLiter = new Money(123, "test");
        fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Invalid currency.");
        }

        pricePerLiter = new Money(Money.MONEY_MIN_BOUND - 1, CurrencyEnum.EUR);
        fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Amount is bellow the allowed amount boundary.");
        }

        pricePerLiter = new Money(Money.MONEY_MAX_BOUND + 1, CurrencyEnum.EUR);
        fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Amount is above the allowed amount boundary.");
        }

    }

    @Test
    public void notValidVolumeTest() {

        Money pricePerLiter = new Money(123, CurrencyEnum.EUR);
        LocalDate date = LocalDate.now();

        Fuel fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            null,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Record without volume.");
        }

        Volume volume = new Volume(123, "im wrong");
        fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Invalid volume type.");
        }

        volume = new Volume(Volume.VOLUME_MIN_BOUND - 1, VolumeTypeEnum.LITER);
        fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Amount is bellow the allowed amount boundary.");
        }

        volume = new Volume(Volume.VOLUME_MAX_BOUND + 1, VolumeTypeEnum.LITER);
        fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            12345
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Amount is above the allowed amount boundary.");
        }

    }

    @Test
    public void notValidDriverIdTest() {

        Money pricePerLiter = new Money(123, CurrencyEnum.EUR);
        Volume volume = new Volume(123, VolumeTypeEnum.LITER);
        LocalDate date = LocalDate.now();

        Fuel fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            -1
        );

        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Negative driver id.");
        }

        fuel = new Fuel(
            FuelTypeEnum.TYPE_95,
            pricePerLiter,
            volume,
            date,
            999999 + 1
        );
        try {
            this.fuelEntityConstraint.validate(fuel);
            fail("Exception expected.");
        } catch (ValidationFailedException exception) {
            assertEquals(exception.getMessage(), "Driver id out of bounds.");
        }
    }
}
