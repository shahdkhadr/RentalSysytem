package com.rental.rental.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data Transfer Object for admin information")
public class AdminDTO {

    @NotEmpty(message = "The admin id is required")
    @Schema(description = "Unique identifier of the admin", example = "1")
    private int adminId;

    @NotEmpty(message = "The name is required")
    @Schema(description = "Name of the admin", example = "Ali Hamadneh")
    private String adminName;

    @NotEmpty(message = "The phone number is required")
    @Schema(description = "Phone number of the admin", example = "0568347481")
    private String phoneNumber;
}
