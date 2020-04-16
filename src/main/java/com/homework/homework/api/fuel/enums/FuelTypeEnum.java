package com.homework.homework.api.fuel.enums;

public class FuelTypeEnum {
    public static final String TYPE_95 = "95";
    public static final String TYPE_98 = "98";
    public static final String TYPE_D = "D";

    public static boolean isPartOfEnum(String value) {
        return (
            TYPE_95.equals(value) ||
                TYPE_98.equals(value) ||
                TYPE_D.equals(value)
        );
    }
}
