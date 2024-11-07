package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;
import org.springframework.stereotype.Service;

@Service
public class OnArrivalStrategy implements PaymentStrategy {
    @Override
    public boolean pay(PaymentDTO paymentDTO) {
        // Implement the logic to interact with PayPal API
        System.out.println("On Arrival Payment...");
        return true; // Return true if payment is successful
    }

}
