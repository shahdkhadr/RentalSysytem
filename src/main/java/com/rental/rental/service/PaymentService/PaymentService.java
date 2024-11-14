package com.rental.rental.service.PaymentService;

import com.rental.rental.dto.InvoiceDTO;
import com.rental.rental.dto.PaymentDTO;
import com.rental.rental.model.*;
import com.rental.rental.repository.BranchRepository;
import com.rental.rental.repository.InvoiceRepository;
import com.rental.rental.repository.PaymentRepository;
import com.rental.rental.service.PaymentService.PaymentFactoryImplementation.PaymentFactoryService;
import com.rental.rental.service.PaymentService.PaymentStrategyImplementation.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private PaymentFactoryService paymentFactoryService;

    public List<PaymentDTO> getAllPayments(){
        return paymentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PaymentDTO getPaymentById(int paymentId ){
        return paymentRepository.findById(paymentId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public PaymentDTO savePayment(PaymentDTO paymentDTO){
        Payment payment = convertToEntity(paymentDTO);
        return convertToDTO(paymentRepository.save(payment));
    }

    public final String processPayment(PaymentDTO paymentDTO) {

        Payment payment = convertToEntity(paymentDTO);

        PaymentStrategy paymentStrategy = paymentFactoryService.getPaymentService(paymentDTO);

        if (paymentStrategy == null) {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
            return "Payment Failed";
        }

        boolean isSuccess = false;

        try {
            isSuccess = paymentStrategy.pay(paymentDTO);
        } catch (Exception e){
            System.err.println("Payment processing failed due to an error: " + e.getMessage());
        }


        // Update payment status based on processing result
        payment.setStatus(isSuccess ? PaymentStatus.COMPLETED : PaymentStatus.FAILED);

        paymentRepository.save(payment);

        if (isSuccess) {
            return "Payment processed successfully";
        } else {
            return "Payment Failed";
        }
    }

    public PaymentDTO updatePayment( int id, PaymentDTO paymentDTO ) {
        Optional<Payment> existingPaymentOpt = paymentRepository.findById(id);
        if( existingPaymentOpt.isPresent() ){
            Payment existingPayment = existingPaymentOpt.get();

            if( (paymentDTO.getPaymentMethod() != null && !paymentDTO.getPaymentMethod().isEmpty() )){
                existingPayment.setPaymentMethod(paymentDTO.getPaymentMethod());
            }

            Calendar calendar = Calendar.getInstance();

            // Clear out the time part to keep only the date
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);



            if( paymentDTO.getPaymentDate() != null && (paymentDTO.getPaymentDate().after(calendar.getTime()) || paymentDTO.getPaymentDate().equals(calendar.getTime()))){
                existingPayment.setPaymentDate(paymentDTO.getPaymentDate());
            }

            if( paymentDTO.getStatus() != null ){
                existingPayment.setStatus(paymentDTO.getStatus());
            }

            if( paymentDTO.getTotalAmount() != 0.0 ){
                existingPayment.setTotalAmount(paymentDTO.getTotalAmount());
            }

            Payment updatedPayment = paymentRepository.save(existingPayment);
            return convertToDTO(updatedPayment);

        }
        return null;
    }


    public void deletePayment( int paymentId ){
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if ( payment.isPresent() ){
            paymentRepository.deleteById(paymentId);
        }
    }

    public PaymentDTO convertToDTO(Payment payment){
        return PaymentDTO.builder()
                .paymentId(payment.getPaymentId())
                .paymentMethod(payment.getPaymentMethod())
                .paymentDate(payment.getPaymentDate())
                .status(payment.getStatus())
                .totalAmount(payment.getTotalAmount())
                .cardType(payment.getCardType())
                .build();
    }

    public Payment convertToEntity( PaymentDTO paymentDTO ){
        return Payment.builder()
                .paymentId(paymentDTO.getPaymentId())
                .paymentMethod(paymentDTO.getPaymentMethod())
                .paymentDate(paymentDTO.getPaymentDate())
                .status(paymentDTO.getStatus())
                .totalAmount(paymentDTO.getTotalAmount())
                .cardType(paymentDTO.getCardType())
                .build();

    }

}
