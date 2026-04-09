package com.champsoft.vrms2432352.modules.order.api.dto;

import java.math.BigDecimal;

public record OrderItemResponse(
    String id,
    String productId,
    String productName,
    int quantity,
    BigDecimal unitPrice,
    BigDecimal lineTotal,
    int remainingInventory
) { }
