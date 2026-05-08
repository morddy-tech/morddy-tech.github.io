package com.vehicleInfoSystem.models.enums;

public enum FuelType {
    PETROL, DIESEL, ELECTRIC;

    public static boolean isValid(String value) {
        for (FuelType ft : values()) {
            if (ft.name().equalsIgnoreCase(value)) return true;
        }
        return false;
    }
}