package com.champsoft.vrms2432352.modules.order.application.service;

import com.champsoft.vrms2432352.modules.order.api.dto.CreateOrderRequest;
import com.champsoft.vrms2432352.modules.order.api.dto.OrderCustomerSummary;
import com.champsoft.vrms2432352.modules.order.api.dto.OrderItemResponse;
import com.champsoft.vrms2432352.modules.order.api.dto.OrderResponse;
import com.champsoft.vrms2432352.modules.order.application.port.out.CustomerValidationPort;
import com.champsoft.vrms2432352.modules.order.application.port.out.InventoryReservationPort;
import com.champsoft.vrms2432352.modules.order.application.port.out.ProductCatalogPort;
import com.champsoft.vrms2432352.modules.order.domain.model.Order;
import com.champsoft.vrms2432352.modules.order.domain.model.OrderItem;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderOrchestrator {
    private final CustomerValidationPort customerValidationPort;
    private final ProductCatalogPort productCatalogPort;
    private final InventoryReservationPort inventoryReservationPort;
    private final OrderCrudService orderCrudService;

    public OrderOrchestrator(
        CustomerValidationPort customerValidationPort,
        ProductCatalogPort productCatalogPort,
        InventoryReservationPort inventoryReservationPort,
        OrderCrudService orderCrudService
    ) {
        this.customerValidationPort = customerValidationPort;
        this.productCatalogPort = productCatalogPort;
        this.inventoryReservationPort = inventoryReservationPort;
        this.orderCrudService = orderCrudService;
    }

    @Transactional
    public OrderResponse createOrder(CreateOrderRequest request) {
        var customer = customerValidationPort.getRequiredCustomer(request.customerId());
        List<OrderItem> items = request.items().stream().map(itemRequest -> {
            var product = productCatalogPort.getRequiredProduct(itemRequest.productId());
            inventoryReservationPort.reserve(product.id(), itemRequest.quantity());
            return OrderItem.create(product.id(), itemRequest.quantity(), product.price());
        }).toList();
        Order order = orderCrudService.save(Order.create(customer.id(), items));
        return toResponse(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> listOrders() {
        return orderCrudService.listOrders().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrder(String id) {
        return toResponse(orderCrudService.getOrder(id));
    }

    public OrderResponse updateStatus(String id, com.champsoft.vrms2432352.modules.order.domain.model.OrderStatus status) {
        return toResponse(orderCrudService.updateStatus(id, status));
    }

    public void deleteOrder(String id) {
        orderCrudService.deleteOrder(id);
    }

    private OrderResponse toResponse(Order order) {
        var customer = customerValidationPort.getRequiredCustomer(order.getCustomerId());
        var itemResponses = order.getItems().stream().map(item -> {
            var product = productCatalogPort.getRequiredProduct(item.getProductId());
            var availableQuantity = inventoryReservationPort.getAvailableQuantity(item.getProductId());
            return new OrderItemResponse(
                item.getId(),
                item.getProductId(),
                product.name(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getLineTotal(),
                availableQuantity
            );
        }).toList();

        return new OrderResponse(
            order.getId(),
            new OrderCustomerSummary(customer.id(), customer.name(), customer.email()),
            itemResponses,
            order.getTotalAmount(),
            order.getStatus().name(),
            order.getCreatedAt()
        );
    }
}
