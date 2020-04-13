package com.homework.homework.api.fuel.domain.command;

import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.commandbus.interfaces.CommandInterface;

public class RegisterFuelConsumptionCommand implements CommandInterface {
    private final Fuel fuel;

    public RegisterFuelConsumptionCommand(Fuel fuel) {
        this.fuel = fuel;
    }

    public Fuel getFuel() {
        return fuel;
    }
}
