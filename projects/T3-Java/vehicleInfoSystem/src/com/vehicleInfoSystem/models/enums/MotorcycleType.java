package com.vehicleInfoSystem.models.enums;

public enum MotorcycleType {
    SPORT, CRUISER, OFF_ROAD;

    public static boolean isValid(String value) {
        for (MotorcycleType mt : values()) {
            if (mt.name().equalsIgnoreCase(value.replace("-", "_"))) return true;
        }
        return false;
    }
}