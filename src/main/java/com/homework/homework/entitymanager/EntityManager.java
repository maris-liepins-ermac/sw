package com.homework.homework.entitymanager;

import com.homework.homework.storage.StorageResolver;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.storage.interfaces.StorageAdapterInterface;

import java.io.IOException;

public class EntityManager {
    private final StorageAdapterInterface storageAdapter;

    public EntityManager() throws IOException, StorageAdapterNotFoundException {
        StorageResolver resolver = new StorageResolver();
        this.storageAdapter = resolver.resolve();
    }

    public void persist(EntityInterface entity) {
        this.storageAdapter.persist(entity);
    }
}
