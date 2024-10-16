package com.rental.rental.dto;

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
public class InvoiceDTO {

    @NotNull(message = "The invoice ID is required")
    private int invoiceId;

    @NotNull(message = "The total amount is required")
    @Positive(message = "The total amount must be greater than zero")
    private double totalAmount;

    @NotNull(message = "The invoice date is required")
    @FutureOrPresent(message = "The invoice date must be in the present or future")
    private Date printDate;

    @NotNull(message = "Employee ID is required")
    private int employeeId;

    @NotNull(message = "Payment ID is required")
    private int paymentId;
}
