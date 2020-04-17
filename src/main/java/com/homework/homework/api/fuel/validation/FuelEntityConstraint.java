package com.homework.homework.api.fuel.validation;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.enums.FuelTypeEnum;
import com.homework.homework.api.money.enums.CurrencyEnum;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.enums.VolumeTypeEnum;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.validation.ConstraintInterface;
import com.homework.homework.validation.exception.ValidationFailedException;

import java.util.Objects;

public class FuelEntityConstraint implements ConstraintInterface {
    public void validate(EntityInterface entity) throws ValidationFailedException {
        this.validateInstance(entity);

        Fuel castedEntity = (Fuel) entity;
        this.validateFuelType(castedEntity);
        this.validatePricePerLiter(castedEntity);
        this.validateVolume(castedEntity);
        this.validateDate(castedEntity);
        this.validateDriverId(castedEntity);
    }

    private void validateInstance(EntityInterface entity) throws ValidationFailedException {
        if (!(entity instanceof Fuel)) {
            throw new ValidationFailedException("Object must be instance of Fuel.");
        }
    }

    private void validateFuelType(Fuel entity) throws ValidationFailedException {
        if (!FuelTypeEnum.isPartOfEnum(entity.getFuelType())) {
            throw new ValidationFailedException("Invalid fuel type.");
        }
    }

    private void validatePricePerLiter(Fuel entity) throws ValidationFailedException {
        if (entity.getPricePerLiter() == null) {
            throw new ValidationFailedException("Record without price.");
        }

        if (!CurrencyEnum.isPartOfEnum(entity.getPricePerLiter().getCurrency())) {
            throw new ValidationFailedException("Invalid currency.");
        }

        if (entity.getPricePerLiter().getAmount() < Money.MONEY_MIN_BOUND) {
            throw new ValidationFailedException("Amount is bellow the allowed amount boundary.");
        }

        if (entity.getPricePerLiter().getAmount() > Money.MONEY_MAX_BOUND) {
            throw new ValidationFailedException("Amount is above the allowed amount boundary.");
        }
    }

    private void validateVolume(Fuel entity) throws ValidationFailedException {
        if (entity.getVolume() == null) {
            throw new ValidationFailedException("Record without volume.");
        }

        if (!VolumeTypeEnum.isPartOfEnum(entity.getVolume().getUnitOfMeasurement())) {
            throw new ValidationFailedException("Invalid volume type.");
        }

        if (entity.getVolume().getAmount() < Volume.VOLUME_MIN_BOUND) {
            throw new ValidationFailedException("Amount is bellow the allowed amount boundary.");
        }

        if (entity.getVolume().getAmount() > Volume.VOLUME_MAX_BOUND) {
            throw new ValidationFailedException("Amount is above the allowed amount boundary.");
        }
    }

    private void validateDriverId(Fuel entity) throws ValidationFailedException {
        if (entity.getDriverId() < 1) {
            throw new ValidationFailedException("Driver id out of bounds or not set.");
        }

        if (entity.getDriverId() > 999999) {
            throw new ValidationFailedException("Driver id out of bounds.");
        }
    }

    private void validateDate(Fuel entity) throws ValidationFailedException {
        if(null == entity.getDate()){
            throw new ValidationFailedException("Date is not set.");
        }
    }
}
