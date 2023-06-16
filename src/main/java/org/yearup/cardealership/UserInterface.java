package org.yearup.cardealership;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);
    DealershipFileManager dfm;
    Dealership dealership;
    ContractDataManager cdm;
    Contract contract;


    public void display(){
        init();
        displayMenu();
    }

   private void init(){
       dfm = new DealershipFileManager("inventory.csv");
       dealership = dfm.getDealership();

       cdm = new ContractDataManager("contracts.csv");
   }

    private void displayVehicles(ArrayList<Vehicle> inventory) {
        System.out.printf("%-12s %-7s %-15s %-20s %-15s %-15s %-18s %-10s\n", "VIN", "YEAR", "MAKE", "MODEL", "TYPE", "COLOR", "ODOMETER", "PRICE");
        //System.out.println("VIN          YEAR    MAKE            MODEL                TYPE            COLOR           ODOMETER            PRICE");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------\n");
        for (Vehicle v: inventory) {

            System.out.printf("%-12s %-7d %-15s %-20s %-15s %-15s %-18d %-10.2f\n",
                    v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                    v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
        }
    }

    public void displayMenu() {
        boolean screenDone = false;
        while (!screenDone) {
            String menu = "\u001B[34m" + """ 
                    
                    -------Tuner City--------
                    
                       -Search Options-
                    [1] Vehicles By Price
                    [2] Vehicles By Make/Model
                    [3] Vehicles By Year
                    [4] Vehicles By Color
                    [5] Vehicles By Mileage
                    [6} Vehicles By Type
                    [7} All Vehicles
                       -Other Options-
                    [8] Add Vehicle
                    [9} Remove Vehicle
                    [0] Buy Or Lease Vehicle
                    [X] Exit
                    
                    """;

            System.out.print(menu);
            System.out.print("Enter a menu option: ");
            String input = scanner.nextLine();

            switch (input.toUpperCase()) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processByYearRequest();
                case "4" -> processByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehiclesRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                case "0" -> buyOrLeaseAVehicle();
                case "X" -> {
                    screenDone = true;
                    System.out.println("Exiting Car Dealership");
                    System.exit(0);
                }
                default -> System.out.println("Invalid input. Please try again!");
            }
        }
    }
    public void processGetByPriceRequest(){
        System.out.println("What is the minimum amount you are willing to pay?");
        String input = scanner.nextLine();
        double min = Double.parseDouble(input);
        System.out.println("What is the maximum amount you are willing to pay?");
        input = scanner.nextLine();
        double max = Double.parseDouble(input);
        System.out.println("----------------------------------- Search By Price Between " + min + " - " + max + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByPrice(min, max);
        displayVehicles(aList);
    }
    public void processGetByMakeModelRequest(){
        System.out.println("What make are you looking for?");
        String make = scanner.nextLine();
        System.out.println("What model are you looking for?");
        String model = scanner.nextLine();
        System.out.println("----------------------------------- Search By " + make + model + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMakeModel(make, model);
        displayVehicles(aList);
    }
    public void processByYearRequest(){
        System.out.println("What is the oldest year of vehicle you are looking for?");
        String input = scanner.nextLine();
        int min = Integer.parseInt(input);
        System.out.println("What is the newest year of vehicle you are looking for?");
        input = scanner.nextLine();
        int max = Integer.parseInt(input);
        System.out.println("----------------------------------- Search By Year Between " + min + " - " + max + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByYear(min, max);
        displayVehicles(aList);
    }
    public void processByColorRequest(){
        System.out.println("What color vehicle are you looking for?");
        String color = scanner.nextLine();
        System.out.println("------------------------------------- Search By Color: " + color + "-----------------------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByColor(color);
        displayVehicles(aList);
    }
    public void processGetByMileageRequest(){
        System.out.println("What is the minimum amount of miles you want the car to have?");
        String input = scanner.nextLine();
        double min = Double.parseDouble(input);
        System.out.println("What is the maximum amount of miles you want the car to have?");
        input = scanner.nextLine();
        double max = Double.parseDouble(input);
        System.out.println("---------------------------------- Search By Mileage Between " + min + " - " + max + "----------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByMileage(min, max);
        displayVehicles(aList);
    }
    public void processGetByVehicleTypeRequest(){
        System.out.println("What type of vehicle are you looking for?");
        String type = scanner.nextLine();
        System.out.println("------------------------------------- Search By Vehicle Type: " + type + "-----------------------------------------------------------");
        ArrayList aList = (ArrayList) dealership.getVehiclesByType(type);
        displayVehicles(aList);
    }
    public void processGetAllVehiclesRequest(){
        ArrayList aList = (ArrayList) dealership.getAllVehicles();
        displayVehicles(aList);
    }
    public void processAddVehicleRequest(){
        System.out.print("Enter the vehicle VIN:  ");
        String vin = scanner.nextLine();
        System.out.print("Enter the vehicle year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter the vehicle model: ");
        String model = scanner.nextLine();
        System.out.print("Enter the vehicle type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Enter the vehicle color: ");
        String color = scanner.nextLine();
        System.out.print("Enter the vehicle mileage: ");
        int odometer = scanner.nextInt();
        System.out.print("Enter the vehicle price: ");
        double price = scanner.nextDouble();
        System.out.println("\n" + make +" "+ model + " was successfully added to Tuner City's inventory " );
        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicles(vehicle);


        dfm.saveDealership(dealership);
    }
    public void processRemoveVehicleRequest(){
        System.out.print("Please enter the VIN of the vehicle you would like to remove: ");
        String vin = scanner.next();
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                System.out.println("The " + v.getMake() + v.getModel() + "has been removed");
                dealership.removeVehicles(v);
            }
        }
        dfm.saveDealership(dealership);
    }

    // allows the user to select their contract of choice between sale or lease
    public void buyOrLeaseAVehicle() {
        System.out.println("Please provide the VIN of the vehicle you would like to purchase");
        String vin = scanner.nextLine();
        // create a switch statement that allows you to select lease or sale
        Vehicle vehicle = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (vin.equals(v.getVin())) {
                vehicle = v;
                break;
            }
        }

        if (vehicle != null) {
            System.out.println("Select the contract type:");
            System.out.println("[1] Sales Contract");
            System.out.println("[2] Lease Contract");
            System.out.print("Enter your choice: ");
            int contractChoice = scanner.nextInt();

            if (contractChoice == 1) {
                createSalesContractData(vehicle);
            } else if (contractChoice == 2) {
                createLeaseContract(vehicle);
            } else {
                System.out.println("Invalid contract type choice.");
            }
        } else {
            System.out.println("Vehicle not found.");
        }
    }

    // actually creates the leasing contract date by asking the cx for their info
        public void createSalesContractData(Vehicle vehicle) {

            System.out.println("Generating your Sales Contract...");

            LocalDate currentDate = LocalDate.now();

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

            String contractDate = currentDate.format(dateFormatter);

            System.out.print("Enter customer name: ");

            String customerName = scanner.next().trim();

            System.out.print("Enter customer email: ");

            String customerEmail = scanner.next().trim();

            double salesTaxAmount = vehicle.getPrice() * 0.05; // Calculate sales tax amount as 5% of the vehicle price

            double recordingFee = 100.00; // Set the recording fee to $100

            double processingFee = (vehicle.getPrice() < 10000) ? 295.00 : 495.00; // Set the processing fee based on vehicle price

            System.out.print("Enter finance option (yes/no): ");

            boolean financeOption = scanner.next().equalsIgnoreCase("yes");

            SalesContract salesContract = new SalesContract(contractDate, customerName, customerEmail, vehicle,
                    financeOption);

            cdm.saveContract(salesContract);
            dealership.removeVehicles(vehicle);
            dfm.saveDealership(dealership);
            System.out.println("Sales Contract created and saved to Tuner City's records..");

        }

// actually creates the leasing contract date by asking the cx for their info
    public void createLeaseContract(Vehicle vehicle) {

        System.out.println("Generating your Lease Contract...");

        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        String contractDate = currentDate.format(dateFormatter);

        System.out.print("Enter customer name: ");

        String customerName = scanner.next().trim();

        System.out.print("Enter customer email: ");

        String customerEmail = scanner.next().trim();

        double expectedEndingValue = vehicle.getPrice() * 0.5; // Calculate expected ending value as 50% of the vehicle price

        double leaseFee = vehicle.getPrice() * 0.07; // Calculate lease fee as 7% of the vehicle price

        LeaseContract leaseContract = new LeaseContract(contractDate, customerName, customerEmail, vehicle);

        cdm.saveContract(leaseContract);

        dealership.removeVehicles(vehicle);
        dfm.saveDealership(dealership);
        System.out.println("Lease Contract created and saved to Tuner City's records.");

    }
}

