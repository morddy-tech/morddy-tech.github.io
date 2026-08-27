package com.vehicleInfoSystem.models.enums;

public enum TransmissionType {
    MANUAL, AUTOMATIC;

    public static boolean isValid(String value) {
        for (TransmissionType tt : values()) {
            if (tt.name().equalsIgnoreCase(value)) return true;
        }
        return false;
    }
}