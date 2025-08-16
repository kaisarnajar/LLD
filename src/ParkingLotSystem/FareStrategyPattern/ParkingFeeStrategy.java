package ParkingLotSystem.FareStrategyPattern;

import ParkingLotSystem.DurationType;

public interface ParkingFeeStrategy {
    double calculateFee(String vehicleType, int duration, DurationType durationType);
}
