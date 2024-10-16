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
public class EmployeeDTO {

    @NotNull(message = "The employee ID is required")
    private int employeeId;

    @NotEmpty(message = "The employee name is required")
    private String employeeName;

    @NotEmpty(message = "The employee phone number is required")
    private String phoneNumber;

    @NotEmpty(message = "The employment type is required")
    private String employmentType;

    @NotNull(message = "Department ID is required")
    private int departmentId;

    @NotEmpty(message = "The employee must have at least one invoice")
    private List<Integer> invoiceIds;
}
