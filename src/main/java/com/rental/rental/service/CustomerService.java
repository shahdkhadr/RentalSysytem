package com.rental.rental.service;

import com.rental.rental.dto.CustomerDTO;
import com.rental.rental.model.Customer;
import com.rental.rental.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ReservationService reservationService;

    public List<CustomerDTO> getAllCustomers(){
        return customerRepository.
                findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(int id){
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return convertToDTO(customer);
    }
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setCustomerName(customerDTO.getCustomerName());
        existingCustomer.setCustomerAddress(customerDTO.getCustomerAddress());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        existingCustomer.setDriverLicense(customerDTO.getDriverLicense());
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return convertToDTO(updatedCustomer);

    }
    public void deleteCustomer(int id) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(existingCustomer);

    }
//    public List<ReservationDTO> getCustomerReservations(int customerId) {
//        Customer customer = customerRepository.findById(customerId)
//                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
//
//        return customer.getReservation().stream()
//                .map(reservation -> reservationService.convertToDTO(reservation))
//                .collect(Collectors.toList());
//    }
    private CustomerDTO convertToDTO(Customer customer){
        return CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .customerName(customer.getCustomerName())
                .customerAddress(customer.getCustomerAddress())
                .phoneNumber(customer.getPhoneNumber())
                .driverLicense(customer.getDriverLicense())
                .notificationIds(customer.getNotifications() != null
                        ? customer.getNotifications().stream()
                        .map(notification -> notification.getNotificationId())
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .reservationIds(customer.getReservation() != null
                        ? customer.getReservation().stream()
                        .map(reservation -> reservation.getReservationId())
                        .collect(Collectors.toList())
                        : new ArrayList<>())
                .build();
    }
    private Customer convertToEntity(CustomerDTO customerDTO){
        Customer customer = Customer.builder()
                .customerId(customerDTO.getCustomerId())
                .customerName(customerDTO.getCustomerName())
                .customerAddress(customerDTO.getCustomerAddress())
                .phoneNumber(customerDTO.getPhoneNumber())
                .driverLicense(customerDTO.getDriverLicense())
                .build();
        return customer;
    }


}
