package com.homework.homework.storage.adapters.filesystem.fileoperations;

import com.google.gson.Gson;
import com.homework.homework.entitymanager.condition.ConditionInterface;
import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.entitymanager.exception.NoRecordsFoundException;
import com.homework.homework.storage.adapters.filesystem.results.RowIdSearchResult;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileOperationCommands {

    private String NO_RECORD_FOUND = "Record was not found in the database";

    public static RowIdSearchResult getEntityRowNumber(
        EntityInterface entity,
        ArrayList<String> entities
    ) {
        Gson gson = new Gson();
        RowIdSearchResult result = new RowIdSearchResult(false, 0);
        for (int i = 0; i < entities.size(); i++) {
            EntityInterface entityToCheck = gson.fromJson(entities.get(i), entity.getClass());
            if (entityToCheck.getId() == entity.getId()) {
                result = new RowIdSearchResult(true, i);
                break;
            }
        }

        return result;
    }

    public static long getLastId(
        Class entityClassName,
        ArrayList<String> entities
    ) {
        Gson gson = new Gson();
        long maxNumber = -1;

        for (int i = 0; i < entities.size(); i++) {
            EntityInterface entityToCheck = gson.fromJson(entities.get(i), (Type) entityClassName);
            if (maxNumber < entityToCheck.getId()) {
                maxNumber = entityToCheck.getId();
            }
        }

        return maxNumber;
    }

    public EntityInterface find(
        Class entityClassName,
        ArrayList<ConditionInterface> conditions,
        ArrayList<String> entities
    ) throws ConditionNotExecutableException, NoRecordsFoundException {
        Gson gson = new Gson();

        for (int i = 0; i < entities.size(); i++) {
            EntityInterface entityToCheck = gson.fromJson(entities.get(i), (Type) entityClassName);
            if (this.checkConditions(entityToCheck, conditions)) {
                return entityToCheck;
            }
        }

        throw new NoRecordsFoundException(this.NO_RECORD_FOUND);
    }

    public ArrayList<EntityInterface> findAllByConditions(
        Class entityClassName,
        ArrayList<ConditionInterface> conditions,
        ArrayList<String> entities
    ) throws ConditionNotExecutableException, NoRecordsFoundException {
        Gson gson = new Gson();
        ArrayList<EntityInterface> retrievedList = new ArrayList<>();

        for (int i = 0; i < entities.size(); i++) {
            EntityInterface entityToCheck = gson.fromJson(entities.get(i), (Type) entityClassName);
            try {
                if (this.checkConditions(entityToCheck, conditions)) {
                    retrievedList.add(entityToCheck);
                }
            } catch (ConditionNotExecutableException e) {
                continue;
            }

        }
        if (0 == retrievedList.size()) {
            throw new NoRecordsFoundException(this.NO_RECORD_FOUND);
        }

        return retrievedList;
    }


    private boolean checkConditions(EntityInterface entity, ArrayList<ConditionInterface> conditions) throws ConditionNotExecutableException {
        boolean compliesWithCondition = true;
        for (int i = 0; i < conditions.size(); i++) {
            ConditionInterface condition = conditions.get(i);
            if (!condition.applyCondition(entity)) {
                compliesWithCondition = false;
            }

            if (!compliesWithCondition) {
                break;
            }
        }
        return compliesWithCondition;
    }

}
