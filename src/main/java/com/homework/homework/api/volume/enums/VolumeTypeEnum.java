package com.homework.homework.api.volume.enums;

public class VolumeTypeEnum {
    public static final String LITER = "L";

    public static boolean isPartOfEnum(String value) {
        return (
            LITER.equals(value)
        );
    }
}
