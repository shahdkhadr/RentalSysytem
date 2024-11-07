package com.rental.rental.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object for branch information")
public class BranchDTO {

    @NotNull(message = "The branch ID is required")
    @Schema(description = "Unique identifier of the branch", example = "1")
    private int branchId;

    @NotEmpty(message = "The branch name is required")
    @Schema(description = "The branch name ", example = "Nablus Branch")
    private String branchName;

    @NotEmpty(message = "The branch location is required")
    @Schema(description = "The branch location ", example = "Nablus")
    private String branchLocation;

    @NotEmpty(message = "Contact details are required")
    @Schema(description = "The contact details ", example = "0568347481")
    private String contactDetails;

    @NotNull(message = "Reservation ID is required")
    private int reservationId;

    @NotNull(message = "Payment ID is required")
    private int paymentId;

}
