package com.example.invoice.service;

import com.example.invoice.model.Invoice;
import com.example.invoice.repository.InvoiceRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Captor
    private ArgumentCaptor<Invoice> invoiceCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @After
    public void tearDown() {
        // Cleanup logic
    }

    @Test
    public void testSaveInvoice() {
        Invoice invoice = new Invoice();
        invoice.setClient("ABC Corp");
        invoice.setAmount(5000.0);
        invoice.setInvoiceDate(LocalDate.now());

        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);
        Invoice saved = invoiceService.saveInvoice(invoice);

        verify(invoiceRepository, times(1)).save(invoiceCaptor.capture());
        assertEquals("ABC Corp", invoiceCaptor.getValue().getClient());
    }

    @Test
    public void testGetInvoice() {
        Invoice invoice = new Invoice();
        invoice.setClient("XYZ Inc");
        when(invoiceRepository.findById(1L)).thenReturn(Optional.of(invoice));

        Invoice result = invoiceService.getInvoice(1L);
        assertNotNull(result);
        assertEquals("XYZ Inc", result.getClient());
    }
}