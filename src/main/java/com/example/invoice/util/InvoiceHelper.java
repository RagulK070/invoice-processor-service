package com.example.invoice.util;

public class InvoiceHelper {
    private String prefix = "INV-";

    public String getPrefixedId(Long id) {
        return prefix + id;
    }

    public String process() {
        HelperService service = new HelperService();
        return service.computeValue();
    }
}