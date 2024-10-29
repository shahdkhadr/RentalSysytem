package com.rental.rental.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Data Transfer Object for invoice information")
public class InvoiceDTO {

    @NotNull(message = "The invoice ID is required")
    @Schema(description = "Unique identifier of the invoice", example = "1")
    private int invoiceId;

    @NotNull(message = "The total amount is required")
    @Positive(message = "The total amount must be greater than zero")
    @Schema(description = "Total amount of money", example = "2000")
    private double totalAmount;

    @NotNull(message = "The invoice date is required")
    @FutureOrPresent(message = "The invoice date must be in the present or future")
    @Schema(description = "The invoice date", example = "2024-12-12")
    private Date printDate;

    @NotNull(message = "Employee ID is required")
    private int employeeId;

    @NotNull(message = "Payment ID is required")
    private int paymentId;
}
