package com.homework.homework.commandbus;

import com.homework.homework.commandbus.config.CommandBusConf;
import com.homework.homework.commandbus.exception.CommandExecutionFailedException;
import com.homework.homework.commandbus.interfaces.CommandHandlerInterface;
import com.homework.homework.commandbus.interfaces.CommandInterface;

public class CommandBus {

    public void handle(CommandInterface command) throws CommandExecutionFailedException {
        try {
            CommandHandlerInterface handler = CommandBusConf.matchHandler(command);
            handler.handle(command);
        } catch (Exception exception) {
            throw new CommandExecutionFailedException(exception.getMessage());
        }
    }
}
