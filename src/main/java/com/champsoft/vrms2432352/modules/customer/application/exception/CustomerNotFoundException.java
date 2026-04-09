package com.champsoft.vrms2432352.modules.customer.application.exception;

import com.champsoft.vrms2432352.shared.web.NotFoundException;

public class CustomerNotFoundException extends NotFoundException {
    public CustomerNotFoundException(String id) {
        super("Customer not found: " + id);
    }
}
