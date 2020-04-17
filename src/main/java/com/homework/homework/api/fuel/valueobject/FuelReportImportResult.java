package com.homework.homework.api.fuel.valueobject;

import java.util.List;

public class FuelReportImportResult {
    private Integer successfulImports;
    private Integer failedImportsCount;
    private List<String> failedImports;

    public FuelReportImportResult(
            Integer successfulImports,
            Integer failedImportsCount,
            List<String> failedImports
    ) {
        this.successfulImports = successfulImports;
        this.failedImportsCount = failedImportsCount;
        this.failedImports = failedImports;
    }


    public Integer getSuccessfulImports() {
        return successfulImports;
    }

    public Integer getFailedImportsCount() {
        return failedImportsCount;
    }

    public List<String> getFailedImports() {
        return failedImports;
    }
}
