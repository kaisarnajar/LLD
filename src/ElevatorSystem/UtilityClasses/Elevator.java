package ElevatorSystem.UtilityClasses;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.Enums.ElevatorState;
import ElevatorSystem.Observers.ElevatorObserver;

import java.util.*;

public class Elevator {
    private int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState elevatorState;
    private List<ElevatorObserver> observerList;
    private Queue<ElevatorRequest> requests;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.elevatorState = ElevatorState.IDLE;
        this.observerList = new ArrayList<>();
        this.requests = new LinkedList<>();
    }

    public void addObserver(ElevatorObserver observer) {
        observerList.add(observer);
    }

    // Remove an observer
    public void removeObserver(ElevatorObserver observer) {
        observerList.remove(observer);
    }

    private void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver observer : observerList) {
            observer.onElevatorStateChange(this, state);
        }
    }

    // Notify all observers about a floor change
    private void notifyFloorChange(int floor) {
        for (ElevatorObserver observer : observerList) {
            observer.onElevatorFloorChange(this, floor);
        }
    }

    public void setState(ElevatorState newState) {
        this.elevatorState = newState;
        notifyStateChange(newState);
    }

    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }

    public void addRequest(ElevatorRequest elevatorRequest) {
        if (!requests.contains(elevatorRequest)) {
            requests.add(elevatorRequest);
        }

        int requestedFloor = elevatorRequest.getFloor();

        if (elevatorState == ElevatorState.IDLE && !requests.isEmpty()) {
            if (requestedFloor > currentFloor) {
                direction = Direction.UP;
            } else if (requestedFloor < currentFloor) {
                direction = Direction.DOWN;
            }
            setState(ElevatorState.MOVING);
        }
    }

    public void moveToNextStop(int nextStop) {
        if (elevatorState != ElevatorState.MOVING) {
            return;
        }

        while (currentFloor != nextStop) {
            if (direction == Direction.UP) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            notifyFloorChange(currentFloor);
            if (currentFloor == nextStop) {
                completeArrival();
                return;
            }
        }
    }

    private void completeArrival() {
        setState(ElevatorState.STOPPED);

        requests.removeIf(request -> request.getFloor() == currentFloor);

        if (requests.isEmpty()) {
            direction = Direction.IDLE;
            setState(ElevatorState.IDLE);
        } else {
            setState(ElevatorState.MOVING);
        }
    }

    public int getId() {
        return id;
    }

    // Get the elevator's current floor
    public int getCurrentFloor() {
        return currentFloor;
    }

    // Get the elevator's current direction
    public Direction getDirection() {
        return direction;
    }

    // Get the elevator's current state
    public ElevatorState getState() {
        return elevatorState;
    }

    // Get a copy of the current requests queue to prevent external modification
    public Queue<ElevatorRequest> getRequestsQueue() {
        return new LinkedList<>(requests);
    }

    // Get a list of all destination floors for display purposes
    public List<ElevatorRequest> getDestinationFloors() {
        return new ArrayList<>(requests);
    }
}
