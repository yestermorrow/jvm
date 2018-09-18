package com.wyj.jvm.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wyj on 2018/9/18
 */
public class ReentrantLockTest1 {

    private Lock lock = new ReentrantLock();

    public void testMethod() {
        lock.lock();
        for (int i = 0; i < 5; i++) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() + (" " + (i + 1)));
        }
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockTest1 reentrantLockTest1 = new ReentrantLockTest1();
        reentrantLockTest1.testMethod();
    }
}
