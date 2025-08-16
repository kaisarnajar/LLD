package ElevatorSystem.Command;

import ElevatorSystem.Enums.Direction;
import ElevatorSystem.UtilityClasses.ElevatorController;

public class ElevatorRequest implements ElevatorCommand {

    private int elevatorId;
    private int floor; //Floor where the request is made.
    private Direction requestDirection;
    private ElevatorController controller;
    private boolean isInternalRequest;

    public ElevatorRequest(int elevatorId, int floor, boolean isInternalRequest, Direction direction) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.isInternalRequest = isInternalRequest;
        this.requestDirection = direction;
        this.controller = new ElevatorController();
    }

    @Override
    public void execute() {
        if (isInternalRequest) {
            controller.requestFloor(elevatorId, floor);
        } else {
            controller.requestElevator(elevatorId, floor, requestDirection);
        }
    }

    public Direction getDirection() {
        return requestDirection;
    }

    public int getFloor() {
        return floor;
    }

    public boolean checkIsInternalRequest() {
        return isInternalRequest;
    }

}
