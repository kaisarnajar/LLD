package ElevatorSystem.SchedulingStrategyPackage;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.UtilityClasses.Elevator;

import java.util.Queue;

public class LookSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        Queue<ElevatorRequest> requests = elevator.getRequestsQueue();

        if (requests.isEmpty()) return currentFloor;

        ElevatorRequest primaryRequest = requests.peek();
        int targetFloor = primaryRequest.getFloor();
        Direction direction = (targetFloor > currentFloor) ? Direction.UP : Direction.DOWN;

        Integer candidate = null;
        for (ElevatorRequest req : requests) {
            int reqFloor = req.getFloor();
            if (direction == Direction.UP && reqFloor > currentFloor && reqFloor <= targetFloor) {
                if (candidate == null || reqFloor < candidate) candidate = reqFloor;
            } else if (direction == Direction.DOWN && reqFloor < currentFloor && reqFloor >= targetFloor) {
                if (candidate == null || reqFloor > candidate) candidate = reqFloor;
            }
        }
        return (candidate != null) ? candidate : targetFloor;
    }
}