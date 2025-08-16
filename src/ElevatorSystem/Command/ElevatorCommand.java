package ElevatorSystem.Command;

import ElevatorSystem.UtilityClasses.ElevatorController;

public interface ElevatorCommand {
    void execute(ElevatorController elevatorController);
}
