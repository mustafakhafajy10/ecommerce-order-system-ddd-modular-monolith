package com.champsoft.vrms2432352.modules.customer.application.exception;

import com.champsoft.vrms2432352.shared.web.ConflictException;

public class DuplicateCustomerException extends ConflictException {
    public DuplicateCustomerException(String email) {
        super("Customer email already exists: " + email);
    }
}
