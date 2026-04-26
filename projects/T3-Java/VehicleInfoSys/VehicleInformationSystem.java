import java.util.Scanner;

public class VehicleInformationSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            // CAR
            System.out.println("Enter Car Details");

            System.out.print("Make: ");
            String make = scanner.nextLine();

            System.out.print("Model: ");
            String model = scanner.nextLine();

            System.out.print("Year: ");
            int year = scanner.nextInt();

            Car car = new Car(make, model, year);

            System.out.print("Number of Doors: ");
            car.setNumberOfDoors(scanner.nextInt());

            scanner.nextLine();

            System.out.print("Fuel Type (Petrol/Diesel/Electric): ");
            car.setFuelType(scanner.nextLine());


            // MOTORCYCLE
            System.out.println("\nEnter Motorcycle Details");

            System.out.print("Make: ");
            make = scanner.nextLine();

            System.out.print("Model: ");
            model = scanner.nextLine();

            System.out.print("Year: ");
            year = scanner.nextInt();

            Motorcycle motorcycle = new Motorcycle(make, model, year);

            System.out.print("Number of Wheels: ");
            motorcycle.setNumberOfWheels(scanner.nextInt());

            scanner.nextLine();

            System.out.print("Motorcycle Type (Sport/Cruiser/Off-road): ");
            motorcycle.setMotorcycleType(scanner.nextLine());


            // TRUCK
            System.out.println("\nEnter Truck Details");

            System.out.print("Make: ");
            make = scanner.nextLine();

            System.out.print("Model: ");
            model = scanner.nextLine();

            System.out.print("Year: ");
            year = scanner.nextInt();

            Truck truck = new Truck(make, model, year);

            System.out.print("Cargo Capacity (tons): ");
            truck.setCargoCapacity(scanner.nextDouble());

            scanner.nextLine();

            System.out.print("Transmission Type (Manual/Automatic): ");
            truck.setTransmissionType(scanner.nextLine());


            // DISPLAY INFORMATION
            System.out.println("\n--- Vehicle Details ---");

            System.out.println("\nCar:");
            System.out.println(car.getMake()+" "+car.getModel()+" "+car.getYear());
            System.out.println("Doors: "+car.getNumberOfDoors());
            System.out.println("Fuel: "+car.getFuelType());

            System.out.println("\nMotorcycle:");
            System.out.println(motorcycle.getMake()+" "+motorcycle.getModel()+" "+motorcycle.getYear());
            System.out.println("Wheels: "+motorcycle.getNumberOfWheels());
            System.out.println("Type: "+motorcycle.getMotorcycleType());

            System.out.println("\nTruck:");
            System.out.println(truck.getMake()+" "+truck.getModel()+" "+truck.getYear());
            System.out.println("Cargo Capacity: "+truck.getCargoCapacity());
            System.out.println("Transmission: "+truck.getTransmissionType());

        }
        catch(Exception e) {
            System.out.println("Invalid input detected. Please restart the program and enter correct values.");
        }

        scanner.close();
    }
}