package com.homework.homework.entitymanager.condition;

import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.storage.interfaces.EntityInterface;

public interface ConditionInterface {
    public boolean applyCondition(EntityInterface entity) throws ConditionNotExecutableException;
}
