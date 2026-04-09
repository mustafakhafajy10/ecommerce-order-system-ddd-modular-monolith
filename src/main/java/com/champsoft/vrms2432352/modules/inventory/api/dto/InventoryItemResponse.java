package com.champsoft.vrms2432352.modules.inventory.api.dto;

public record InventoryItemResponse(
    String id,
    String productId,
    int quantity
) { }
