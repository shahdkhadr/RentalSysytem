package com.rental.rental.service;

import com.rental.rental.dto.VehicleDTO;
import com.rental.rental.model.*;
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
        Vehicle vehicle;
        if (vehicleDTO.getDtype().equals("CAR")) {
            vehicle = new Car();
        } else if (vehicleDTO.getDtype().equals("VAN")) {
            vehicle = new Van();
        } else if (vehicleDTO.getDtype().equals("TRUCK")) {
            vehicle = new Truck();
        } else if (vehicleDTO.getDtype().equals("MOTOR")) {
            vehicle = new Motor();
        } else if (vehicleDTO.getDtype().equals("BUS")) {
            vehicle = new Bus();
        }
        else {
            vehicle = new Vehicle(); // Or handle this as an error case
        }
        vehicle = convertToEntity(vehicleDTO); // Populate other fields
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
        Integer reservationId = null;
        if (vehicle.getReservations() != null && !vehicle.getReservations().isEmpty()) {
            // If reservations exist, use the reservationId of the first reservation
            reservationId = vehicle.getReservations().get(0).getReservationId();
        }

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
                .dtype(VehicleDTO.VehicleType.valueOf(vehicle.getDtype().name()))  // Convert Vehicle.VehicleType to VehicleDTO.VehicleType
                .rentalId(vehicle.getRental() != null ? vehicle.getRental().getRentalId() : null)
                .reservationId(reservationId)  // Set the first reservation's ID, if available
                .stallId(vehicle.getParkingStall() != null ? vehicle.getParkingStall().getStallId() : null)
                //.vehicleCheckIds(vehicle.getVehicleChecks() != null ? vehicle.getVehicleChecks().stream().map(VehicleCheck::getVehicleCheckId).collect(Collectors.toList()) : null)
                .build();
    }


    public Vehicle convertToEntity(VehicleDTO vehicleDTO) {
        Vehicle vehicle;

        // Determine which subclass of Vehicle to instantiate based on dtype
        switch (vehicleDTO.getDtype().name()) {
            case "CAR":
                vehicle = new Car();
                break;
            case "VAN":
                vehicle = new Van();
                break;
            case "BUS":
                vehicle = new Bus();
                break;
            case "TRUCK":
                vehicle = new Truck();
                break;
            case "MOTOR":
                vehicle = new Motor();
                break;
            default:
                vehicle = new Vehicle(); // Fallback to base class if dtype is unknown
                break;
        }

        // Set common properties from vehicleDTO to vehicle
        vehicle.setVehicleId(vehicleDTO.getVehicleId());
        vehicle.setEngineCapacity(vehicleDTO.getEngineCapacity());
        vehicle.setFuelType(vehicleDTO.getFuelType());
        vehicle.setHasAirConditioning(vehicleDTO.isHasAirConditioning());
        vehicle.setLatitude(vehicleDTO.getLatitude());
        vehicle.setLongitude(vehicleDTO.getLongitude());
        vehicle.setManufacturer(vehicleDTO.getManufacturer());
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setPlateNumber(vehicleDTO.getPlateNumber());
        vehicle.setVehicleName(vehicleDTO.getVehicleName());
        vehicle.setVehicleStatus(vehicleDTO.getVehicleStatus());
        vehicle.setDtype(Vehicle.VehicleType.valueOf(vehicleDTO.getDtype().name()));

        return vehicle;
    }

}
