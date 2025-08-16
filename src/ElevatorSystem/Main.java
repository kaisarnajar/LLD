package ElevatorSystem;

import ElevatorSystem.Enums.Direction;
import ElevatorSystem.Observers.ElevatorDisplay;
import ElevatorSystem.SchedulingStrategyPackage.FCFSSchedulingStrategy;
import ElevatorSystem.SchedulingStrategyPackage.ScanSchedulingStrategy;
import ElevatorSystem.UtilityClasses.Building;
import ElevatorSystem.UtilityClasses.Elevator;
import ElevatorSystem.UtilityClasses.ElevatorController;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Building building = new Building("Office Tower", 10, 3);
        ElevatorController elevatorController = building.getElevatorController();
        ElevatorDisplay elevatorDisplay = new ElevatorDisplay();

        for (Elevator elevator : elevatorController.getElevators()) {
            elevator.addObserver(elevatorDisplay);
        }

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        // Display simulation details and options
        System.out.println("Elevator System Simulation");
        System.out.println("Building: " + building.getName());
        System.out.println("Floors: " + building.getNumberOfFloors());
        System.out.println("Elevators: " + elevatorController.getElevators().size());

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Handle external elevator request
                // Handle internal elevator floor request
                System.out.print("Enter elevator ID: ");
                int externalElevatorId = scanner.nextInt();
                elevatorController.setCurrentElevator(externalElevatorId); // Set the selected elevator
                System.out.print("Enter floor number: ");
                int floorNum = scanner.nextInt();
                System.out.print("Direction (1 for UP, 2 for DOWN): ");
                int dirChoice = scanner.nextInt();
                Direction dir = dirChoice == 1 ? Direction.UP : Direction.DOWN;
                elevatorController.requestElevator(externalElevatorId, floorNum, dir);
                break;
            case 2:
                // Handle internal elevator floor request
                System.out.print("Enter elevator ID: ");
                int elevatorId = scanner.nextInt();
                elevatorController.setCurrentElevator(elevatorId); // Set the selected elevator
                System.out.print("Enter destination floor: ");
                int destFloor = scanner.nextInt();
                elevatorController.requestFloor(elevatorId, destFloor);
                break;
            case 3:
                // Simulate the next step in the system
                System.out.println("Simulating next step...");
                elevatorController.step(); // Perform the simulation step
                displayElevatorStatus(
                        elevatorController.getElevators()); // Display elevator statuses
                break;
            case 4:
                // Change the scheduling strategy
                System.out.println("Select strategy:");
                System.out.println("1. SCAN Algorithm");
                System.out.println("2. FCFS Algorithm");
                System.out.println("3. Look Algorithm");
                int strategyChoice = scanner.nextInt();
                if (strategyChoice == 1) {
                    elevatorController.setSchedulingStrategy(new ScanSchedulingStrategy());
                    System.out.println("Strategy set to SCAN Algorithm");
                } else {
                    elevatorController.setSchedulingStrategy(new FCFSSchedulingStrategy());
                    System.out.println("Strategy set to Nearest Elevator First");
                }
                break;
            case 5:
                // Exit the simulation
                running = false;
                break;
            default:
                // Handle invalid choices
                System.out.println("Invalid choice!");
        }
        scanner.close(); // Close the scanner to release resources
        System.out.println("Simulation ended"); // End of simulation
    }

    private static void displayElevatorStatus(List<Elevator> elevators) {
        System.out.println("nElevator Status:");
        for (Elevator elevator : elevators) {
            // Print details of each elevator, including current floor, direction, and
            // state
            System.out.println("Elevator " + elevator.getId() + ": Floor "
                    + elevator.getCurrentFloor() + ", Direction "
                    + elevator.getDirection() + ", State " + elevator.getState()
                    + ", Destinations " + elevator.getDestinationFloors());
        }
    }
}
