package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.service.PaymentService.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentContext implements PaymentStrategy {
    @Autowired
    private StripePayment stripePayment;

    @Autowired
    private PayPalPayment payPalPayment;

    @Override
    public boolean pay(PaymentDTO paymentDTO) {
        if ("Stripe".equalsIgnoreCase(paymentDTO.getCardType())) {
            return stripePayment.pay(paymentDTO);
        } else if ("PayPal".equalsIgnoreCase(paymentDTO.getCardType())) {
            return payPalPayment.pay(paymentDTO);
        } else {
            throw new IllegalArgumentException("Unsupported card type!");
        }
    }
}

