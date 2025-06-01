package com.example.invoice.util;

public class LegacyUtils {
    public static final String PREFIX = "INV";

    public static String formatInvoiceId(Long id) {
        return PREFIX + "-" + id;
    }
}