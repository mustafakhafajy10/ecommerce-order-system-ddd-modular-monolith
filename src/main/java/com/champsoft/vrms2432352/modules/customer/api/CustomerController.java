package com.champsoft.vrms2432352.modules.customer.api;

import com.champsoft.vrms2432352.modules.customer.api.dto.CreateCustomerRequest;
import com.champsoft.vrms2432352.modules.customer.api.dto.CustomerResponse;
import com.champsoft.vrms2432352.modules.customer.api.dto.UpdateCustomerRequest;
import com.champsoft.vrms2432352.modules.customer.application.service.CustomerCrudService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerCrudService customerCrudService;

    public CustomerController(CustomerCrudService customerCrudService) {
        this.customerCrudService = customerCrudService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> list() {
        return ResponseEntity.ok(customerCrudService.listCustomers().stream().map(this::toResponse).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable String id) {
        return ResponseEntity.ok(toResponse(customerCrudService.getCustomer(id)));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CreateCustomerRequest request) {
        var customer = customerCrudService.createCustomer(request.name(), request.email());
        return ResponseEntity
            .created(URI.create("/api/customers/" + customer.getId()))
            .body(toResponse(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable String id, @Valid @RequestBody UpdateCustomerRequest request) {
        return ResponseEntity.ok(toResponse(customerCrudService.updateCustomer(id, request.name(), request.email())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        customerCrudService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    private CustomerResponse toResponse(com.champsoft.vrms2432352.modules.customer.domain.model.Customer customer) {
        return new CustomerResponse(customer.getId(), customer.getName(), customer.getEmail());
    }
}
