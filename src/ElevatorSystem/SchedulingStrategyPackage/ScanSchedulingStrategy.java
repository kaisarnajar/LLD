package ElevatorSystem.SchedulingStrategyPackage;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.UtilityClasses.Elevator;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ScanSchedulingStrategy implements SchedulingStrategy {
    @Override
    public int getNextStop(Elevator elevator) {
        int currentFloor = elevator.getCurrentFloor();
        Direction direction = elevator.getDirection();
        Queue<ElevatorRequest> requests = elevator.getRequestsQueue();

        if (requests.isEmpty()) return currentFloor;

        PriorityQueue<ElevatorRequest> upQueue = new PriorityQueue<>(Comparator.comparingInt(ElevatorRequest::getFloor));
        PriorityQueue<ElevatorRequest> downQueue = new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());

        while (!requests.isEmpty()) {
            ElevatorRequest r = requests.poll();
            if (r.getFloor() > currentFloor) upQueue.add(r);
            else downQueue.add(r);
        }

        if (direction == Direction.UP) {
            return !upQueue.isEmpty() ? upQueue.poll().getFloor() : switchDirection(elevator, downQueue);
        } else if (direction == Direction.DOWN) {
            return !downQueue.isEmpty() ? downQueue.poll().getFloor() : switchDirection(elevator, upQueue);
        } else {
            return !upQueue.isEmpty() ? upQueue.poll().getFloor()
                    : !downQueue.isEmpty() ? downQueue.poll().getFloor()
                    : currentFloor;
        }
    }

    private int switchDirection(Elevator elevator, PriorityQueue<ElevatorRequest> queue) {
        elevator.setDirection(elevator.getDirection() == Direction.UP ? Direction.DOWN : Direction.UP);
        return queue.isEmpty() ? elevator.getCurrentFloor() : queue.poll().getFloor();
    }
}
