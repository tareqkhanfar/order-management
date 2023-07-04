package com.khanfar.project2.Controllers;

import com.khanfar.project2.DTO.CustomerDTO;
import com.khanfar.project2.Service.CustomerService;
import com.khanfar.project2.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        CustomerDTO customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Validated @RequestBody CustomerDTO customer) {

        CustomerDTO createdCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Integer id, @Validated @RequestBody CustomerDTO customer) {
        CustomerDTO existingCustomer = customerService.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

        // Update existingCustomer properties with customer request body

        CustomerDTO updatedCustomer = customerService.saveCustomer(existingCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
