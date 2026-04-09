package com.champsoft.vrms2432352.modules.order.application.port.out;

public interface InventoryReservationPort {
    void reserve(String productId, int quantity);
    int getAvailableQuantity(String productId);
}
