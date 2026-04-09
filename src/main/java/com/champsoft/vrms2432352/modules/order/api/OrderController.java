package com.champsoft.vrms2432352.modules.order.api;

import com.champsoft.vrms2432352.modules.order.api.dto.CreateOrderRequest;
import com.champsoft.vrms2432352.modules.order.api.dto.OrderResponse;
import com.champsoft.vrms2432352.modules.order.api.dto.UpdateOrderRequest;
import com.champsoft.vrms2432352.modules.order.application.service.OrderOrchestrator;
import com.champsoft.vrms2432352.modules.order.domain.model.OrderStatus;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderOrchestrator orderOrchestrator;
    private final OrderRepresentationAssembler assembler;

    public OrderController(OrderOrchestrator orderOrchestrator, OrderRepresentationAssembler assembler) {
        this.orderOrchestrator = orderOrchestrator;
        this.assembler = assembler;
    }

    @GetMapping
    public CollectionModel<EntityModel<OrderResponse>> list() {
        return assembler.toCollection(orderOrchestrator.listOrders());
    }

    @GetMapping("/{id}")
    public EntityModel<OrderResponse> getById(@PathVariable String id) {
        return assembler.toModel(orderOrchestrator.getOrder(id));
    }

    @PostMapping
    public ResponseEntity<EntityModel<OrderResponse>> create(@Valid @RequestBody CreateOrderRequest request) {
        var response = orderOrchestrator.createOrder(request);
        return ResponseEntity
            .created(URI.create("/api/orders/" + response.id()))
            .body(assembler.toModel(response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<OrderResponse>> update(
        @PathVariable String id,
        @Valid @RequestBody UpdateOrderRequest request
    ) {
        var response = orderOrchestrator.updateStatus(id, OrderStatus.valueOf(request.status().toUpperCase()));
        return ResponseEntity.ok(assembler.toModel(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        orderOrchestrator.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
