package com.rental.rental.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleCheckDTO {

    @NotNull(message = "The check ID is required")
    private int checkId;

    @NotNull(message = "Check date is required")
    private Date checkDate;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Vehicle ID is required")
    private int vehicleId;
}
