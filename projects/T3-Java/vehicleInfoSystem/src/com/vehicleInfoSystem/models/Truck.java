package com.vehicleInfoSystem.models;

import com.vehicleInfoSystem.models.enums.TransmissionType;

public class Truck implements Vehicle, TruckVehicle {
    private final String make;
    private final String model;
    private final int year;
    private double cargoCapacity;
    private TransmissionType transmissionType;

    public Truck(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override public String getMake() { return make; }
    @Override public String getModel() { return model; }
    @Override public int getYear() { return year; }
    @Override public void setCargoCapacity(double capacity) { this.cargoCapacity = capacity; }
    @Override public double getCargoCapacity() { return cargoCapacity; }
    @Override public void setTransmissionType(TransmissionType transmission) { this.transmissionType = transmission; }
    @Override public TransmissionType getTransmissionType() { return transmissionType; }

    @Override
    public String toString() {
        return String.format("%s %s (%d) | Capacity: %.1f tons | Transmission: %s",
                make, model, year, cargoCapacity, transmissionType);
    }
}