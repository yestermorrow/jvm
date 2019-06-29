package com.wyj.jvm.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

public class LockAtomicDemo {
    volatile AtomicInteger i = new AtomicInteger();

    public void add() {
        // i++;
        i.incrementAndGet();

    }

    public static void main(String[] args) throws InterruptedException{
        LockAtomicDemo ld = new LockAtomicDemo();
        for (int i = 0; i < 2; i ++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j ++) {
                    ld.add();
                }
            }).start();
        }
        Thread.sleep(2000L);
        System.out.println(ld.i);
    }
}
