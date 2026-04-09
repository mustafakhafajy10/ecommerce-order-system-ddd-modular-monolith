package com.champsoft.vrms2432352.modules.product.api.dto;

import java.math.BigDecimal;

public record ProductResponse(
    String id,
    String name,
    BigDecimal price
) { }
