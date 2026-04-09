package com.champsoft.vrms2432352.modules.inventory.application.exception;

import com.champsoft.vrms2432352.shared.web.ConflictException;

public class DuplicateInventoryItemException extends ConflictException {
    public DuplicateInventoryItemException(String productId) {
        super("Inventory item already exists for product: " + productId);
    }
}
