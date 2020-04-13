package com.homework.homework.storage;


import com.homework.homework.api.fuel.domain.handler.RegisterFuelConsumptionCommandHandler;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.storage.interfaces.StorageAdapterInterface;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StorageResolverTest {
    @Test
    public void StorageResolverFindsAdapterTest() {
        try{
            StorageResolver storageResolver = new StorageResolver();
            StorageAdapterInterface storage = storageResolver.resolve();
            assertNotNull(storage, "Storage adapter is not found");
        }
        catch(StorageAdapterNotFoundException | IOException exception)
        {
            fail("Exception not expected.");
        }
        catch(NullPointerException exception)
        {
            fail("Config file was not found at all.");
        }
    }
}
