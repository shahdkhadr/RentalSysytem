package com.rental.rental.service;

import com.rental.rental.dto.VehicleDTO;
import com.rental.rental.model.Vehicle;
import com.rental.rental.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<VehicleDTO> getAllVehicles() {
        return vehicleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public VehicleDTO getVehicleById(int vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + vehicleId));
        return convertToDTO(vehicle);
    }

    public VehicleDTO createVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = convertToEntity(vehicleDTO);
        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return convertToDTO(savedVehicle);
    }

    public VehicleDTO updateVehicle(int vehicleId, VehicleDTO vehicleDTO) {
        Vehicle existingVehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + vehicleId));

        existingVehicle.setVehicleName(vehicleDTO.getVehicleName());
        existingVehicle.setVehicleStatus(vehicleDTO.getVehicleStatus());
        existingVehicle.setFuelType(vehicleDTO.getFuelType());
        existingVehicle.setEngineCapacity(vehicleDTO.getEngineCapacity());
        existingVehicle.setHasAirConditioning(vehicleDTO.isHasAirConditioning());
        existingVehicle.setModel(vehicleDTO.getModel());
        existingVehicle.setManufacturer(vehicleDTO.getManufacturer());
        existingVehicle.setPlateNumber(vehicleDTO.getPlateNumber());
        existingVehicle.setLongitude(vehicleDTO.getLongitude());
        existingVehicle.setLatitude(vehicleDTO.getLatitude());

        // Convert VehicleDTO.VehicleType to Vehicle.VehicleType
        existingVehicle.setDtype(Vehicle.VehicleType.valueOf(vehicleDTO.getDtype().name()));

        Vehicle updatedVehicle = vehicleRepository.save(existingVehicle);
        return convertToDTO(updatedVehicle);
    }

    public void deleteVehicle(int vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + vehicleId));
        vehicleRepository.delete(vehicle);
    }

    private VehicleDTO convertToDTO(Vehicle vehicle) {
        return VehicleDTO.builder()
                .vehicleId(vehicle.getVehicleId())
                .vehicleName(vehicle.getVehicleName())
                .vehicleStatus(vehicle.getVehicleStatus())
                .fuelType(vehicle.getFuelType())
                .engineCapacity(vehicle.getEngineCapacity())
                .hasAirConditioning(vehicle.isHasAirConditioning())
                .model(vehicle.getModel())
                .manufacturer(vehicle.getManufacturer())
                .plateNumber(vehicle.getPlateNumber())
                .longitude(vehicle.getLongitude())
                .latitude(vehicle.getLatitude())
                .dtype(VehicleDTO.VehicleType.valueOf(vehicle.getDtype().name())) // Convert Vehicle.VehicleType to VehicleDTO.VehicleType
                .rentalId(vehicle.getRental() != null ? vehicle.getRental().getRentalId() : null)
                .reservationId(vehicle.getReservation() != null ? vehicle.getReservation().getReservationId() : null)
                .stallId(vehicle.getParkingStall() != null ? vehicle.getParkingStall().getStallId() : null)
                //.vehicleCheckIds(vehicle.getVehicleChecks().stream().map(vehicleCheck -> vehicleCheck.getVehicleCheckId()).collect(Collectors.toList()))
                .build();
    }

    private Vehicle convertToEntity(VehicleDTO vehicleDTO) {
        return Vehicle.builder()
                .vehicleId(vehicleDTO.getVehicleId())
                .vehicleName(vehicleDTO.getVehicleName())
                .vehicleStatus(vehicleDTO.getVehicleStatus())
                .fuelType(vehicleDTO.getFuelType())
                .engineCapacity(vehicleDTO.getEngineCapacity())
                .hasAirConditioning(vehicleDTO.isHasAirConditioning())
                .model(vehicleDTO.getModel())
                .manufacturer(vehicleDTO.getManufacturer())
                .plateNumber(vehicleDTO.getPlateNumber())
                .longitude(vehicleDTO.getLongitude())
                .latitude(vehicleDTO.getLatitude())
                .dtype(Vehicle.VehicleType.valueOf(vehicleDTO.getDtype().name())) // Convert VehicleDTO.VehicleType to Vehicle.VehicleType
                .build();
    }
}
