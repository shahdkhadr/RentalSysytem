package com.rental.rental.service;

import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.model.Payment;
import com.rental.rental.model.PaymentStatus;
import com.rental.rental.repository.BranchRepository;
import com.rental.rental.repository.InvoiceRepository;
import com.rental.rental.repository.PaymentRepository;
import com.rental.rental.service.PaymentService.PaymentFactoryImplementation.PaymentFactoryService;
import com.rental.rental.service.PaymentService.PaymentService;
import com.rental.rental.service.PaymentService.PaymentStrategyImplementation.PaymentStrategy;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentServiceUnitTests {

    @Mock(lenient = true)
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentFactoryService paymentFactoryService;

    @InjectMocks
    private PaymentService paymentService;

    private PaymentDTO paymentDTO;
    private Payment payment;

    @BeforeEach
    public void setup(){
        paymentDTO = PaymentDTO.builder()
                .paymentId(1)
                .paymentMethod("card")
                .status(PaymentStatus.PENDING)
                .totalAmount(200)
                .cardType("PayPal")
                .build();
        payment = Payment.builder()
                .paymentId(1)
                .paymentMethod("card")
                .status(PaymentStatus.PENDING)
                .totalAmount(200)
                .cardType("PayPal")
                .build();
    }

    @Test
    @Order(1)
    @DisplayName("Test 1: Successful Payment Process")
    public void successPaymentProcess(){
        PaymentStrategy paymentStrategy = mock(PaymentStrategy.class);

        when(paymentFactoryService.getPaymentService(paymentDTO)).thenReturn(paymentStrategy);
        when(paymentStrategy.pay(paymentDTO)).thenReturn(true);
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        String result = paymentService.processPayment(paymentDTO);

        assertThat(result).isEqualTo("Payment processed successfully");

    }

    @Test
    @Order(2)
    @DisplayName("Test 2: Failed Payment Process - No Strategy")
    public void failedPaymentProcess(){
        doReturn(null).when(paymentFactoryService).getPaymentService(paymentDTO);
        doReturn(payment).when(paymentRepository).save(payment);

        var result = paymentService.processPayment(paymentDTO);

        assertThat(result).isEqualTo("Payment Failed");

    }

}
