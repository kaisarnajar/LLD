package InventorySystemDesign.ReplenishPackage;

import InventorySystemDesign.ProductPackage.Product;

public class BulkOrderStrategy implements ReplenishStrategy {
    @Override
    public void replenish(Product product) {
        // Implement Bulk Order replenishment logic
        System.out.println("Applying Bulk Order replenishment for " + product.getName());
        // Order in large quantities to minimize order costs
    }
}