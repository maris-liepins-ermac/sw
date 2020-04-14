package com.homework.homework.storage.interfaces;

import java.util.HashMap;

public interface StorageAdapterInterface {
    void persist(EntityInterface entity);
    RepositoryInterface getRepository(Class entityClassName);
}
