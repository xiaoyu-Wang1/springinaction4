package test;

import java.util.concurrent.TimeUnit;

/**
 * 测试 volatile 变量是否是多线程安全的
 */
public class VolatileThreadSafeTest {
    @SuppressWarnings("unsafe")
    private volatile int sum = 0;

    public static void main(String[] args) throws InterruptedException {
        VolatileThreadSafeTest threadSafeTest = new VolatileThreadSafeTest();
        System.out.println(threadSafeTest.add(1000));
    }

    private int add(int times) throws InterruptedException {
        new Thread(new AddRunnable(times), "addThread1").start();
//        TimeUnit.SECONDS.sleep(3);
        new Thread(new AddRunnable(times), "addThread2").start();
//        TimeUnit.SECONDS.sleep(3);
        new Thread(new AddRunnable(times), "addThread3").start();
        new Thread(new AddRunnable(times), "addThread4").start();
        new Thread(new AddRunnable(times), "addThread5").start();
        new Thread(new AddRunnable(times), "addThread6").start();
        new Thread(new AddRunnable(times), "addThread7").start();
        new Thread(new AddRunnable(times), "addThread8").start();
        new Thread(new AddRunnable(times), "addThread9").start();
        new Thread(new AddRunnable(times), "addThread10").start();
        TimeUnit.SECONDS.sleep(3);
        return sum;
    }

    private class AddRunnable implements Runnable {
        private int times;

        public AddRunnable(int times) {
            this.times = times;
        }

        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                sum++;
            }
        }
    }
}
