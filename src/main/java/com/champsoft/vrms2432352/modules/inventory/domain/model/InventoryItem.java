package com.champsoft.vrms2432352.modules.inventory.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;
import com.champsoft.vrms2432352.shared.web.ConflictException;
import java.util.UUID;

public class InventoryItem {
    private final String id;
    private final String productId;
    private Quantity quantity;

    private InventoryItem(String id, String productId, int quantity) {
        if (productId == null || productId.isBlank()) {
            throw new BadRequestException("Inventory item must reference a product");
        }
        this.id = id;
        this.productId = productId;
        this.quantity = new Quantity(quantity);
    }

    public static InventoryItem create(String productId, int quantity) {
        return new InventoryItem(UUID.randomUUID().toString(), productId, quantity);
    }

    public static InventoryItem rehydrate(String id, String productId, int quantity) {
        return new InventoryItem(id, productId, quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = new Quantity(quantity);
    }

    public void reserve(int quantityToReserve) {
        if (quantityToReserve <= 0) {
            throw new BadRequestException("Reservation quantity must be greater than 0");
        }
        if (quantity.value() < quantityToReserve) {
            throw new ConflictException("Not enough inventory for product " + productId);
        }
        this.quantity = new Quantity(quantity.value() - quantityToReserve);
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity.value();
    }
}
