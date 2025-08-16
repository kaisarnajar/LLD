package InventorySystemDesign;

import InventorySystemDesign.Enum.ProductCategory;
import InventorySystemDesign.ProductPackage.Product;
import InventorySystemDesign.ProductPackage.ProductFactory;
import InventorySystemDesign.ReplenishPackage.BulkOrderStrategy;
import InventorySystemDesign.ReplenishPackage.JustInTimeStrategy;
import InventorySystemDesign.ReplenishPackage.ReplenishStrategy;

public class Main {
    public static void main(String[] args) {
        ReplenishStrategy replenishStrategy = new JustInTimeStrategy();
        InventoryManager inventoryManager = InventoryManager.getInstance(replenishStrategy);

        Warehouse warehouseOne = new Warehouse("WareHouse One");
        Warehouse warehouseTwo = new Warehouse("Warehouse Two");
        inventoryManager.addWarehouse(warehouseOne);
        inventoryManager.addWarehouse(warehouseTwo);

        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createProduct(
                ProductCategory.ELECTRONICS, "SKU123", "Laptop", 1000.0, 50, 25);
        Product tShirt = productFactory.createProduct(
                ProductCategory.CLOTHING, "SKU456", "T-Shirt", 20.0, 200, 100);
        Product apple = productFactory.createProduct(
                ProductCategory.GROCERY, "SKU789", "Apple", 1.0, 100, 200);

        warehouseOne.addProduct(laptop, 10);
        warehouseOne.addProduct(tShirt, 20);
        warehouseOne.addProduct(apple, 40);

        inventoryManager.setReplenishmentStrategy(new JustInTimeStrategy());

        // Perform inventory check and replenish if needed
        inventoryManager.performInventoryCheck();

        inventoryManager.setReplenishmentStrategy(new BulkOrderStrategy());

        // Replenish a specific product if needed
        inventoryManager.checkAndReplenish("SKU123");
    }
}
