package com.rental.rental.controller;


import com.rental.rental.dto.InvoiceDTO;
import com.rental.rental.model.Invoice;
import com.rental.rental.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car-rental-system")
@Tag(name = "Invoice", description = "The Invoice API")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    @Operation(summary = "Get all invoices", description = "Retrieve a list of all invoices")
    public ResponseEntity<List<InvoiceDTO>> getInvoices(){
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @GetMapping("/invoice/{invoiceId}")
    @Operation(summary = "Get invoice by ID", description = "Retrieve an invoice by it's ID")
    public ResponseEntity<InvoiceDTO> getInvoice(@PathVariable int invoiceId){
        return ResponseEntity.ok(invoiceService.getInvoiceById(invoiceId));
    }

    @PostMapping("/invoice")
    @Operation(summary = "Create a new invoice", description = "Add a new invoice to the system")
    public ResponseEntity<InvoiceDTO> saveInvoice(@RequestBody InvoiceDTO invoiceDTO){
        return ResponseEntity.ok(invoiceService.saveInvoice(invoiceDTO));
    }

    @PutMapping("/invoice/{invoiceId}")
    @Operation(summary = "Update an invoice", description = "Update details of an existing invoice")
    public ResponseEntity<InvoiceDTO> updateInvoice(@PathVariable int invoiceId, @RequestBody InvoiceDTO invoiceDTO){
        return ResponseEntity.ok(invoiceService.updateInvoice(invoiceId ,invoiceDTO));
    }

    @DeleteMapping("/invoice/{invoiceId}")
    @Operation(summary = "Delete a invoice", description = "Remove a invoice from the system")
    public void deleteInvoice(@PathVariable int invoiceId){
        invoiceService.deleteInvoice(invoiceId);
    }

}
