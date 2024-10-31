package com.rental.rental.service.PaymentService.PaymentStrategyImplementation;
import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.service.config.StripeConfig;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StripePayment extends CardPaymentTemplate {

    @Autowired
    private StripeConfig stripeConfig;

    @Override
    protected boolean processPaymentWithProvider(PaymentDTO paymentDTO) {
        try {
            Stripe.apiKey = stripeConfig.getSecretKey();

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount((long) (paymentDTO.getTotalAmount() * 100))
                    .setCurrency("usd")
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println("Payment processed through Stripe: " + paymentIntent.getId());

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
