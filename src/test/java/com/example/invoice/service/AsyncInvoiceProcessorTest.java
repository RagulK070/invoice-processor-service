package com.example.invoice.service;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class AsyncInvoiceProcessorTest {

    @Test(timeout = 2000)
    public void testConcurrentProcessing() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(2);
        final int[] counter = {0};

        Runnable task = () -> {
            counter[0]++;
            latch.countDown();
        };

        executor.submit(task);
        executor.submit(task);

        latch.await();
        executor.shutdown();

        assertEquals(2, counter[0]);
    }
}
