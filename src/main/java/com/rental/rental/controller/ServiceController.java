package com.rental.rental.controller;

import com.rental.rental.dto.ServiceDTO;
import com.rental.rental.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car-rental-system")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/services")
    public ResponseEntity<List<ServiceDTO>> getAllServices() {
        List<ServiceDTO> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/service")
    public ResponseEntity<ServiceDTO> getServiceById(@RequestParam int serviceId) {
        ServiceDTO service = serviceService.getServiceById(serviceId);
        return ResponseEntity.ok(service);
    }

    @PostMapping("/service")
    public ResponseEntity<ServiceDTO> createService(@RequestBody ServiceDTO serviceDTO) {
        ServiceDTO createdService = serviceService.createService(serviceDTO);
        return ResponseEntity.ok(createdService);
    }

    @PutMapping("/service")
    public ResponseEntity<ServiceDTO> updateService(@RequestParam int serviceId, @RequestBody ServiceDTO serviceDTO) {
        ServiceDTO updatedService = serviceService.updateService(serviceId, serviceDTO);
        return ResponseEntity.ok(updatedService);
    }

    @DeleteMapping("/service")
    public ResponseEntity<String> deleteService(@RequestParam int serviceId) {
        serviceService.deleteService(serviceId);
        return ResponseEntity.ok("Service with ID " + serviceId + " has been deleted successfully.");
    }
}
