package com.homework.homework.storage.adapters.filesystem;

import com.homework.homework.entitymanager.condition.ConditionInterface;
import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.entitymanager.exception.NoRecordsFoundException;
import com.homework.homework.storage.adapters.filesystem.fileoperations.FileOperationCommands;
import com.homework.homework.storage.interfaces.EntityInterface;
import com.homework.homework.storage.interfaces.StorageRepositoryInterface;

import java.util.ArrayList;


public class FileSystemRepository implements StorageRepositoryInterface {

    private final FileSystemStorageAdapter fileSystemStorageAdapter;
    private final Class entityClassName;

    public FileSystemRepository(Class entityClassName) {
        this.entityClassName = entityClassName;
        this.fileSystemStorageAdapter = new FileSystemStorageAdapter();
        this.fileSystemStorageAdapter.prepareFile(entityClassName);
    }

    @Override
    public long getLastId() {
        return FileOperationCommands.getLastId(this.entityClassName, this.fileSystemStorageAdapter.getEntities(entityClassName));
    }

    @Override
    public EntityInterface find(
        ArrayList<ConditionInterface> conditions
    ) throws ConditionNotExecutableException, NoRecordsFoundException {
        FileOperationCommands commands = new FileOperationCommands();
        return commands.find(entityClassName, conditions, this.fileSystemStorageAdapter.getEntities(entityClassName));
    }

    @Override
    public ArrayList<EntityInterface> findAllByConditions(
        ArrayList<ConditionInterface> conditions
    ) throws ConditionNotExecutableException, NoRecordsFoundException {
        FileOperationCommands commands = new FileOperationCommands();
        return commands.findAllByConditions(entityClassName, conditions, this.fileSystemStorageAdapter.getEntities(entityClassName));
    }

}
