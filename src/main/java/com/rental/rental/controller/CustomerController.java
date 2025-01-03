package com.rental.rental.controller;

import com.rental.rental.dto.CustomerDTO;
import com.rental.rental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-rental-system")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers(){
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @GetMapping("/customer")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CustomerDTO> getCustomer(@RequestParam int customerId){
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }

    @PostMapping("/customer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @PutMapping("/customer")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestParam int customerId, @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerId, customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customer")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteCustomer(@RequestParam int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer with ID " + customerId + " has been deleted successfully.");
    }
}
