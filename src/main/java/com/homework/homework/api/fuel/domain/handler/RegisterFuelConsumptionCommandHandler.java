package com.homework.homework.api.fuel.domain.handler;

import com.homework.homework.api.fuel.domain.command.RegisterFuelConsumptionCommand;
import com.homework.homework.api.fuel.validation.FuelEntityConstraint;
import com.homework.homework.commandbus.exception.CommandExecutionFailedException;
import com.homework.homework.commandbus.interfaces.CommandHandlerInterface;
import com.homework.homework.commandbus.interfaces.CommandInterface;
import com.homework.homework.entitymanager.EntityManager;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.validation.exception.ValidationFailedException;

import java.io.IOException;

public class RegisterFuelConsumptionCommandHandler implements CommandHandlerInterface {
    private final EntityManager entityManager;
    private final FuelEntityConstraint validator;

    public RegisterFuelConsumptionCommandHandler() throws IOException, StorageAdapterNotFoundException {
        this.entityManager = new EntityManager();
        this.validator = new FuelEntityConstraint();
    }

    @Override
    public void handle(CommandInterface command) throws CommandExecutionFailedException {
        RegisterFuelConsumptionCommand castedCommand = (RegisterFuelConsumptionCommand) command;
        try {
            this.validator.validate(castedCommand.getFuel());
            this.entityManager.persist(castedCommand.getFuel());
        } catch (ValidationFailedException e) {
            throw new CommandExecutionFailedException(e.getMessage());
        }
    }
}
