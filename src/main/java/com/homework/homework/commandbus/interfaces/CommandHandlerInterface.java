package com.homework.homework.commandbus.interfaces;

import com.homework.homework.commandbus.exception.CommandExecutionFailedException;

public interface CommandHandlerInterface<C extends CommandInterface> {
    void handle(C command) throws CommandExecutionFailedException;
}
