package com.vehicleInfoSystem.models;

import com.vehicleInfoSystem.models.enums.MotorcycleType;

public interface MotorVehicle {
    void setNumberOfWheels(int wheels);
    int getNumberOfWheels();
    void setMotorcycleType(MotorcycleType type);
    MotorcycleType getMotorcycleType();
}