package com.rental.rental.service;

import com.rental.rental.dto.InvoiceDTO;
import com.rental.rental.model.Department;
import com.rental.rental.model.Employee;
import com.rental.rental.model.Invoice;
import com.rental.rental.model.Payment;
import com.rental.rental.repository.EmployeeRepository;
import com.rental.rental.repository.InvoiceRepository;
import com.rental.rental.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<InvoiceDTO> getAllInvoices(){
        return invoiceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public InvoiceDTO getInvoiceById( int invoiceId ){
        return invoiceRepository.findById(invoiceId)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public InvoiceDTO saveInvoice(InvoiceDTO invoiceDTO){
        Invoice invoice = convertToEntity(invoiceDTO);
        return convertToDTO(invoiceRepository.save(invoice));
    }

    public InvoiceDTO updateInvoice( int id, InvoiceDTO invoiceDTO ) {
        Optional<Invoice> existingInvoiceOpt = invoiceRepository.findById(id);
        if( existingInvoiceOpt.isPresent() ){
            Invoice existingInvoice = existingInvoiceOpt.get();

            if( !(invoiceDTO.getTotalAmount() == 0.0 )){
                existingInvoice.setTotalAmount(invoiceDTO.getTotalAmount());
            }

            Calendar calendar = Calendar.getInstance();

            // Clear out the time part to keep only the date
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);



            if( invoiceDTO.getPrintDate() != null && (invoiceDTO.getPrintDate().after(calendar.getTime()) || invoiceDTO.getPrintDate().equals(calendar.getTime()))){
                existingInvoice.setPrintDate(invoiceDTO.getPrintDate());
            }

            if( invoiceDTO.getEmployeeId() != 0 ){
                Optional<Employee> employee = employeeRepository.findById( invoiceDTO.getEmployeeId() );
                employee.ifPresent(existingInvoice::setEmployee);
            }

            if( invoiceDTO.getPaymentId() != 0 ){
                Optional<Payment> payment = paymentRepository.findById( invoiceDTO.getPaymentId() );
                payment.ifPresent(existingInvoice::setPayment);
            }

            Invoice updatedInvoice = invoiceRepository.save(existingInvoice);
            return convertToDTO(updatedInvoice);

        }
        return null;
    }

    public void deleteInvoice( int invoiceId ){
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        if ( invoice.isPresent() ){
            invoiceRepository.deleteById(invoiceId);
        }
    }

    public InvoiceDTO convertToDTO( Invoice invoice ){
        InvoiceDTO invoiceDTO = new InvoiceDTO();
        invoiceDTO.setInvoiceId(invoice.getInvoiceId());
        invoiceDTO.setEmployeeId(invoice.getEmployee().getEmployeeId());
        invoiceDTO.setPaymentId(invoice.getPayment().getPaymentId());
        invoiceDTO.setPrintDate(invoice.getPrintDate());
        invoiceDTO.setTotalAmount(invoice.getTotalAmount());
        return invoiceDTO;
    }

    public Invoice convertToEntity( InvoiceDTO invoiceDTO ){

        Optional<Employee> employee = employeeRepository.findById(invoiceDTO.getEmployeeId());
        if (!employee.isPresent()) {
            throw new IllegalArgumentException("Employee not found");
        }

        Optional<Payment> payment = paymentRepository.findById(invoiceDTO.getPaymentId());
        if (!payment.isPresent()) {
            throw new IllegalArgumentException("Payment not found");
        }

        return Invoice.builder()
                .invoiceId(invoiceDTO.getInvoiceId())
                .printDate(invoiceDTO.getPrintDate())
                .totalAmount(invoiceDTO.getTotalAmount())
                .employee(employee.get())
                .payment(payment.get())
                .build();
    }

}
