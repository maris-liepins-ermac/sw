package com.homework.homework.entitymanager;

import com.homework.homework.commandbus.exception.CommandExecutionFailedException;
import com.homework.homework.entitymanager.condition.ConditionInterface;
import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.entitymanager.exception.NoRecordsFoundException;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.util.ArrayList;

public interface RepositoryInterface {
    long getLastId();

    EntityInterface find(long id) throws ConditionNotExecutableException, NoRecordsFoundException;

    ArrayList<EntityInterface> findAllByConditions(ArrayList<ConditionInterface> conditions) throws ConditionNotExecutableException, NoRecordsFoundException;
    ArrayList<EntityInterface> findAll() throws NoRecordsFoundException, ConditionNotExecutableException;

}
