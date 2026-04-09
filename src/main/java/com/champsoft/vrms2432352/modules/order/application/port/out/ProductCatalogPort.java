package com.champsoft.vrms2432352.modules.order.application.port.out;

import java.math.BigDecimal;

public interface ProductCatalogPort {
    OrderProductView getRequiredProduct(String productId);

    record OrderProductView(String id, String name, BigDecimal price) { }
}
