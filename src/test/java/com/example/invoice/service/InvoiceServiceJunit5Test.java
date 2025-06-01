package com.example.invoice.service;

import com.example.invoice.model.Invoice;
import com.example.invoice.repository.InvoiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceJunit5Test {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    private ArgumentCaptor<Invoice> invoiceCaptor;

    @BeforeEach
    void init() {
        invoiceCaptor = ArgumentCaptor.forClass(Invoice.class);
    }

    @Test
    @DisplayName("Should save invoice using JUnit 5")
    void testSaveInvoice() {
        Invoice invoice = new Invoice();
        invoice.setClient("JUnit5 Corp");
        invoice.setAmount(3000.0);
        invoice.setInvoiceDate(LocalDate.now());

        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        Invoice result = invoiceService.saveInvoice(invoice);

        verify(invoiceRepository).save(invoiceCaptor.capture());
        assertEquals("JUnit5 Corp", invoiceCaptor.getValue().getClient());
    }

    @Test
    void testGetInvoice() {
        Invoice invoice = new Invoice();
        invoice.setClient("Five Inc");

        when(invoiceRepository.findById(5L)).thenReturn(Optional.of(invoice));
        Invoice result = invoiceService.getInvoice(5L);

        assertNotNull(result);
        assertEquals("Five Inc", result.getClient());
    }
}