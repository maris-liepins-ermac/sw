package com.homework.homework.storage.adapters.filesystem;

import com.google.gson.Gson;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.storage.interfaces.RepositoryInterface;

import java.lang.reflect.Type;

public class FileSystemRepository implements RepositoryInterface {

    private final FileSystemStorageAdapter fileSystemStorageAdapter;
    private final Class entityClassName;
    private final Gson gson;

    public FileSystemRepository(Class entityClassName) {
        this.entityClassName = entityClassName;
        this.fileSystemStorageAdapter = new FileSystemStorageAdapter();
        this.fileSystemStorageAdapter.prepareFile(entityClassName);
        this.gson = new Gson();
    }

    @Override
    public long getLastId() {
        if (null == this.fileSystemStorageAdapter) {
            return 0;
        }
        return this.fileSystemStorageAdapter.getLastId(this.entityClassName);
    }
}
