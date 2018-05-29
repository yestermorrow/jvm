package com.wyj.jvm.SynchronizationTool;

import java.util.concurrent.CountDownLatch;

/**
 * Created by wyj on 2018/5/10
 *
 * 在计时测试中使用countDownLatch来启动和停止线程
 *
 * 测试n个线程并发执行某个任务时需要的时间
 */
public class TestHarness {

    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException{
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i=0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {}
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }

    public static void main(String[] args) throws InterruptedException {
        TestHarness testHarness = new TestHarness();
        long time = testHarness.timeTasks(10, new Task());
        System.out.println(time);
    }
}
class Task implements Runnable
{
    int i = 0 ;
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
