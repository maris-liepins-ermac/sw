package com.homework.homework.api.fuel.valueobject;

public class MonthAndDriverValueObject {
    private Integer year;
    private Integer month;
    private long driverId = -1;

    public MonthAndDriverValueObject(Integer year, Integer month, Integer driverId) {
        this.year = year;
        this.month = month;
        this.driverId = driverId;
    }

    public MonthAndDriverValueObject(Integer year, Integer month) {
        this.year = year;
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getMonth() {
        return month;
    }

    public long getDriverId() {
        return driverId;
    }

    public static MonthAndDriverValueObject setUpWithoutDriver(Integer year, Integer month) {
        return new MonthAndDriverValueObject(year, month);
    }
}
