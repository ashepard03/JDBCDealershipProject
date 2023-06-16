package com.yearup.dealership;

public interface VehicleDAO {
    public List<Vehicle> getVehiclesByPrice(double min, double max);
}
