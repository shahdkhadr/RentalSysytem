package com.rental.rental.service;

import com.rental.rental.dto.ServiceDTO;
import com.rental.rental.model._Service;
import com.rental.rental.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ServiceDTO getServiceById(int id) {
        _Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
        return convertToDTO(service);
    }

    public ServiceDTO createService(ServiceDTO serviceDTO) {
        _Service service = convertToEntity(serviceDTO);
        _Service savedService = serviceRepository.save(service);
        return convertToDTO(savedService);
    }

    public ServiceDTO updateService(int id, ServiceDTO serviceDTO) {
        _Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));

        existingService.setServiceName(serviceDTO.getServiceName());
        existingService.setServiceCost(serviceDTO.getServiceCost());

        _Service updatedService = serviceRepository.save(existingService);
        return convertToDTO(updatedService);
    }

    public void deleteService(int id) {
        _Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found with id: " + id));
        serviceRepository.delete(service);
    }

    private ServiceDTO convertToDTO(_Service service) {
        return ServiceDTO.builder()
                .serviceId(service.getServiceId())
                .serviceName(service.getServiceName())
                .serviceCost(service.getServiceCost())
                .build();
    }

    private _Service convertToEntity(ServiceDTO serviceDTO) {
        return _Service.builder()
                .serviceId(serviceDTO.getServiceId())
                .serviceName(serviceDTO.getServiceName())
                .serviceCost(serviceDTO.getServiceCost())
                .build();
    }
}
