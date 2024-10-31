package com.rental.rental.controller;

import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.service.PaymentService.PaymentService;
import com.rental.rental.service.PaymentService.PaymentStrategyImplementation.CardPaymentContext;
import com.rental.rental.service.PaymentService.PaymentFactoryImplementation.PaymentFactoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/car-rental-system")
@Tag(name = "Payment", description = "The Payment API")
public class PaymentController {

    @Autowired
    private CardPaymentContext cardPaymentContext;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentFactoryService paymentFactoryService;

    @GetMapping("/payments")
    @Operation(summary = "Get all payments", description = "Retrieve a list of all payments")
    public ResponseEntity<List<PaymentDTO>> getPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // Updated `processPayment` method using CardPaymentContext for Stripe and PayPal
    @PostMapping("/process")
    @Operation(summary = "Process a payment", description = "Process a payment using Stripe or PayPal based on the card type")
    public ResponseEntity<Map<String, String>> processPayment(@RequestBody PaymentDTO paymentDTO) {
        boolean paymentStatus = cardPaymentContext.pay(paymentDTO);
        Map<String, String> response = new HashMap<>();

        if (paymentStatus) {
            response.put("message", "Payment processed successfully");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Payment failed");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @GetMapping("/payment/{paymentId}")
    @Operation(summary = "Get payment by ID", description = "Retrieve a payment by its ID")
    public ResponseEntity<PaymentDTO> getPayment(@PathVariable int paymentId) {
        return ResponseEntity.ok(paymentService.getPaymentById(paymentId));
    }

    @PostMapping("/payment")
    @Operation(summary = "Create a new payment", description = "Add a new payment to the system")
    public ResponseEntity<PaymentDTO> savePayment(@RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.savePayment(paymentDTO));
    }

    @PutMapping("/payment/{paymentId}")
    @Operation(summary = "Update a payment", description = "Update details of an existing payment")
    public ResponseEntity<PaymentDTO> updatePayment(@PathVariable int paymentId, @RequestBody PaymentDTO paymentDTO) {
        return ResponseEntity.ok(paymentService.updatePayment(paymentId, paymentDTO));
    }

    @DeleteMapping("/payment/{paymentId}")
    @Operation(summary = "Delete a payment", description = "Remove a payment from the system")
    public void deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
    }
}
