package com.champsoft.vrms2432352.modules.order.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {
    private final String id;
    private final String customerId;
    private final List<OrderItem> items;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private final Instant createdAt;

    private Order(
        String id,
        String customerId,
        List<OrderItem> items,
        BigDecimal totalAmount,
        OrderStatus status,
        Instant createdAt
    ) {
        if (customerId == null || customerId.isBlank()) {
            throw new BadRequestException("Order customerId is required");
        }
        if (items == null || items.isEmpty()) {
            throw new BadRequestException("An order must contain at least one item");
        }
        if (totalAmount == null || totalAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Order total amount must be greater than 0");
        }
        this.id = id;
        this.customerId = customerId;
        this.items = new ArrayList<>(items);
        this.totalAmount = totalAmount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Order create(String customerId, List<OrderItem> items) {
        var total = items.stream()
            .map(OrderItem::getLineTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Order(UUID.randomUUID().toString(), customerId, items, total, OrderStatus.CONFIRMED, Instant.now());
    }

    public static Order rehydrate(
        String id,
        String customerId,
        List<OrderItem> items,
        BigDecimal totalAmount,
        OrderStatus status,
        Instant createdAt
    ) {
        return new Order(id, customerId, items, totalAmount, status, createdAt);
    }

    public void updateStatus(OrderStatus newStatus) {
        if (newStatus == null) {
            throw new BadRequestException("Order status is required");
        }
        if (status == OrderStatus.CANCELLED && newStatus != OrderStatus.CANCELLED) {
            throw new BadRequestException("Cancelled orders cannot transition to another status");
        }
        this.status = newStatus;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
