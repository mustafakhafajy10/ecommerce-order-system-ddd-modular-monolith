package com.champsoft.vrms2432352.modules.order.application.port.out;

public interface CustomerValidationPort {
    OrderCustomerView getRequiredCustomer(String customerId);

    record OrderCustomerView(String id, String name, String email) { }
}
