package com.homework.homework.storage.interfaces;

import com.homework.homework.entitymanager.condition.ConditionInterface;
import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.entitymanager.exception.NoRecordsFoundException;

import java.util.ArrayList;

public interface StorageRepositoryInterface {
    long getLastId();
    EntityInterface find(ArrayList<ConditionInterface> conditions) throws ConditionNotExecutableException, NoRecordsFoundException;
    ArrayList<EntityInterface> findAllByConditions(ArrayList<ConditionInterface> conditions) throws ConditionNotExecutableException, NoRecordsFoundException;

}
