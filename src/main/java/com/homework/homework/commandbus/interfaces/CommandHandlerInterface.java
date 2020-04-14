package com.homework.homework.commandbus.interfaces;

import com.homework.homework.api.fuel.domain.command.RegisterFuelConsumptionCommand;

public interface CommandHandlerInterface<C extends CommandInterface> {
    void handle(C command);
}
