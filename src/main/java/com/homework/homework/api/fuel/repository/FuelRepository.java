package com.homework.homework.api.fuel.repository;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.valueobject.MonthAndDriverValueObject;
import com.homework.homework.entitymanager.Repository;
import com.homework.homework.entitymanager.condition.ConditionInterface;
import com.homework.homework.entitymanager.condition.DateCondition;
import com.homework.homework.entitymanager.condition.LongCondition;
import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.entitymanager.exception.NoRecordsFoundException;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FuelRepository {
    private final Repository repository;

    public FuelRepository() throws IOException, StorageAdapterNotFoundException {
        this.repository = new Repository(Fuel.class);
    }

    public ArrayList<EntityInterface> getRecordsByDriver(long driverId)
        throws ConditionNotExecutableException, NoRecordsFoundException {
        LongCondition driverIdCondition = new LongCondition(driverId, "valueMatches", "driverId");
        ArrayList<ConditionInterface> conditions = new ArrayList<>();
        conditions.add(driverIdCondition);
        return this.repository.findAllByConditions(conditions);
    }

    public ArrayList<EntityInterface> getRecordsByMonths(MonthAndDriverValueObject value)
        throws ConditionNotExecutableException, NoRecordsFoundException {
        ArrayList<ConditionInterface> conditions = new ArrayList<>();

        if (0 <= value.getDriverId()) {
            LongCondition driverIdCondition = new LongCondition(
                value.getDriverId(),
                "valueMatches",
                "driverId");
            conditions.add(driverIdCondition);
        }

        LocalDate dateToCompare = LocalDate.of(value.getYear(), value.getMonth(), 1);
        DateCondition dateIsPartOfMonth = new DateCondition(
            dateToCompare,
            "dateHasSameMonth",
            "date"
        );
        conditions.add(dateIsPartOfMonth);

        return this.repository.findAllByConditions(conditions);
    }
}
