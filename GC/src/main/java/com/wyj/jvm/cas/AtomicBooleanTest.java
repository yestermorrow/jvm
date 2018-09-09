package com.wyj.jvm.cas;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Cteated by wyj on 2017/9/9
 *
 */
public class AtomicBooleanTest implements Runnable{

    private static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) {
        AtomicBooleanTest ast = new AtomicBooleanTest();
        Thread thread1 = new Thread(ast);
        Thread thread = new Thread(ast);
        thread1.start();
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("thread:" + Thread.currentThread().getName()+";flag:"+flag.get());
        if (flag.compareAndSet(true, false)) {
            System.out.println(Thread.currentThread().getName()+""+flag.get());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag.set(true);
        } else {
            System.out.println("重试机制Thread："+Thread.currentThread().getName()+";flag"+flag.get());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            run();
        }
    }
}
