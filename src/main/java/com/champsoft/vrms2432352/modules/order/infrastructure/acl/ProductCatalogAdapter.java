package com.champsoft.vrms2432352.modules.order.infrastructure.acl;

import com.champsoft.vrms2432352.modules.order.application.port.out.ProductCatalogPort;
import com.champsoft.vrms2432352.modules.product.application.service.ProductCatalogService;
import org.springframework.stereotype.Component;

@Component
public class ProductCatalogAdapter implements ProductCatalogPort {
    private final ProductCatalogService productCatalogService;

    public ProductCatalogAdapter(ProductCatalogService productCatalogService) {
        this.productCatalogService = productCatalogService;
    }

    @Override
    public OrderProductView getRequiredProduct(String productId) {
        var product = productCatalogService.getRequired(productId);
        return new OrderProductView(product.getId(), product.getName(), product.getPrice());
    }
}
