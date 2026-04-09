package com.champsoft.vrms2432352.modules.order.application.service;

import com.champsoft.vrms2432352.modules.order.application.exception.OrderNotFoundException;
import com.champsoft.vrms2432352.modules.order.application.port.out.OrderRepositoryPort;
import com.champsoft.vrms2432352.modules.order.domain.model.Order;
import com.champsoft.vrms2432352.modules.order.domain.model.OrderStatus;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderCrudService {
    private final OrderRepositoryPort orderRepository;

    public OrderCrudService(OrderRepositoryPort orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrder(String id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional
    public Order updateStatus(String id, OrderStatus status) {
        var order = getOrder(id);
        order.updateStatus(status);
        return orderRepository.save(order);
    }

    @Transactional
    public void deleteOrder(String id) {
        getOrder(id);
        orderRepository.deleteById(id);
    }
}
