package com.champsoft.vrms2432352.modules.customer.application.service;

import com.champsoft.vrms2432352.modules.customer.domain.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerLookupService {
    private final CustomerCrudService customerCrudService;

    public CustomerLookupService(CustomerCrudService customerCrudService) {
        this.customerCrudService = customerCrudService;
    }

    public boolean exists(String customerId) {
        try {
            customerCrudService.getCustomer(customerId);
            return true;
        } catch (RuntimeException ex) {
            return false;
        }
    }

    public Customer getRequired(String customerId) {
        return customerCrudService.getCustomer(customerId);
    }
}
