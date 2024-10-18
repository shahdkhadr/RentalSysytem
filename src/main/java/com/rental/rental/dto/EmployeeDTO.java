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
@Schema(description = "Data Transfer Object for employee information")
public class EmployeeDTO {

    @NotNull(message = "The employee ID is required")
    @Schema(description = "Unique identifier of the employee", example = "1")
    private int employeeId;

    @NotEmpty(message = "The employee name is required")
    @Schema(description = "Name of the employee", example = "Abdullah Sholi")
    private String employeeName;

    @NotEmpty(message = "The employee phone number is required")
    @Schema(description = "Phone number of the employee", example = "0592659066")
    private String phoneNumber;

    @NotEmpty(message = "The employment type is required")
    @Schema(description = "Type of the employee", example = "Accounting")
    private String employmentType;

    @NotNull(message = "Department ID is required")
    private int departmentId;

}
