package ElevatorSystem;

import ElevatorSystem.Command.ElevatorRequest;
import ElevatorSystem.Enums.Direction;
import ElevatorSystem.Observers.ElevatorDisplay;
import ElevatorSystem.UtilityClasses.Building;
import ElevatorSystem.UtilityClasses.Elevator;
import ElevatorSystem.UtilityClasses.ElevatorController;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Building building = new Building("Office Tower", 10, 3);
        ElevatorController controller = building.getElevatorController();
        ElevatorDisplay display = new ElevatorDisplay();

        for (Elevator e : controller.getElevators()) e.addObserver(display);

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nElevator System:");
            System.out.println("1. External Request");
            System.out.println("2. Internal Request");
            System.out.println("3. Step Simulation");
            System.out.println("4. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter elevator ID: ");
                    int eid = sc.nextInt();
                    System.out.print("Enter floor: ");
                    int floor = sc.nextInt();
                    System.out.print("Direction (1=UP, 2=DOWN): ");
                    int dir = sc.nextInt();
                    new ElevatorRequest(eid, floor, false, dir == 1 ? Direction.UP : Direction.DOWN).execute(controller);
                }
                case 2 -> {
                    System.out.print("Enter elevator ID: ");
                    int eid = sc.nextInt();
                    System.out.print("Enter destination floor: ");
                    int floor = sc.nextInt();
                    new ElevatorRequest(eid, floor, true, Direction.UP).execute(controller);
                }
                case 3 -> {
                    controller.step();
                    displayElevatorStatus(controller.getElevators());
                }
                case 4 -> running = false;
                default -> System.out.println("Invalid choice");
            }
        }
        sc.close();
    }

    private static void displayElevatorStatus(List<Elevator> elevators) {
        System.out.println("\nElevator Status:");
        for (Elevator e : elevators) {
            System.out.println("Elevator " + e.getId() + ": Floor " + e.getCurrentFloor() +
                    ", Direction " + e.getDirection() + ", State " + e.getState() +
                    ", Destinations " + e.getDestinationFloors());
        }
    }
}
