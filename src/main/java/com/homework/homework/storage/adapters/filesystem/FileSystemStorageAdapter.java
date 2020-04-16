package com.homework.homework.storage.adapters.filesystem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.homework.homework.storage.adapters.filesystem.fileoperations.FileOperationCommands;
import com.homework.homework.storage.adapters.filesystem.results.RowIdSearchResult;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.storage.interfaces.StorageAdapterInterface;
import com.homework.homework.storage.interfaces.StorageRepositoryInterface;

import java.util.ArrayList;

public class FileSystemStorageAdapter implements StorageAdapterInterface {

    private final FileSystem fileSystem;
    private final Gson gson;
    private ArrayList<String> entities = new ArrayList<String>();

    public FileSystemStorageAdapter() {
        this.fileSystem = new FileSystem();
        this.gson = new Gson();
    }

    public ArrayList<String> getEntities(Class entity) {
        this.prepareFile(entity);
        return this.entities;
    }

    public void persist(EntityInterface entity) {
        this.prepareFile(entity.getClass());

        if (entity.getId() < 0) {
            entity.setId(FileOperationCommands.getLastId(entity.getClass(), entities) + 1);
        }

        RowIdSearchResult result = FileOperationCommands.getEntityRowNumber(entity, entities);

        if (result.isFound()) {
            this.entities.set((int) result.getRowId(), gson.toJson(entity));
        } else {
            entity.setId(FileOperationCommands.getLastId(entity.getClass(), entities) + 1);
            this.entities.add(gson.toJson(entity));
        }
        this.fileSystem.write(entity.getClass().getSimpleName(), this.gson.toJson(this.entities));
    }

    public void prepareFile(Class className) {
        if (this.fileSystem.fileExists(className.getSimpleName()) && !this.fileSystem.fileIsEmpty(className.getSimpleName())) {
            this.populateEntityRowsFromFile(className.getSimpleName());
        }

        if (!this.fileSystem.fileExists(className.getSimpleName())) {
            this.fileSystem.create(className.getSimpleName());
        }
    }

    public void populateEntityRowsFromFile(String className) {
        String fileContent = this.fileSystem.read(className);
        TypeToken<ArrayList<String>> token = new TypeToken<ArrayList<String>>() {
        };
        this.entities = this.gson.fromJson(fileContent, token.getType());
    }

    public StorageRepositoryInterface getRepository(Class entityClassName) {
        return new FileSystemRepository(entityClassName);
    }
}
