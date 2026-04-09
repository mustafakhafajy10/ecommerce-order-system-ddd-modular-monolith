package com.champsoft.vrms2432352.modules.product.domain.model;

import com.champsoft.vrms2432352.shared.web.BadRequestException;
import java.math.BigDecimal;

public record Money(BigDecimal value) {
    public Money {
        if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BadRequestException("Product price must be greater than 0");
        }
    }
}
