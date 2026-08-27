package com.vehicleInfoSystem.models;

import com.vehicleInfoSystem.models.enums.MotorcycleType;

public class Motorcycle implements Vehicle, MotorVehicle {
    private final String make;
    private final String model;
    private final int year;
    private int wheels;
    private MotorcycleType motorcycleType;

    public Motorcycle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override public String getMake() { return make; }
    @Override public String getModel() { return model; }
    @Override public int getYear() { return year; }
    @Override public void setNumberOfWheels(int wheels) { this.wheels = wheels; }
    @Override public int getNumberOfWheels() { return wheels; }
    @Override public void setMotorcycleType(MotorcycleType type) { this.motorcycleType = type; }
    @Override public MotorcycleType getMotorcycleType() { return motorcycleType; }

    @Override
    public String toString() {
        return String.format("%s %s (%d) | Wheels: %d | Type: %s",
                make, model, year, wheels, motorcycleType);
    }
}