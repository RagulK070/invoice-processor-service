package com.example.invoice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LegacyUtils.class)
public class LegacyUtilsTest {

    @Test
    public void testFormatInvoiceIdStaticMock() {
        PowerMockito.mockStatic(LegacyUtils.class);
        PowerMockito.when(LegacyUtils.formatInvoiceId(10L)).thenReturn("MOCKED-10");

        String result = LegacyUtils.formatInvoiceId(10L);
        assertEquals("MOCKED-10", result);
        PowerMockito.verifyStatic(LegacyUtils.class, times(1));
        LegacyUtils.formatInvoiceId(10L);
    }
}