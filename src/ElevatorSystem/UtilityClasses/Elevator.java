package ElevatorSystem.UtilityClasses;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.Enums.ElevatorState;
import ElevatorSystem.Observers.ElevatorObserver;

import java.util.*;

public class Elevator {
    private final int id;
    private int currentFloor;
    private Direction direction;
    private ElevatorState state;
    private final List<ElevatorObserver> observers;
    private final Queue<ElevatorRequest> requests;

    public Elevator(int id) {
        this.id = id;
        this.currentFloor = 1;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.observers = new ArrayList<>();
        this.requests = new LinkedList<>();
    }

    public void addObserver(ElevatorObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ElevatorObserver observer) {
        observers.remove(observer);
    }

    private void notifyStateChange(ElevatorState state) {
        for (ElevatorObserver obs : observers) obs.onElevatorStateChange(this, state);
    }

    private void notifyFloorChange(int floor) {
        for (ElevatorObserver obs : observers) obs.onElevatorFloorChange(this, floor);
    }

    public void setState(ElevatorState newState) {
        this.state = newState;
        notifyStateChange(newState);
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    public void addRequest(ElevatorRequest request) {
        if (!requests.contains(request)) requests.add(request);
        if (state == ElevatorState.IDLE) setState(ElevatorState.MOVING);
    }

    public void moveToNextStop(int nextFloor) {
        if (state != ElevatorState.MOVING) return;
        while (currentFloor != nextFloor) {
            currentFloor += (direction == Direction.UP ? 1 : -1);
            notifyFloorChange(currentFloor);
        }
        completeArrival();
    }

    private void completeArrival() {
        setState(ElevatorState.STOPPED);
        requests.removeIf(r -> r.getFloor() == currentFloor);
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

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public Queue<ElevatorRequest> getRequestsQueue() {
        return new LinkedList<>(requests);
    }

    public List<ElevatorRequest> getDestinationFloors() {
        return new ArrayList<>(requests);
    }
}

