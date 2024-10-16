package com.rental.rental.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDTO {

    @NotNull(message = "The notification ID is required")
    private int notificationId;

    @NotEmpty(message = "The message is required")
    private String message;

    private boolean isRead;

    @NotNull(message = "Timestamp is required")
    private Time timestamp;

    @NotNull(message = "Customer ID is required")
    private int customerId;
}
