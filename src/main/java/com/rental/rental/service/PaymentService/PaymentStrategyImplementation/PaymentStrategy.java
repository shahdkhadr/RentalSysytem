package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;

public interface PaymentStrategy {
    boolean pay(PaymentDTO paymentDTO);
}
