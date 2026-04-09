package com.champsoft.vrms2432352.modules.product.application.exception;

import com.champsoft.vrms2432352.shared.web.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(String id) {
        super("Product not found: " + id);
    }
}
