package com.homework.homework.api;

import com.homework.homework.api.fuel.domain.command.RegisterFuelConsumptionCommand;
import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.repository.FuelRepository;
import com.homework.homework.api.fuel.service.CalculateTotalSpentAmountService;
import com.homework.homework.api.fuel.service.ConvertFuelToConsumptionRecordService;
import com.homework.homework.api.fuel.service.FuelConsumptionByMonthService;
import com.homework.homework.api.fuel.valueobject.FuelConsumptionRecord;
import com.homework.homework.api.fuel.valueobject.FuelGroupedByFuelType;
import com.homework.homework.api.fuel.valueobject.MonthAndDriverValueObject;
import com.homework.homework.api.money.model.Money;
import com.homework.homework.api.response.*;
import com.homework.homework.commandbus.CommandBus;
import com.homework.homework.commandbus.exception.CommandExecutionFailedException;
import com.homework.homework.entitymanager.Repository;
import com.homework.homework.entitymanager.RepositoryInterface;
import com.homework.homework.entitymanager.condition.exception.ConditionNotExecutableException;
import com.homework.homework.entitymanager.exception.NoRecordsFoundException;
import com.homework.homework.storage.exeption.StorageAdapterNotFoundException;
import com.homework.homework.storage.interfaces.EntityInterface;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class FuelConsumptionController {

    private FuelRepository fuelRepository;
    private RepositoryInterface repository = null;

    public FuelConsumptionController() {
        try {
            this.repository = new Repository(Fuel.class);
            this.fuelRepository = new FuelRepository();
        } catch (IOException | StorageAdapterNotFoundException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/reports/find")
    public ApiResponseInterface all() {

        ArrayList<EntityInterface> entities = null;

        try {
            entities = this.repository.findAll();
        } catch (ConditionNotExecutableException | NoRecordsFoundException e) {
            return new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), "/reports/find");
        }

        return new ApiSuccessResponseWithObject<ArrayList<EntityInterface>>(
                HttpStatus.OK,
                "/reports/find",
                entities
        );
    }

    @GetMapping("/reports/find/{id}")
    ApiResponseInterface one(@PathVariable Long id) {

        Fuel entity = null;

        try {
            entity = (Fuel) this.repository.find(id);
        } catch (ConditionNotExecutableException | NoRecordsFoundException e) {
            return new ApiErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage(), "/reports/find/{id}");
        }

        return new ApiSuccessResponseWithObject<Fuel>(
                HttpStatus.OK,
                "/reports/find/{id}",
                entity
        );
    }

    @PostMapping(value = "/reports/add", consumes = "application/json", produces = "application/json")
    public ApiResponseInterface registerConsumptionRecord(@Valid @RequestBody Fuel newFuel) {
        CommandBus commandBus = new CommandBus();
        try {
            commandBus.handle(new RegisterFuelConsumptionCommand(newFuel));
        } catch (CommandExecutionFailedException e) {
            return new ApiErrorResponseWithObject<Fuel>(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    "/reports/add",
                    newFuel
            );
        }
        return new ApiSuccessResponse(HttpStatus.CREATED, "/reports/add");
    }


    @GetMapping("/reports/find/money-spent-by-month/{month}/{year}")
    ApiResponseInterface spentByMonth(
            @PathVariable Integer month,
            @PathVariable Integer year,
            @RequestParam(required = false) Integer driverId
    ) {
        MonthAndDriverValueObject monthAndDriver;
        if (driverId == null || driverId < 1) {
            monthAndDriver = new MonthAndDriverValueObject(year, month);
        } else {
            monthAndDriver = new MonthAndDriverValueObject(year, month, driverId);
        }

        ArrayList<EntityInterface> entities = null;
        Money totalAmount = null;
        try {
            entities = this.fuelRepository.getRecordsByMonths(monthAndDriver);
            totalAmount = CalculateTotalSpentAmountService.calculate(entities);
        } catch (ConditionNotExecutableException | NoRecordsFoundException e) {
            return new ApiErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    "/reports/find/money-spent-by-month/{month}/{year}"
            );
        }

        return new ApiSuccessResponseWithObject<Money>(
                HttpStatus.OK,
                "/reports/find/money-spent-by-month/{month}/{year}",
                totalAmount
        );

    }

    @GetMapping("/reports/find/consumption-records-by-month/{month}/{year}")
    ApiResponseInterface consumptionRecordsByMonth(
            @PathVariable Integer month,
            @PathVariable Integer year,
            @RequestParam(required = false) Integer driverId
    ) {
        MonthAndDriverValueObject monthAndDriver;
        if (driverId == null || driverId < 1) {
            monthAndDriver = new MonthAndDriverValueObject(year, month);
        } else {
            monthAndDriver = new MonthAndDriverValueObject(year, month, driverId);
        }

        ArrayList<EntityInterface> entities = null;
        ArrayList<FuelConsumptionRecord> convertedEntities = null;

        try {
            entities = this.fuelRepository.getRecordsByMonths(monthAndDriver);
            convertedEntities = ConvertFuelToConsumptionRecordService.convertEntities(entities);
        } catch (ConditionNotExecutableException | NoRecordsFoundException e) {
            return new ApiErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    "/reports/find/consumption-records-by-month/{month}/{year}"
            );
        }

        return new ApiSuccessResponseWithObject<ArrayList<FuelConsumptionRecord>>(
                HttpStatus.OK,
                "/reports/find/consumption-records-by-month/{month}/{year}",
                convertedEntities
        );

    }

    @GetMapping("/reports/find/records-by-fuel-and-month/{month}/{year}")
    ApiResponseInterface consumptionRecordsByFuelAndMonth(
            @PathVariable Integer month,
            @PathVariable Integer year,
            @RequestParam(required = false) Integer driverId
    ) {
        MonthAndDriverValueObject monthAndDriver;
        if (driverId == null || driverId < 1) {
            monthAndDriver = new MonthAndDriverValueObject(year, month);
        } else {
            monthAndDriver = new MonthAndDriverValueObject(year, month, driverId);
        }

        ArrayList<EntityInterface> entities = null;
        HashMap<String, FuelGroupedByFuelType> groupedFuelEntities = null;

        FuelConsumptionByMonthService fuelService = new FuelConsumptionByMonthService();

        try {
            entities = this.fuelRepository.getRecordsByMonths(monthAndDriver);
            groupedFuelEntities = fuelService.calculate(entities);
        } catch (ConditionNotExecutableException | NoRecordsFoundException e) {
            return new ApiErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    e.getMessage(),
                    "/reports/find/consumption-records-by-month/{month}/{year}"
            );
        }

        return new ApiSuccessResponseWithObject<HashMap<String, FuelGroupedByFuelType>>(
                HttpStatus.OK,
                "/reports/find/consumption-records-by-month/{month}/{year}",
                groupedFuelEntities
        );

    }

}
