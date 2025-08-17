package VendingMachineSystem.VendingMachineStates;

public class IdleState implements VendingMachineState {

    public IdleState() {
        System.out.println("Vending machine is now in Idle State");
    }

    @Override
    public String getStateName() {
        return "IdleState";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        if(!context.getInventory().hasItems()) {
            return new OutOfStockState();
        }

        if(!context.getCoinList().isEmpty())
            return new HasMoneyState();

        return this;
    }
}
