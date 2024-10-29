package com.rental.rental.service.PaymentService.PaymentFactoryImplementation;

import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.service.PaymentService.PaymentService;
import com.rental.rental.service.PaymentService.PaymentStrategyImplementation.OnArrivalPaymentContext;
import com.rental.rental.service.PaymentService.PaymentStrategyImplementation.CardPaymentContext;
import com.rental.rental.service.PaymentService.PaymentStrategyImplementation.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentFactoryService {
    @Autowired
    private CardPaymentContext cardPaymentContext;

    @Autowired
    private OnArrivalPaymentContext onArrivalPaymentContext;

    public PaymentStrategy getPaymentService(PaymentDTO paymentDTO){
        if("onArrival".equalsIgnoreCase(paymentDTO.getPaymentMethod())){
            return onArrivalPaymentContext;
        } else if("Card".equalsIgnoreCase(paymentDTO.getPaymentMethod())){
            return cardPaymentContext;
        } else{
            throw new IllegalArgumentException("Unsupported payment method! ");
        }

    }
}
