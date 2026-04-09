package com.champsoft.vrms2432352.modules.order.infrastructure.acl;

import com.champsoft.vrms2432352.modules.customer.application.service.CustomerLookupService;
import com.champsoft.vrms2432352.modules.order.application.port.out.CustomerValidationPort;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidationAdapter implements CustomerValidationPort {
    private final CustomerLookupService customerLookupService;

    public CustomerValidationAdapter(CustomerLookupService customerLookupService) {
        this.customerLookupService = customerLookupService;
    }

    @Override
    public OrderCustomerView getRequiredCustomer(String customerId) {
        var customer = customerLookupService.getRequired(customerId);
        return new OrderCustomerView(customer.getId(), customer.getName(), customer.getEmail());
    }
}
