package com.champsoft.vrms2432352.modules.order.api.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
    String id,
    OrderCustomerSummary customer,
    List<OrderItemResponse> items,
    BigDecimal totalAmount,
    String status,
    Instant createdAt
) { }
