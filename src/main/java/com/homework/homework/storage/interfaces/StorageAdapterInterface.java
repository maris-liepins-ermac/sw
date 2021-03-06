package com.homework.homework.storage.interfaces;

public interface StorageAdapterInterface {
    void persist(EntityInterface entity);

    StorageRepositoryInterface getRepository(Class entityClassName);
}
