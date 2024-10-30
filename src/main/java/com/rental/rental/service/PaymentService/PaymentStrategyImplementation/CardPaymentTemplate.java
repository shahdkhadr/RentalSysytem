package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;

public abstract class CardPaymentTemplate implements PaymentStrategy {

    @Override
    public final boolean pay(PaymentDTO paymentDTO) {
        validateCard(paymentDTO);
        boolean isProcessed = processPaymentWithProvider(paymentDTO);
        confirmPayment(paymentDTO);
        return isProcessed;
    }

    private void validateCard(PaymentDTO paymentDTO) {
        System.out.println("Validating card information...");
    }

    protected abstract boolean processPaymentWithProvider(PaymentDTO paymentDTO);

    private void confirmPayment(PaymentDTO paymentDTO) {
        System.out.println("Payment confirmation sent.");
    }
}

