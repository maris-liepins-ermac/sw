package com.homework.homework.storage.adapters.filesystem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.homework.homework.storage.adapters.filesystem.results.RowIdSearchResult;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.storage.interfaces.RepositoryInterface;
import com.homework.homework.storage.interfaces.StorageAdapterInterface;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FileSystemStorageAdapter implements StorageAdapterInterface {

    private final FileSystem fileSystem;
    private final Gson gson;
    public ArrayList<String> entities = new ArrayList<String>();

    public FileSystemStorageAdapter() {
        this.fileSystem = new FileSystem();
        this.gson = new Gson();
    }

    public void persist(EntityInterface entity) {
        this.prepareFile(entity.getClass());
        System.out.println(entity.getId());
        RowIdSearchResult result = this.getEntityRowNumber(entity);
        if (result.isFound()) {
            this.entities.set((int) result.getRowId(), gson.toJson(entity));
        } else {
            entity.setId(this.getLastId(entity.getClass()) + 1);
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

    public RepositoryInterface getRepository(Class entityClassName) {
        return new FileSystemRepository(entityClassName);
    }

    public RowIdSearchResult getEntityRowNumber(EntityInterface entity) {
        RowIdSearchResult result = new RowIdSearchResult(false, 0);
        for (int i = 0; i < this.entities.size(); i++) {
            EntityInterface entityToCheck = gson.fromJson(this.entities.get(i), entity.getClass());
            if (entityToCheck.getId() == entity.getId()) {
                result = new RowIdSearchResult(true, i);
                break;
            }
        }

        return result;
    }

    public long getLastId(Class entityClassName) {
        long maxNumber = -1;

        for (int i = 0; i < this.entities.size(); i++) {
            EntityInterface entityToCheck = gson.fromJson(this.entities.get(i), (Type) entityClassName);
            if (maxNumber < entityToCheck.getId()) {
                maxNumber = entityToCheck.getId();
            }
        }

        return maxNumber;
    }
}
