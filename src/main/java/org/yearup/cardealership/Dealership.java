package org.yearup.cardealership;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public List<Vehicle> getVehiclesByPrice(double min, double max) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                values.add(v);
            }
        }
        return values;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getModel().equals(model) || v.getMake().equals(make)) {
                values.add(v);
            }
        }
        return values;

    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= min && v.getYear() <= max) {
                values.add(v);
            }
        }
        return values;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> values = new ArrayList<Vehicle>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                values.add(v);
            }
        }return values;
    }

        public List<Vehicle> getVehiclesByMileage ( double min, double max){
            List<Vehicle> values = new ArrayList<Vehicle>();
            for (Vehicle v : inventory) {
                if (v.getOdometer() >= min && v.getOdometer() <= max) {
                    values.add(v);
                }
            }
            return values;
        }

        public List<Vehicle> getVehiclesByType (String vehicleType){
            List<Vehicle> values = new ArrayList<Vehicle>();
            for (Vehicle v : inventory) {
                if (v.getVehicleType().equalsIgnoreCase(vehicleType)) {
                    values.add(v);
                }
            }return values;
        }

        public List<Vehicle> getAllVehicles () {
            return inventory;
        }

        public void addVehicles (Vehicle vehicle){
            inventory.add(vehicle);
        }

        public void removeVehicles (Vehicle vehicle){
            inventory.add(vehicle);
        }
}

