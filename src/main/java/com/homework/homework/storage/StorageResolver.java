package com.homework.homework.storage;

import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.storage.filesystem.FileSystemStorageAdapter;
import com.homework.homework.storage.interfaces.StorageAdapterInterface;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StorageResolver {

    private final Properties prop;

    public StorageResolver() throws IOException {
        InputStream storageProps = new FileInputStream("src/main/resources/storage.properties");
        this.prop = new Properties();
        prop.load(storageProps);
    }

    public StorageAdapterInterface resolve() throws StorageAdapterNotFoundException {
        String storage = this.prop.getProperty("storage.adapter");
        System.out.println(storage);
        StorageAdapterInterface adapter;
        switch (storage) {
            case "filesystem":
                adapter = new FileSystemStorageAdapter();
                break;
            default:
                throw new StorageAdapterNotFoundException("Storage adapter %s could not be found".format(storage));
        }

        return adapter;
    }
}
