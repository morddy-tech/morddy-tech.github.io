package com.vehicleInfoSystem;

import com.vehicleInfoSystem.models.*;
import com.vehicleInfoSystem.models.enums.*;

import java.util.Scanner;
import java.time.Year;

public class VehicleInformationSystem {
    private static final int MIN_YEAR = 1886;
    private static final int MAX_YEAR = Year.now().getValue() + 1;

    // Helper input methods
    private static String readString(Scanner sc, String prompt) {
        System.out.print(prompt);
        String input = sc.nextLine().trim();
        while (input.isEmpty()) {
            System.out.print("Input cannot be empty. " + prompt);
            input = sc.nextLine().trim();
        }
        return input;
    }

    private static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int value = sc.nextInt();
                sc.nextLine();
                if (value >= min && value <= max) return value;
                System.out.printf("Please enter a number between %d and %d.\n", min, max);
            } else {
                sc.nextLine();
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static double readDouble(Scanner sc, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                double value = sc.nextDouble();
                sc.nextLine();
                if (value >= min && value <= max) return value;
                System.out.printf("Please enter a value between %.1f and %.1f.\n", min, max);
            } else {
                sc.nextLine();
                System.out.println("Invalid number. Please try again.");
            }
        }
    }

    private static <E extends Enum<E>> E readEnum(Scanner sc, String prompt, Class<E> enumClass, String allowedValues) {
        while (true) {
            System.out.print(prompt + " (" + allowedValues + "): ");
            String input = sc.nextLine().trim().toUpperCase().replace("-", "_");
            try {
                return Enum.valueOf(enumClass, input);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid value. Allowed: " + allowedValues);
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("=== Vehicle Information System ===\n");

            // Car
            System.out.println("--- Please Enter Car Details ---");
            String make = readString(scanner, "Make: ");
            String model = readString(scanner, "Model: ");
            int year = readInt(scanner, "Year: ", MIN_YEAR, MAX_YEAR);
            Car car = new Car(make, model, year);
            int doors = readInt(scanner, "Number of Doors (2-6): ", 2, 6);
            car.setNumberOfDoors(doors);
            FuelType fuel = readEnum(scanner, "Fuel Type", FuelType.class, "Petrol/Diesel/Electric");
            car.setFuelType(fuel);

            // Motorcycle
            System.out.println("\n--- Motorcycle Details ---");
            make = readString(scanner, "Make: ");
            model = readString(scanner, "Model: ");
            year = readInt(scanner, "Year: ", MIN_YEAR, MAX_YEAR);
            Motorcycle motorcycle = new Motorcycle(make, model, year);
            int wheels = readInt(scanner, "Number of Wheels (2 or 3): ", 2, 3);
            motorcycle.setNumberOfWheels(wheels);
            MotorcycleType bikeType = readEnum(scanner, "Motorcycle Type", MotorcycleType.class, "Sport/Cruiser/Off-road");
            motorcycle.setMotorcycleType(bikeType);

            // Truck
            System.out.println("\n--- Truck Details ---");
            make = readString(scanner, "Make: ");
            model = readString(scanner, "Model: ");
            year = readInt(scanner, "Year: ", MIN_YEAR, MAX_YEAR);
            Truck truck = new Truck(make, model, year);
            double capacity = readDouble(scanner, "Cargo Capacity (tons, 0-100): ", 0.0, 100.0);
            truck.setCargoCapacity(capacity);
            TransmissionType transmission = readEnum(scanner, "Transmission Type", TransmissionType.class, "Manual/Automatic");
            truck.setTransmissionType(transmission);

            // Display
            System.out.println("\n=== Vehicle Details Review ===");
            System.out.println("Car:        " + car);
            System.out.println("Motorcycle: " + motorcycle);
            System.out.println("Truck:      " + truck);

        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}