package chapter05;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThrowExceptionLock {

    public static void main(String[] args) {
        Lock lock = new Mutex();
        try {
            lock.lockInterruptibly();
            System.out.println("lock success...");
            throw new Exception();
        } catch (Exception e) {
            if (lock.tryLock()) {
                System.out.println("lock success again...");
            }
        }
    }
}
