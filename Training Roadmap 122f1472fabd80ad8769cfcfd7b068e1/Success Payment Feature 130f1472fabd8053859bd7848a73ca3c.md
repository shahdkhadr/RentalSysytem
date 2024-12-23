# Success Payment Feature

### PayPal Payment

```
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
        payment.setIntent("sale");
        payment.setTransactions(transactions);

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        payment.setPayer(payer);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setReturnUrl("<http://localhost:8081/fake-return-url>"); 
        redirectUrls.setCancelUrl("<http://localhost:8081/fake-cancel-url>");
        payment.setRedirectUrls(redirectUrls);

        Payment createdPayment = payment.create(apiContext);
        System.out.println("Payment processed through PayPal with ID: " + createdPayment.getId());

        return true; 
    } catch (PayPalRESTException e) {
        e.printStackTrace();
        return false; 
    }
}

```

- Annotated with `@Service`, allowing Spring to manage it as a service.
- Extends `CardPaymentTemplate`, implementing the `processPaymentWithProvider` method.
- Uses `@Autowired` to inject `PayPalConfig`, which provides PayPal client ID and secret.
1. **API Context Initialization:**
    - Creates `APIContext` with client ID, secret, and environment (`sandbox`), **sandbox** is a testing environment that allows developers to simulate transactions and test their applications without using real money.
2. **Amount Setup:**
    - Creates an `Amount` object.
    - Sets currency to `"USD"` and formats the total amount to two decimal places.
3. **Transaction Details:**
    - Creates a `Transaction` object with the amount and description.
    - Adds this transaction to a list of transactions.
4. **Payment Setup:**
    - Creates a `Payment` object.
    - Sets intent to `"sale"` (funds captured immediately).
    - Associates the transaction list with the payment.
    
    ### Intent Types
    
    - **"sale"**: Captures funds immediately upon transaction completion, typically used for one-time purchases.
    - **"authorize"**: Holds funds without capturing, allowing you to capture them later. Common in cases where confirmation or additional checks are needed before finalizing the transaction.
    - **"order"**: Creates an agreement for future payment captures, often used for multi-payment orders like subscriptions.
    - **"capture"**: Explicitly captures authorized funds, typically used after setting the intent to "authorize" to complete the transaction and transfer funds.
5. **Payer Information:**
    - Creates a `Payer` object and sets payment method to `"paypal"`.
    - Attaches the payer to the payment.
6. **Redirect URLs:**
    - Sets up `RedirectUrls` for return (success) and cancel scenarios.
    - Adds these URLs to the payment.
    - we use fake URL for now.
7. **Payment Execution:**
    - Calls `payment.create(apiContext)` to execute the payment.
    - Logs the payment ID if successful.

### Stripe Payment

```

@Override
protected boolean processPaymentWithProvider(PaymentDTO paymentDTO) {
    try {
        Stripe.apiKey = stripeConfig.getSecretKey();

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount((long) (paymentDTO.getTotalAmount() * 100)) // Amount in cents
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
```

**`PaymentIntentCreateParams`**

: Configures the payment with details such as:

- **Amount**: `paymentDTO.getTotalAmount()` multiplied by 100 to convert dollars to cents (Stripe works in the smallest currency unit).
- **Currency**: Sets currency to USD for the transaction.
- **`PaymentIntent.create`**: Sends the payment parameters to Stripe, creating a payment session.