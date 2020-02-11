package chapter08;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest2 {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 run");
                countDownLatch.countDown();
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 run");
                countDownLatch.countDown();
            }
        }, "t2").start();

        countDownLatch.await();

        System.out.println("main end...");
    }
}
