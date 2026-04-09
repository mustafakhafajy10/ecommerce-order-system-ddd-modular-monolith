package com.champsoft.vrms2432352.modules.inventory.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateInventoryItemRequest(
    @NotBlank String productId,
    @Min(0) int quantity
) { }
