package com.alibaba.question2;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ticket 模式的自旋锁实现类
 *
 * @author wangyuquan
 */
public class TicketSpinLock {

    /**
     * 保存当前线程的票号
     */
    private ThreadLocal<Integer> myTicketThreadLocal = new ThreadLocal<>();

    /**
     * 线程的票号
     */
    private AtomicInteger ticketNumber = new AtomicInteger();

    /**
     * 当前服务的票号
     */
    private AtomicInteger currService = new AtomicInteger();


    /**
     * 获取锁
     */
    public void lock() {
        int myTicket = ticketNumber.getAndIncrement();
        myTicketThreadLocal.set(myTicket);
        while (myTicket != currService.get()) {
            Thread.yield();
        }
    }

    /**
     * 释放锁
     */
    public void unlock() {
        int myTicket = myTicketThreadLocal.get();
        currService.compareAndSet(myTicket, myTicket + 1);
        myTicketThreadLocal.remove();
    }
}
