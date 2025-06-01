package com.example.invoice.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HelperService.class, InvoiceHelper.class})
public class LegacyUtilsConstructorMockTest {

    @Test
    public void testMockNewObjectCreation() throws Exception {
        HelperService mockHelper = mock(HelperService.class);
        when(mockHelper.computeValue()).thenReturn("mocked-value");

        PowerMockito.whenNew(HelperService.class).withNoArguments().thenReturn(mockHelper);

        InvoiceHelper helper = new InvoiceHelper();
        String result = helper.process();

        assertEquals("mocked-value", result);
        verify(mockHelper).computeValue();
    }

    @Test
    public void testWhiteboxSetPrivateField() throws Exception {
        InvoiceHelper helper = new InvoiceHelper();
        Whitebox.setInternalState(helper, "prefix", "TEST-");

        String modified = helper.getPrefixedId(100L);
        assertEquals("TEST-100", modified);
    }
}