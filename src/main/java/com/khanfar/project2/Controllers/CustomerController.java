package com.khanfar.project2.Controllers;

import com.khanfar.project2.Entity.Customer;
import com.khanfar.project2.Service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.khanfar.project2.Exception.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

@Autowired
    private  CustomerService customerService ;



    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        System.out.println("testtt");
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
        return ResponseEntity.ok(customer);
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer createdCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer id, @Validated @RequestBody Customer customer) {
        Customer existingCustomer = customerService.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

        // Update existingCustomer properties with customer request body

        Customer updatedCustomer = customerService.saveCustomer(existingCustomer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
