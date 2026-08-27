package com.vehicleInfoSystem.models;

import com.vehicleInfoSystem.models.enums.FuelType;

public interface CarVehicle {
    void setNumberOfDoors(int doors);
    int getNumberOfDoors();
    void setFuelType(FuelType fuelType);
    FuelType getFuelType();
}