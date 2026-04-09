package com.champsoft.vrms2432352.modules.inventory.api.dto;

import jakarta.validation.constraints.Min;

public record UpdateInventoryItemRequest(
    @Min(0) int quantity
) { }
