package com.homework.homework.api.fuel.service;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.valueobject.FuelConsumptionRecord;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.util.ArrayList;

public class ConvertFuelToConsumptionRecordService {
    public static ArrayList<FuelConsumptionRecord> convertEntities(
            ArrayList<EntityInterface> entities
    ) {
        ArrayList<FuelConsumptionRecord> consumptionRecords = new ArrayList<>();
        for (EntityInterface entity : entities) {
            Fuel castedEntity = (Fuel) entity;
            consumptionRecords.add(FuelConsumptionRecord.buildFromFuelEntity(castedEntity));
        }
        return consumptionRecords;
    }
}
