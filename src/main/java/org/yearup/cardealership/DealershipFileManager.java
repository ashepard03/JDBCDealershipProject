package org.yearup.cardealership;

import java.io.*;

public class DealershipFileManager {
    private String fileName;
    public DealershipFileManager(String fileName) {
        this.fileName = fileName;
    }

    public Dealership getDealership() {
        Dealership dealership;

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;


            // Read first line of csv into Dealership constructor
            input = bufReader.readLine();
            String[] details = input.split("\\|"); // split the line into pieces to extract and store info from each piece
            String name = details[0];
            String address = details[1];
            String phone = details[2];
            dealership = new Dealership(name, address, phone);

            while ((input = bufReader.readLine()) != null) { // For every line in the csv file until no more lines:
                details = input.split("\\|");
                String vin = details[0];
                int year = Integer.parseInt(details[1]);
                String make = details[2];
                String model = details[3];
                String vehicleType = details[4];
                String color = details[5];
                int odometer = Integer.parseInt(details[6]);
                double price = Double.parseDouble(details[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicles(vehicle);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }

    public void saveDealership(Dealership dealership){
//        String name = dealership.getName();
//        String address = dealership.getAddress();
//        String phone = dealership.getPhone();
        String dealer = String.format("%s|%s|%s\n", dealership.getName(), dealership.getAddress(), dealership.getPhone());

        try {
            FileWriter fileWriter = new FileWriter("inventory.csv");
            fileWriter.write(dealer);
            for (Vehicle v : dealership.getAllVehicles()) {
                String vehicle = String.format("%s|%d|%s|%s|%s|%s|%d|%.2f\n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
                fileWriter.write(vehicle);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



