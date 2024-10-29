package com.rental.rental.dto;

import com.rental.rental.model.PaymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Data Transfer Object for payment information")
public class PaymentDTO {

    @NotNull(message = "The payment ID is required")

    private int paymentId;
    @Schema(description = "Unique identifier of the payment", example = "1")
    @NotEmpty(message = "The payment method is required")
    private String paymentMethod;

    @NotNull(message = "The payment date is required")
    @Schema(description = "The payment date", example = "2024-12-12")
    private Date paymentDate;

    @NotEmpty(message = "The payment status is required")
    @Schema(description = "The payment status", example = "PENDING")
    private PaymentStatus status;

    @NotNull(message = "The total amount is required")
    @Positive(message = "The total amount must be greater than zero")
    @Schema(description = "The total amount of money", example = "3000")
    private double totalAmount;

}
