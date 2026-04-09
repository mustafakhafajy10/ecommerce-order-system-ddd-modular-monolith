package com.champsoft.vrms2432352.modules.product.application.service;

import com.champsoft.vrms2432352.modules.product.domain.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductCatalogService {
    private final ProductCrudService productCrudService;

    public ProductCatalogService(ProductCrudService productCrudService) {
        this.productCrudService = productCrudService;
    }

    public Product getRequired(String productId) {
        return productCrudService.getProduct(productId);
    }
}
