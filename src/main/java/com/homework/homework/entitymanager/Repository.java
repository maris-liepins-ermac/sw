package com.homework.homework.entitymanager;

import com.homework.homework.entitymanager.condition.ConditionInterface;
import com.homework.homework.entitymanager.condition.LongCondition;
import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.entitymanager.exception.NoRecordsFoundException;
import com.homework.homework.storage.StorageResolver;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.storage.interfaces.StorageAdapterInterface;
import com.homework.homework.storage.interfaces.StorageRepositoryInterface;

import java.io.IOException;
import java.util.ArrayList;

public class Repository implements RepositoryInterface {
    private StorageRepositoryInterface repository;

    public Repository(Class entityClassName) throws IOException, StorageAdapterNotFoundException {
        StorageResolver resolver = new StorageResolver();
        StorageAdapterInterface storageAdapter = resolver.resolve();
        this.repository = storageAdapter.getRepository(entityClassName);
    }

    @Override
    public long getLastId() {
        return this.repository.getLastId();
    }

    @Override
    public EntityInterface find(long id) throws ConditionNotExecutableException, NoRecordsFoundException {

        LongCondition entityIdCondition = new LongCondition(
            id,
            "valueMatches",
            "id"
        );
        ArrayList<ConditionInterface> conditions = new ArrayList<ConditionInterface>();
        conditions.add(entityIdCondition);


        return this.repository.find(conditions);
    }

    @Override
    public ArrayList<EntityInterface> findAllByConditions(
        ArrayList<ConditionInterface> conditions
    ) throws ConditionNotExecutableException, NoRecordsFoundException {
        return this.repository.findAllByConditions(conditions);
    }

    @Override
    public ArrayList<EntityInterface> findAll() throws NoRecordsFoundException, ConditionNotExecutableException {
        ArrayList<ConditionInterface> conditions = new ArrayList<>();
        return this.repository.findAllByConditions(conditions);
    }

}
