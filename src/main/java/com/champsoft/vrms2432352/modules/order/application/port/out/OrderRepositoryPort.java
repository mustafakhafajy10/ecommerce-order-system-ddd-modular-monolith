package com.champsoft.vrms2432352.modules.order.application.port.out;

import com.champsoft.vrms2432352.modules.order.domain.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepositoryPort {
    Order save(Order order);
    List<Order> findAll();
    Optional<Order> findById(String id);
    void deleteById(String id);
}
