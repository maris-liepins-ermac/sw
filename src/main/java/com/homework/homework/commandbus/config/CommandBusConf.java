package com.homework.homework.commandbus.config;

import com.homework.homework.api.fuel.domain.handler.RegisterFuelConsumptionCommandHandler;
import com.homework.homework.commandbus.exception.HandlerNotFoundException;
import com.homework.homework.commandbus.interfaces.CommandHandlerInterface;
import com.homework.homework.commandbus.interfaces.CommandInterface;

public class CommandBusConf {

    public static CommandHandlerInterface matchHandler(CommandInterface command) throws HandlerNotFoundException {
        String className = command.getClass().getSimpleName();
        CommandHandlerInterface handler;

        switch (className) {
            case "RegisterFuelConsumptionCommand":
                handler = new RegisterFuelConsumptionCommandHandler();
                break;
            default:
                throw new HandlerNotFoundException("Handler for command %s could not be found".format(className));
        }

        return handler;
    }
}
