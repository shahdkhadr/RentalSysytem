package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.service.PaymentService.PaymentService;
import org.springframework.stereotype.Service;


@Service
public class OnArrivalPaymentContext implements PaymentStrategy {

    @Override
    public boolean pay(PaymentDTO paymentDTO) {
        System.out.println("Payment will be made on arrival.");
        return true;
    }
}

