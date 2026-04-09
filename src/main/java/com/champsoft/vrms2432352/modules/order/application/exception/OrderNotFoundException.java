package com.champsoft.vrms2432352.modules.order.application.exception;

import com.champsoft.vrms2432352.shared.web.NotFoundException;

public class OrderNotFoundException extends NotFoundException {
    public OrderNotFoundException(String id) {
        super("Order not found: " + id);
    }
}
