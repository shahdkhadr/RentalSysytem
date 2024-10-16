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
public class MotorDTO {

    @NotEmpty( message = "The availability side car is required")
    private boolean hasSideCar;

    @NotEmpty( message = "The motor type is required")
    private String motorType;
}
