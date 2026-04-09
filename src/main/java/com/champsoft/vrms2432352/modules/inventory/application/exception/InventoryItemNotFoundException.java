package com.champsoft.vrms2432352.modules.inventory.application.exception;

import com.champsoft.vrms2432352.shared.web.NotFoundException;

public class InventoryItemNotFoundException extends NotFoundException {
    public InventoryItemNotFoundException(String id) {
        super("Inventory item not found: " + id);
    }
}
