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
@Schema(description = "Data Transfer Object for department information")
public class DepartmentDTO {

    @NotNull(message = "The department ID is required")
    @Schema(description = "Unique identifier of the department", example = "1")
    private int departmentId;

    @NotEmpty(message = "The department name is required")
    @Schema(description = "Name of the department", example = "Reservations Department")
    private String departmentName;


    @NotEmpty(message = "The department position is required")
    @Schema(description = "Position of the department", example = "Nablus")
    private String departmentPosition;

}
