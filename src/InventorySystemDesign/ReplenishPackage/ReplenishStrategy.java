package InventorySystemDesign.ReplenishPackage;

import InventorySystemDesign.ProductPackage.Product;

public interface ReplenishStrategy {
    void replenish(Product product);
}
