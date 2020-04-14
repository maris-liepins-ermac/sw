package com.homework.homework.entitymanager;

import com.homework.homework.storage.StorageResolver;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.storage.interfaces.RepositoryInterface;
import com.homework.homework.storage.interfaces.StorageAdapterInterface;

import java.io.IOException;

public class Repository implements RepositoryInterface{
    private final StorageAdapterInterface storageAdapter;
    private final Class entityClassName;

    public Repository(Class entityClassName) throws IOException, StorageAdapterNotFoundException {
        StorageResolver resolver = new StorageResolver();
        this.storageAdapter = resolver.resolve();
        this.entityClassName = entityClassName;
    }

    @Override
    public long getLastId() {
        RepositoryInterface repository = this.storageAdapter.getRepository(this.entityClassName);
        return repository.getLastId();
    }
}
