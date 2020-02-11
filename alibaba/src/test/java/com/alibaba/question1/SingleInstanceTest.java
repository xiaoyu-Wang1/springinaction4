package com.alibaba.question1;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.concurrent.ConcurrentHashMap;

public class SingleInstanceTest extends TestCase {

    public void testGetInstance() {
        ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>(10000);

        for (int i = 0; i < 10000; i++) {
            new Thread(()-> concurrentHashMap.put(SingleInstance.getInstance().hashCode(), 1)).start();
        }

        Assert.assertEquals(1, concurrentHashMap.size());
    }
}