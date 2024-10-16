package com.rental.rental.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDTO {

    @NotNull(message = "The branch ID is required")
    private int branchId;

    @NotEmpty(message = "The branch name is required")
    private String branchName;

    @NotEmpty(message = "The branch location is required")
    private String branchLocation;

    @NotEmpty(message = "Contact details are required")
    private String contactDetails;

    @NotNull(message = "Reservation ID is required")
    private int reservationId;

    @NotNull(message = "Payment ID is required")
    private int paymentId;

    @NotEmpty(message = "The branch must have at least one parking stall")
    private List<Integer> parkingStallIds;
}
