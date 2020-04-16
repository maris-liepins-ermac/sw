package com.homework.homework.entitymanager.condition;

import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.storage.interfaces.EntityInterface;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LongCondition implements ConditionInterface {

    private Long value;
    private String paramToCompareTo;
    private Method method;

    public LongCondition(Long value, String methodToUse, String paramToCompareTo)
        throws ConditionNotExecutableException {
        this.value = value;
        this.paramToCompareTo = paramToCompareTo;
        Class<?> parameterTypes = EntityInterface.class;
        try {
            this.method = this.getClass().getDeclaredMethod(methodToUse, parameterTypes);
        } catch (NoSuchMethodException e) {
            throw new ConditionNotExecutableException(e.getMessage());
        }

    }

    public boolean valueMatches(EntityInterface entity) throws ConditionNotExecutableException {
        try {
            Field field = entity.getClass().getDeclaredField(this.paramToCompareTo);
            field.setAccessible(true);
            Object value = field.get(entity);
            return value.equals(this.value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ConditionNotExecutableException(e.getMessage());
        }
    }

    @Override
    public boolean applyCondition(EntityInterface entity) throws ConditionNotExecutableException {
        try {
            return (boolean) this.method.invoke(this, entity);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new ConditionNotExecutableException(e.getMessage());
        }
    }
}
