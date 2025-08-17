package VendingMachineSystem.UtilityClasses;

public class Inventory {
    ItemShelf[] inventory = null;

    public Inventory(int itemCount) {
        inventory = new ItemShelf[itemCount];
        initialEmptyInventory();
    }

    // Getter for the inventory array
    public ItemShelf[] getInventory() {
        return inventory;
    }

    // Setter for the inventory array
    public void setInventory(ItemShelf[] inventory) {
        this.inventory = inventory;
    }

    public void initialEmptyInventory() {
        int startCode = 101;

        for (int i = 0; i < inventory.length; i++) {
            inventory[i] = new ItemShelf(startCode++);
        }
    }

    public void addItem(Item item, int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                itemShelf.addItem(item);
            }
        }
        throw new Exception("Invalid Code");
    }

    public Item getItem(int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                if (itemShelf.checkIsSoldOut()) {
                    throw new Exception("Item is already sold out");
                } else {
                    Item item = itemShelf.getItems().get(0);
                    return item;
                }
            }
        }
        throw new Exception("Invalid code");
    }

    public void updateSoldOutItem(int codeNumber) {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                if (itemShelf.getItems().isEmpty())
                    itemShelf.setIsSoldOut(true); // Mark the shelf as sold out
            }
        }
    }

    public void removeItem(int codeNumber) throws Exception {
        for (ItemShelf itemShelf : inventory) {
            if (itemShelf.getCode() == codeNumber) {
                itemShelf.removeItem(
                        itemShelf.getItems().getFirst()); // Remove the specific item from the shelf
                return;
            }
        }
        throw new Exception("Invalid Code");
    }

    public boolean hasItems() {
        for (ItemShelf itemShelf : inventory) {
            if (!itemShelf.checkIsSoldOut()) return true;
        }
        return false;
    }
}
