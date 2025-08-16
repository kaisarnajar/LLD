package ParkingLotSystem.VehiclePackage;

import ParkingLotSystem.FareStrategyPattern.ParkingFeeStrategy;

public class CarVehicle extends Vehicle {
    public CarVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}