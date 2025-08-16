package ElevatorSystem.Command;

import ElevatorSystem.Enums.Direction;
import ElevatorSystem.UtilityClasses.ElevatorController;

public class ElevatorRequest implements ElevatorCommand {

    private final int elevatorId;
    private final int floor;
    private final Direction requestDirection;
    private final boolean isInternalRequest;

    public ElevatorRequest(int elevatorId, int floor, boolean isInternalRequest, Direction direction) {
        this.elevatorId = elevatorId;
        this.floor = floor;
        this.isInternalRequest = isInternalRequest;
        this.requestDirection = direction;
    }

    @Override
    public void execute(ElevatorController controller) {
        if (isInternalRequest) {
            controller.requestFloor(elevatorId, floor);
        } else {
            controller.requestElevator(elevatorId, floor, requestDirection);
        }
    }

    public Direction getDirection() { return requestDirection; }
    public int getFloor() { return floor; }
    public boolean isInternalRequest() { return isInternalRequest; }

    @Override
    public String toString() {
        return "Request[elevator=" + elevatorId + ", floor=" + floor + ", internal=" + isInternalRequest + "]";
    }
}

