package com.homework.homework.commandbus.config;

import com.homework.homework.api.fuel.domain.command.RegisterFuelConsumptionCommand;
import com.homework.homework.api.fuel.domain.handler.RegisterFuelConsumptionCommandHandler;
import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.enums.FuelTypeEnum;
import com.homework.homework.api.money.enums.CurrencyEnum;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.volume.model.Volume;
import com.homework.homework.commandbus.exception.HandlerNotFoundException;
import com.homework.homework.commandbus.interfaces.CommandHandlerInterface;
import com.homework.homework.commandbus.interfaces.CommandInterface;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CommandBusConfTest {

    private Fuel generateFuelObject()
    {
        Money pricePerLiter = new Money(123, CurrencyEnum.EUR);
        Volume volume = new Volume(123, FuelTypeEnum.TYPE_95);
        LocalDate date =  LocalDate.now();

        Fuel fuel = new Fuel(
            "FuelType",
            pricePerLiter,
            volume,
            date,
            12345
        );
        return fuel;
    }

    @Test
    public void RegisterFuelConsumptionCommandHasHandlerTest() {
        Fuel fuel = this.generateFuelObject();
        CommandInterface command = new RegisterFuelConsumptionCommand(fuel);
        try {
            CommandHandlerInterface handler = CommandBusConf.matchHandler(command);
            assertTrue(handler instanceof RegisterFuelConsumptionCommandHandler, "Found handler should be instance of RegisterFuelConsumptionCommandHandler");
        } catch (HandlerNotFoundException | IOException | StorageAdapterNotFoundException exception) {
            fail("Exception not expected.");
        }
    }
}
