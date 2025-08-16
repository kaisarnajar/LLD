package ParkingLotSystem.VehiclePackage;

import ParkingLotSystem.FareStrategyPattern.ParkingFeeStrategy;

public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy parkingFeeStrategy) {
        if (vehicleType.equals("CAR")) {
            return new CarVehicle(licensePlate, vehicleType, parkingFeeStrategy);
        } else if (vehicleType.equalsIgnoreCase("Bike")) {
            return new BikeVehicle(licensePlate, vehicleType, parkingFeeStrategy);
        }
        return new OtherVehicle(licensePlate, vehicleType, parkingFeeStrategy);
    }
}
