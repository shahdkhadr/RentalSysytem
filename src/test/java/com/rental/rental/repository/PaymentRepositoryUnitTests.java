package com.rental.rental.repository;

import com.rental.rental.model.Branch;
import com.rental.rental.model.Invoice;
import com.rental.rental.model.Payment;
import com.rental.rental.model.PaymentStatus;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions;
import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaymentRepositoryUnitTests {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Test
    @DisplayName("Test 1: Save Payment")
    @Order(1)
    @Rollback(value = false)
    public void savePaymentTest() throws InterruptedException, ParseException {
        String dateString = "2024-11-05";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dateString);

        // Action
        Payment payment = Payment.builder()
                .paymentMethod("card")
                .paymentDate(date)
                .status(PaymentStatus.valueOf("PENDING"))
                .totalAmount(200)
                .cardType("PayPal")
                .build();

        paymentRepository.save(payment);
        Assertions.assertThat(payment.getPaymentId()).isEqualTo(1);
    }

}
