package ParkingLotSystem;

import ParkingLotSystem.VehiclePackage.Vehicle;

public abstract class ParkingSpot {
    private int spotNumber;
    private boolean isOccupied;
    private Vehicle vehicle;
    private String spotType;

    // Constructor to initialize parking spot with spot number and type
    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle) {
        // Check if the spot is already occupied
        if (isOccupied) {
            throw new IllegalStateException("Spot is already occupied.");
        }
        // Check if the vehicle can be parked in this spot
        if (!canParkVehicle(vehicle)) {
            throw new IllegalArgumentException(
                    "This spot is not suitable for" + vehicle.getVehicleType());
        }
        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate() {
        // Check if the spot is already vacant
        if (!isOccupied) {
            throw new IllegalStateException("Spot is already vacant.");
        }
        this.vehicle = null;
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    // Getter for the vehicle parked in the spot
    public Vehicle getVehicle() {
        return vehicle;
    }

    // Getter for spot type
    public String getSpotType() {
        return spotType;
    }
}
