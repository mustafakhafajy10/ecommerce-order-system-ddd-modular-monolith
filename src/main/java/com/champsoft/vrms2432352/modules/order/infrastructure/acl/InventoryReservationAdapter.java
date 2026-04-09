package com.champsoft.vrms2432352.modules.order.infrastructure.acl;

import com.champsoft.vrms2432352.modules.inventory.application.service.InventoryCrudService;
import com.champsoft.vrms2432352.modules.order.application.port.out.InventoryReservationPort;
import org.springframework.stereotype.Component;

@Component
public class InventoryReservationAdapter implements InventoryReservationPort {
    private final InventoryCrudService inventoryCrudService;

    public InventoryReservationAdapter(InventoryCrudService inventoryCrudService) {
        this.inventoryCrudService = inventoryCrudService;
    }

    @Override
    public void reserve(String productId, int quantity) {
        inventoryCrudService.reserveStock(productId, quantity);
    }

    @Override
    public int getAvailableQuantity(String productId) {
        return inventoryCrudService.getInventoryByProductId(productId).getQuantity();
    }
}
