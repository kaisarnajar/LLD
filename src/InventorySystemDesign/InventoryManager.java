package InventorySystemDesign;

import InventorySystemDesign.ProductPackage.Product;
import InventorySystemDesign.ProductPackage.ProductFactory;
import InventorySystemDesign.ReplenishPackage.ReplenishStrategy;

import java.util.*;

public class InventoryManager {
    private static InventoryManager inventoryManager;

    private List<Warehouse> warehouses;
    private ProductFactory productFactory;
    private ReplenishStrategy replenishmentStrategy;

    private InventoryManager(ReplenishStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
        warehouses = new ArrayList<>();
        productFactory = new ProductFactory();
    }

    public static synchronized InventoryManager getInstance(ReplenishStrategy replenishmentStrategy) {
        if (inventoryManager == null) {
            inventoryManager = new InventoryManager(replenishmentStrategy);
        }
        return inventoryManager;
    }

    public void setReplenishmentStrategy(ReplenishStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
    }

    public void addWarehouse(Warehouse warehouse) {
        warehouses.add(warehouse);
    }

    public void removeWarehouse(Warehouse warehouse) {
        warehouses.remove(warehouse);
    }

    public Product getProductBySku(String sku) {
        for (Warehouse warehouse : warehouses) {
            Product product = warehouse.getProductBySku(sku);
            if (product != null)
                return product;
        }
        return null;
    }

    public void checkAndReplenish(String sku) {
        Product product = getProductBySku(sku);
        if (product != null) {
            if (product.getQuantity() < product.getThreshold()) {
                replenishmentStrategy.replenish(product);
            }
        }
    }

    public void performInventoryCheck() {
        for (Warehouse warehouse : warehouses) {
            for (Product product : warehouse.getAllProducts()) {
                if (product.getQuantity() < product.getThreshold()) {
                    replenishmentStrategy.replenish(product);
                }
            }
        }
    }
}
