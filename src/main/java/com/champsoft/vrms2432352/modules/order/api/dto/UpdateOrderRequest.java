package com.champsoft.vrms2432352.modules.order.api.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateOrderRequest(
    @NotBlank String status
) { }
