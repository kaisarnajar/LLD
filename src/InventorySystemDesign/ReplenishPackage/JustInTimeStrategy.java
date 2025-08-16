package InventorySystemDesign.ReplenishPackage;

import InventorySystemDesign.ProductPackage.Product;

public class JustInTimeStrategy implements ReplenishStrategy {

    @Override
    public void replenish(Product product) {
        System.out.println("Applying Just-In-Time replenishment for " + product.getName());
    }
}
