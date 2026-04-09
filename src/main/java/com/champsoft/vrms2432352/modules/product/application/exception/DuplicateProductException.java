package com.champsoft.vrms2432352.modules.product.application.exception;

import com.champsoft.vrms2432352.shared.web.ConflictException;

public class DuplicateProductException extends ConflictException {
    public DuplicateProductException(String name) {
        super("Product already exists: " + name);
    }
}
