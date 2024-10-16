package com.rental.rental.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingStallDTO {

    @NotNull(message = "The parking stall ID is required")
    private int stallId;

    @NotNull(message = "Branch ID is required")
    private int branchId;

    @NotNull(message = "Vehicle ID is required")
    private int vehicleId;
}
