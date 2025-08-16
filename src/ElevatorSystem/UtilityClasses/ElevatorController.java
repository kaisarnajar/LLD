package ElevatorSystem.UtilityClasses;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.SchedulingStrategyPackage.ScanSchedulingStrategy;
import ElevatorSystem.SchedulingStrategyPackage.SchedulingStrategy;

import java.util.*;

public class ElevatorController {
    private final List<Elevator> elevators;
    private final List<Floor> floors;
    private SchedulingStrategy strategy;

    public ElevatorController(int numElevators, int numFloors) {
        this.elevators = new ArrayList<>();
        this.floors = new ArrayList<>();
        this.strategy = new ScanSchedulingStrategy();

        for (int i = 1; i <= numElevators; i++) elevators.add(new Elevator(i));
        for (int i = 1; i <= numFloors; i++) floors.add(new Floor(i));
    }

    public void setSchedulingStrategy(SchedulingStrategy strategy) {
        this.strategy = strategy;
    }

    public void requestElevator(int elevatorId, int floor, Direction dir) {
        Elevator elevator = getElevatorById(elevatorId);
        if (elevator != null) elevator.addRequest(new ElevatorRequest(elevatorId, floor, false, dir));
    }

    public void requestFloor(int elevatorId, int floor) {
        Elevator elevator = getElevatorById(elevatorId);
        if (elevator != null) {
            Direction dir = (floor > elevator.getCurrentFloor()) ? Direction.UP : Direction.DOWN;
            elevator.addRequest(new ElevatorRequest(elevatorId, floor, true, dir));
        }
    }

    private Elevator getElevatorById(int id) {
        return elevators.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void step() {
        for (Elevator e : elevators) {
            if (!e.getRequestsQueue().isEmpty()) {
                int next = strategy.getNextStop(e);
                e.moveToNextStop(next);
            }
        }
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public List<Floor> getFloors() {
        return floors;
    }
}


