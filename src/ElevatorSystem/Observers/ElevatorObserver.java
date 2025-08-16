package ElevatorSystem.Observers;

import ElevatorSystem.Enums.ElevatorState;
import ElevatorSystem.UtilityClasses.Elevator;

public interface ElevatorObserver {
    void onElevatorStateChange(Elevator elevator, ElevatorState elevatorState);

    void onElevatorFloorChange(Elevator elevator, int floor);
}
