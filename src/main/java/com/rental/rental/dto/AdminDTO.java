package com.rental.rental.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO {

    @NotEmpty(message = "The admin id is required")
    private int adminId;

    @NotEmpty(message = "The name is required")
    private String adminName;

    @NotEmpty(message = "The phone number is required")
    private String phoneNumber;
}
