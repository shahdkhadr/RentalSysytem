package com.rental.rental.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleDTO {

    @NotNull(message = "The vehicle ID is required")
    private int vehicleId;

    @NotBlank(message = "The vehicle name is required")
    private String vehicleName;

    @NotBlank(message = "The vehicle status is required")
    private String vehicleStatus;

    @NotBlank(message = "The fuel type is required")
    private String fuelType;

    @Positive(message = "Engine capacity must be a positive value")
    private double engineCapacity;

    private boolean hasAirConditioning;

    @NotBlank(message = "The vehicle model is required")
    private String model;

    @NotBlank(message = "The manufacturer is required")
    private String manufacturer;

    @NotBlank(message = "The plate number is required")
    private String plateNumber;

    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private double longitude;

    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private double latitude;

    private VehicleType dtype; // Enum for VehicleType

    private Integer rentalId;
    private Integer reservationId;
    private Integer stallId;

    private List<Integer> vehicleCheckIds;

    public enum VehicleType {
        MOTOR,
        CAR,
        TRUCK,
        VAN,
        BUS
    }
}
