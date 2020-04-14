package com.homework.homework.api.driver.entity;

import com.homework.homework.storage.interfaces.EntityInterface;

public class Driver implements EntityInterface {
    private long id;
    private final String name;
    private final String surname;

    public Driver(long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
