package com.champsoft.vrms2432352.modules.order.infrastructure.persistence;

import com.champsoft.vrms2432352.modules.order.application.port.out.OrderRepositoryPort;
import com.champsoft.vrms2432352.modules.order.domain.model.Order;
import com.champsoft.vrms2432352.modules.order.domain.model.OrderItem;
import com.champsoft.vrms2432352.modules.order.domain.model.OrderStatus;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class JpaOrderRepositoryAdapter implements OrderRepositoryPort {
    private final SpringDataOrderRepository repository;

    public JpaOrderRepositoryAdapter(SpringDataOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        var entity = new OrderJpaEntity();
        entity.setId(order.getId());
        entity.setCustomerId(order.getCustomerId());
        entity.setTotalAmount(order.getTotalAmount());
        entity.setStatus(order.getStatus().name());
        entity.setCreatedAt(order.getCreatedAt());
        entity.setItems(order.getItems().stream().map(item -> {
            var itemEntity = new OrderItemJpaEntity();
            itemEntity.setId(item.getId());
            itemEntity.setOrder(entity);
            itemEntity.setProductId(item.getProductId());
            itemEntity.setQuantity(item.getQuantity());
            itemEntity.setUnitPrice(item.getUnitPrice());
            itemEntity.setLineTotal(item.getLineTotal());
            return itemEntity;
        }).toList());
        var saved = repository.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Optional<Order> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    private Order toDomain(OrderJpaEntity entity) {
        return Order.rehydrate(
            entity.getId(),
            entity.getCustomerId(),
            entity.getItems().stream()
                .map(item -> OrderItem.rehydrate(item.getId(), item.getProductId(), item.getQuantity(), item.getUnitPrice()))
                .toList(),
            entity.getTotalAmount(),
            OrderStatus.valueOf(entity.getStatus()),
            entity.getCreatedAt()
        );
    }
}
