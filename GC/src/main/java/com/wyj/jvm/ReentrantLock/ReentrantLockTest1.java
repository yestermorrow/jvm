package com.wyj.jvm.ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wyj on 2018/9/18
 */
public class ReentrantLockTest1 implements Runnable{

    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;
    public void run() {
        for (int j = 0;j<100000;j++) {
            lock.lock();
//            lock.lock();
            System.out.println(i + "进去lock");
            try {
                i++;
            }finally {
                System.out.println(i + "进去unlock");
                lock.unlock();
//                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest1 reenterLock = new ReentrantLockTest1();
        Thread t1 = new Thread(reenterLock);
        Thread t2 = new Thread(reenterLock);
        t1.start();
        t2.start();
//        t1.join();
//        t2.join();
        System.out.println(i);
    }
}
