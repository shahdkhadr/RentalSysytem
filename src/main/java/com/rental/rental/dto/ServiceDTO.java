package com.rental.rental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceDTO {

    @NotNull(message = "The service ID is required")
    private int serviceId;

    @NotBlank(message = "The service name is required")
    private String serviceName;

    @NotNull(message = "The service cost is required")
    private double serviceCost;
}
