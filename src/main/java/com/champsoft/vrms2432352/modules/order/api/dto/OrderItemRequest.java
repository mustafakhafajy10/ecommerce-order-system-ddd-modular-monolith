package com.champsoft.vrms2432352.modules.order.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record OrderItemRequest(
    @NotBlank String productId,
    @Min(1) int quantity
) { }
