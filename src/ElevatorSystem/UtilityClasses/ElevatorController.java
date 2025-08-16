package ElevatorSystem.UtilityClasses;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.SchedulingStrategyPackage.ScanSchedulingStrategy;
import ElevatorSystem.SchedulingStrategyPackage.SchedulingStrategy;

import java.util.*;

public class ElevatorController {
    private List<Elevator> elevators;
    // List of all floors in the building
    private List<Floor> floors;
    // Strategy to determine the scheduling of elevators
    private SchedulingStrategy schedulingStrategy;
    // ID of the current elevator (used for internal operations)
    private int currentElevatorId;

    public ElevatorController() {

    }


    public ElevatorController(int numberOfElevators, int noOfFloors) {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.schedulingStrategy = new ScanSchedulingStrategy();

        for (int i = 1; i <= numberOfElevators; i++) {
            elevators.add(new Elevator(i));
        }
        // Initialize floors
        for (int i = 1; i <= noOfFloors; i++) {
            floors.add(new Floor(i));
        }

    }

    public void setSchedulingStrategy(SchedulingStrategy strategy) {
        this.schedulingStrategy = strategy;
    }

    //External request. floorNumber means from which floor the request came.
    //And direction means whether that person wants to go down or up.
    public void requestElevator(int elevatorId, int floorNumber, Direction direction) {
        System.out.println(
                "External request: Floor " + floorNumber + ", Direction " + direction);

        Elevator selectedElevator = getElevatorById(elevatorId);

        if (selectedElevator != null) {
            selectedElevator.addRequest(new ElevatorRequest(elevatorId, floorNumber, false, direction));
            System.out.println("Assigned elevator " + selectedElevator.getId()
                    + " to floor " + floorNumber);
        } else {
            System.out.println("No elevator available for floor " + floorNumber);
        }
    }

    //Handle internal request for specific floor
    public void requestFloor(int elevatorId, int floorNumber) {
        Elevator elevator = getElevatorById(elevatorId);
        System.out.println("Internal request: Elevator " + elevator.getId()
                + " to floor " + floorNumber);

        Direction direction = floorNumber > elevator.getCurrentFloor() ? Direction.UP : Direction.DOWN;

        elevator.addRequest(new ElevatorRequest(elevatorId, floorNumber, true, direction));
    }

    private Elevator getElevatorById(int elevatorId) {
        for (Elevator elevator : elevators) {
            if (elevator.getId() == elevatorId)
                return elevator;
        }
        return null; // Return null if no matching elevator is found
    }

    public void step() {
        for (Elevator elevator : elevators) {
            if (!elevator.getRequestsQueue().isEmpty()) {
                int nextStop = schedulingStrategy.getNextStop(elevator);

                if (elevator.getCurrentFloor() != nextStop) {
                    elevator.moveToNextStop(nextStop);
                }
            }
        }
    }

    // Get the list of all elevators
    public List<Elevator> getElevators() {
        return elevators;
    }

    // Get the list of all floors
    public List<Floor> getFloors() {
        return floors;
    }

    // Set the ID of the current elevator
    public void setCurrentElevator(int elevatorId) {
        this.currentElevatorId = elevatorId;
    }
}
