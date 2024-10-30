package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;
import org.springframework.stereotype.Service;

@Service
public class StripePayment extends CardPaymentTemplate {

    @Override
    protected boolean processPaymentWithProvider(PaymentDTO paymentDTO) {
        System.out.println("Processing payment through Stripe...");
        return true;
    }
}

