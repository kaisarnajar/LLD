package ElevatorSystem.SchedulingStrategyPackage;

import ElevatorSystem.UtilityClasses.Elevator;

public interface SchedulingStrategy {
    int getNextStop(Elevator elevator);
}
