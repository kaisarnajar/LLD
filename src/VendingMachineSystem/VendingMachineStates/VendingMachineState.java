package VendingMachineSystem.VendingMachineStates;

public interface VendingMachineState {
    String getStateName();
    VendingMachineState next(VendingMachineContext context);
}
