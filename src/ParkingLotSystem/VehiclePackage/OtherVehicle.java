package ParkingLotSystem.VehiclePackage;

import ParkingLotSystem.FareStrategyPattern.ParkingFeeStrategy;

public class OtherVehicle extends Vehicle {
    public OtherVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}