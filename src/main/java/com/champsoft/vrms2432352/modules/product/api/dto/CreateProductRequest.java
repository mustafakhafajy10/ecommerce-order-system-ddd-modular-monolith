package com.champsoft.vrms2432352.modules.product.api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record CreateProductRequest(
    @NotBlank String name,
    @DecimalMin(value = "0.01") BigDecimal price
) { }
