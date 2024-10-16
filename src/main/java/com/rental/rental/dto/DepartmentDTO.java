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
public class DepartmentDTO {

    @NotNull(message = "The department ID is required")
    private int departmentId;

    @NotEmpty(message = "The department name is required")
    private String departmentName;

    @NotEmpty(message = "The department position is required")
    private String departmentPosition;

    @NotEmpty(message = "The department must have at least one employee")
    private List<Integer> employeeIds;
}
