package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;
import org.springframework.stereotype.Service;

@Service
public class CardStrategy implements PaymentStrategy {
    @Override
    public boolean pay(PaymentDTO paymentDTO) {
        // Implement the logic to interact with Stripe API
        System.out.println("Processing payment through Stripe...");
        return true; // Return true if payment is successful
    }

}
