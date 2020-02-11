package com.alibaba.question2;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TicketSpinLockTest extends TestCase {

    private int value = 0;

    public void testLockAndUnLock() throws InterruptedException {
        TicketSpinLock ticketSpinLock = new TicketSpinLock();
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                ticketSpinLock.lock();
                for (int j = 0; j < 1000; j++) {
                    value++;
                }
                ticketSpinLock.unlock();
            });
            thread.start();
            thread.join();
        }

        Assert.assertEquals(100000, value);
    }
}