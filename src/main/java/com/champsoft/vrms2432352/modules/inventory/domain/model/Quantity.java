package com.champsoft.vrms2432352.modules.inventory.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;

public record Quantity(int value) {
    public Quantity {
        if (value < 0) {
            throw new BadRequestException("Inventory quantity must be greater than or equal to 0");
        }
    }
}
