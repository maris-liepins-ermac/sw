package com.homework.homework.api.fuel.domain.handler;

import com.homework.homework.api.fuel.domain.command.RegisterFuelConsumptionCommand;
import com.homework.homework.commandbus.interfaces.CommandHandlerInterface;
import com.homework.homework.commandbus.interfaces.CommandInterface;
import com.homework.homework.entitymanager.EntityManager;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;

import java.io.IOException;

public class RegisterFuelConsumptionCommandHandler implements CommandHandlerInterface {
    private final EntityManager entityManager;

    public RegisterFuelConsumptionCommandHandler() throws IOException, StorageAdapterNotFoundException {
        this.entityManager = new EntityManager();
    }

    @Override
    public void handle(CommandInterface command) {
        RegisterFuelConsumptionCommand castedCommand = (RegisterFuelConsumptionCommand) command;
        this.entityManager.persist(castedCommand.getFuel());
    }
}
