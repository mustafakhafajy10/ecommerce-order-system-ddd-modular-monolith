package com.champsoft.vrms2432352.modules.inventory.application.port.out;

import com.champsoft.vrms2432352.modules.inventory.domain.model.InventoryItem;
import java.util.List;
import java.util.Optional;

public interface InventoryRepositoryPort {
    InventoryItem save(InventoryItem inventoryItem);
    List<InventoryItem> findAll();
    Optional<InventoryItem> findById(String id);
    Optional<InventoryItem> findByProductId(String productId);
    void deleteById(String id);
}
