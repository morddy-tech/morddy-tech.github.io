package com.vehicleInfoSystem.models;

import com.vehicleInfoSystem.models.enums.TransmissionType;

public interface TruckVehicle {
    void setCargoCapacity(double capacity);
    double getCargoCapacity();
    void setTransmissionType(TransmissionType transmission);
    TransmissionType getTransmissionType();
}