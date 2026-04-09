package com.champsoft.vrms2432352.modules.order.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private final String id;
    private final String productId;
    private final int quantity;
    private final BigDecimal unitPrice;
    private final BigDecimal lineTotal;

    private OrderItem(String id, String productId, int quantity, BigDecimal unitPrice) {
        if (productId == null || productId.isBlank()) {
            throw new BadRequestException("Order item productId is required");
        }
        if (quantity <= 0) {
            throw new BadRequestException("Order item quantity must be greater than 0");
        }
        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Order item price must be greater than 0");
        }
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public static OrderItem create(String productId, int quantity, BigDecimal unitPrice) {
        return new OrderItem(UUID.randomUUID().toString(), productId, quantity, unitPrice);
    }

    public static OrderItem rehydrate(String id, String productId, int quantity, BigDecimal unitPrice) {
        return new OrderItem(id, productId, quantity, unitPrice);
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }
}
