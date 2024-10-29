package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.service.PaymentService.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentContext implements PaymentStrategy {

    @Override
    public boolean pay(PaymentDTO paymentDTO) {
        // Here template method design pattern to support multiple card type
        return true;
    }
}

