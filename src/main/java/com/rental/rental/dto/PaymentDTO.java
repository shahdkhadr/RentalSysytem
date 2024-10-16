package com.rental.rental.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {

    @NotNull(message = "The payment ID is required")
    private int paymentId;

    @NotEmpty(message = "The payment method is required")
    private String paymentMethod;

    @NotNull(message = "The payment date is required")
    private Date paymentDate;

    @NotEmpty(message = "The payment status is required")
    private String status;

    @NotNull(message = "The total amount is required")
    @Positive(message = "The total amount must be greater than zero")
    private double totalAmount;

    @NotNull(message = "Invoice ID is required")
    private int invoiceId;

    @NotNull(message = "Rental ID is required")
    private int rentalId;


    private List<Integer> branchIds;
}
