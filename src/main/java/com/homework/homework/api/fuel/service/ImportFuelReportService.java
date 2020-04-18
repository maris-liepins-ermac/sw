package com.homework.homework.api.fuel.service;

import com.google.gson.Gson;
import com.homework.homework.api.fuel.domain.command.RegisterFuelConsumptionCommand;
import com.homework.homework.api.fuel.entity.Fuel;
import com.homework.homework.api.fuel.exceptions.UnableToImportFileFuelReportsException;
import com.homework.homework.api.fuel.valueobject.FuelReportImportResult;
import com.homework.homework.commandbus.CommandBus;
import com.homework.homework.filesystem.ReadFromFile;
import com.homework.homework.filesystem.exceptions.UnableToReadFromFileException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImportFuelReportService {
    private final CommandBus commandBus;
    private final Gson gson;
    private List<String> failedImports;
    private Integer successfulImports = 0;
    private Integer failedImportsCount = 0;

    public ImportFuelReportService() {
        this.commandBus = new CommandBus();
        this.gson = new Gson();
        this.failedImports = new ArrayList<>();
    }

    public FuelReportImportResult importFromFile(MultipartFile file) throws UnableToImportFileFuelReportsException {
        List<String> lines = this.getLinesFromFile(file);
        for (String line : lines) {
            try{
                Fuel fuel = this.gson.fromJson(line, Fuel.class);
                this.commandBus.handle(new RegisterFuelConsumptionCommand(fuel));
                successfulImports++;
            }catch (Exception e)
            {
                failedImports.add(line);
                failedImportsCount++;
            }
        }

        return new FuelReportImportResult(
                this.successfulImports,
                this.failedImportsCount,
                this.failedImports
        );
    }


    private List<String> getLinesFromFile(MultipartFile file) throws UnableToImportFileFuelReportsException {
        List<String> lines = null;
        try {
            lines = ReadFromFile.readLineByLine(file);
        } catch (UnableToReadFromFileException e) {
            throw new UnableToImportFileFuelReportsException(e.getMessage());
        }

        if (1 > lines.size()) {
            throw new UnableToImportFileFuelReportsException("File is empty");
        }

        return lines;
    }
}
