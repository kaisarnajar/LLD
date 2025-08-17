package VendingMachineSystem.VendingMachineStates;

import VendingMachineSystem.Enums.Coin;
import VendingMachineSystem.UtilityClasses.Inventory;
import VendingMachineSystem.UtilityClasses.Item;

import java.util.*;

public class VendingMachineContext {
    private VendingMachineState currentState;
    private Inventory inventory;
    private List<Coin> coinList;
    private int selectedItemCode;

    public VendingMachineContext() {
        inventory = new Inventory(10); // Initialize inventory with 10 slots
        coinList = new ArrayList<>(); // Initialize the coin list
        currentState = new IdleState(); // Set initial state to idle
        System.out.println("Initialized: " + currentState.getStateName());
    }

    // Gets the current state of the vending machine
    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void advanceState() {
        VendingMachineState newState = currentState.next(this);
        currentState = newState;
        System.out.println("Current state: " + currentState.getStateName());
    }

    public void clickOnInsertCoinButton(Coin coin) {
        if (currentState instanceof IdleState || currentState instanceof HasMoneyState) {
            System.out.println("Inserted " + coin.name() + " worth " + coin.value);
            coinList.add(coin); // Add the coin to the list
            advanceState(); // Move to the next state
        } else {
            System.out.println("Cannot insert coin in " + currentState.getStateName());
        }
    }

    public void clickOnStartProductSelectionButton(int codeNumber) {
        if (currentState instanceof HasMoneyState) {
            advanceState();
            selectProduct(codeNumber);
        } else {
            System.out.println("Product selection button can only be clicked in HasMoney state");
        }
    }

    public void selectProduct(int codeNumber) {
        if (currentState instanceof SelectionState) {
            try {
                Item item = inventory.getItem(codeNumber);
                int balance = getBalance();

                if (balance < item.getPrice()) {
                    System.out.println(
                            "Insufficient amount. Product price: " + item.getPrice() + ", paid: " + balance);
                    return;
                }
                setSelectedItemCode(codeNumber);
                advanceState();
                dispenseItem(codeNumber);

                if (balance >= item.getPrice()) { // Return change if applicable
                    int change = balance - item.getPrice();
                    System.out.println("Returning change: " + change);
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Products can only be selected in Selection state");
        }
    }

    public void dispenseItem(int codeNumber) {
        if (currentState instanceof DispenseState) {
            try {
                Item item = inventory.getItem(codeNumber);
                System.out.println("Dispensing: " + item.getType());
                inventory.removeItem(codeNumber); // Remove item from inventory
                inventory.updateSoldOutItem(codeNumber);

                resetBalance();
                resetSelection();
                advanceState(); // Move to the next state
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateInventory(Item item, int codeNumber) {
        if (currentState instanceof IdleState) {
            try {
                inventory.addItem(item, codeNumber);
                System.out.println("Added " + item.getType() + " to slot " + codeNumber);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Inventory can only be updated in Idle state");
        }
    }

    // Getters and setters for context properties
    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }

    public int getSelectedItemCode() {
        return selectedItemCode;
    }

    public void setSelectedItemCode(int codeNumber) {
        this.selectedItemCode = codeNumber;
    }

    // Resets the product selection
    public void resetSelection() {
        this.selectedItemCode = 0;
    }

    // Calculates the total balance from inserted coins
    public int getBalance() {
        int balance = 0;
        for (Coin coin : coinList) {
            balance += coin.value; // Sum up the coin values
        }
        return balance;
    }

    // Resets the balance by clearing all coins
    public void resetBalance() {
        coinList.clear();
    }
}
