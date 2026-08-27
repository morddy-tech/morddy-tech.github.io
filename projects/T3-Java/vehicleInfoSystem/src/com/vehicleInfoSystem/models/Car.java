package com.vehicleInfoSystem.models;

import com.vehicleInfoSystem.models.enums.FuelType;

public class Car implements Vehicle, CarVehicle {
    private final String make;
    private final String model;
    private final int year;
    private int numberOfDoors;
    private FuelType fuelType;

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    @Override public String getMake() { return make; }
    @Override public String getModel() { return model; }
    @Override public int getYear() { return year; }
    @Override public void setNumberOfDoors(int doors) { this.numberOfDoors = doors; }
    @Override public int getNumberOfDoors() { return numberOfDoors; }
    @Override public void setFuelType(FuelType fuelType) { this.fuelType = fuelType; }
    @Override public FuelType getFuelType() { return fuelType; }

    @Override
    public String toString() {
        return String.format("%s %s (%d) | Doors: %d | Fuel: %s",
                make, model, year, numberOfDoors, fuelType);
    }
}