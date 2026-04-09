package com.champsoft.vrms2432352.modules.customer.application.service;

import com.champsoft.vrms2432352.modules.customer.application.exception.CustomerNotFoundException;
import com.champsoft.vrms2432352.modules.customer.application.exception.DuplicateCustomerException;
import com.champsoft.vrms2432352.modules.customer.application.port.out.CustomerRepositoryPort;
import com.champsoft.vrms2432352.modules.customer.domain.model.Customer;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerCrudService {
    private final CustomerRepositoryPort repository;

    public CustomerCrudService(CustomerRepositoryPort repository) {
        this.repository = repository;
    }

    @Transactional
    public Customer createCustomer(String name, String email) {
        repository.findByEmail(email).ifPresent(existing -> {
            throw new DuplicateCustomerException(email);
        });
        return repository.save(Customer.create(name, email));
    }

    @Transactional(readOnly = true)
    public List<Customer> listCustomers() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Customer getCustomer(String id) {
        return repository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional
    public Customer updateCustomer(String id, String name, String email) {
        var customer = getCustomer(id);
        repository.findByEmail(email)
            .filter(existing -> !existing.getId().equals(id))
            .ifPresent(existing -> {
                throw new DuplicateCustomerException(email);
            });
        customer.update(name, email);
        return repository.save(customer);
    }

    @Transactional
    public void deleteCustomer(String id) {
        getCustomer(id);
        repository.deleteById(id);
    }
}
