package com.homework.homework.storage.interfaces;

import java.util.HashMap;

public interface StorageAdapterInterface {
    void persist(EntityInterface entity);
    StorageRepositoryInterface getRepository(Class entityClassName);
}
