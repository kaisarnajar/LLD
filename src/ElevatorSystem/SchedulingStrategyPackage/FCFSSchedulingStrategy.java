package ElevatorSystem.SchedulingStrategyPackage;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.UtilityClasses.Elevator;

import java.util.Queue;

public class FCFSSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        if (elevator.getRequestsQueue().isEmpty()) return elevator.getCurrentFloor();
        ElevatorRequest nextReq = elevator.getRequestsQueue().poll();
        int nextFloor = nextReq.getFloor();

        if (nextFloor > elevator.getCurrentFloor()) elevator.setDirection(Direction.UP);
        else if (nextFloor < elevator.getCurrentFloor()) elevator.setDirection(Direction.DOWN);
        else elevator.setDirection(Direction.IDLE);

        return nextFloor;
    }
}
