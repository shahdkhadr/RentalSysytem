package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.service.config.PayPalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PayPalPayment extends CardPaymentTemplate {

    @Autowired
    private PayPalConfig payPalConfig;

    @Override
    protected boolean processPaymentWithProvider(PaymentDTO paymentDTO) {

        APIContext apiContext = new APIContext(payPalConfig.getClientId(), payPalConfig.getClientSecret(), "sandbox");

        try {
            Amount amount = new Amount();
            amount.setCurrency("USD");
            amount.setTotal(String.format("%.2f", paymentDTO.getTotalAmount()));

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setDescription("Payment with PayPal");

            List<Transaction> transactions = new ArrayList<>();
            transactions.add(transaction);

            Payment payment = new Payment();
            payment.setIntent("sale"); //intent of the payment transaction
            payment.setTransactions(transactions);

            Payer payer = new Payer();
            payer.setPaymentMethod("paypal");
            payment.setPayer(payer);

            RedirectUrls redirectUrls = new RedirectUrls();
            redirectUrls.setReturnUrl("http://localhost:8081/fake-return-url");
            redirectUrls.setCancelUrl("http://localhost:8081/fake-cancel-url");
            payment.setRedirectUrls(redirectUrls);

            Payment createdPayment = payment.create(apiContext);
            System.out.println("Payment processed through PayPal with ID: " + createdPayment.getId());

            return true;
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            return false;
        }
    }
}
