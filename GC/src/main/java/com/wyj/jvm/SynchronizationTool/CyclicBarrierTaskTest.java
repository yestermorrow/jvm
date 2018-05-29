package com.wyj.jvm.SynchronizationTool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * Created by wyj on 2018/5/10
 */
class CyclicBarrierTask implements Runnable {

    private CyclicBarrier cyclicBarrier;
    private int timeout;

    public CyclicBarrierTask(CyclicBarrier cyclicBarrier, int timeout) {
        this.cyclicBarrier = cyclicBarrier;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        CyclicBarrierTaskTest.print("正在running...");
        try {
            TimeUnit.MILLISECONDS.sleep(timeout);
            CyclicBarrierTaskTest.print("到达栅栏处，等待其他线程到达");
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        CyclicBarrierTaskTest.print("所有线程到达栅栏处，继续执行各自线程任务...");
    }
}
public class CyclicBarrierTaskTest {

    public static void print(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("[" + dateFormat.format(new Date()) + "]"
            + Thread.currentThread().getName() + str);
    }

    public static void main(String[] args) {
        int count = 5;
        ExecutorService es = Executors.newFixedThreadPool(count);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count, new Runnable() {
            @Override
            public void run() {
                CyclicBarrierTaskTest.print("所有线程到达栅栏处，可以在此做一些处理...");
            }
        });
        for (int i = 0; i < count; i++) {
            es.execute(new CyclicBarrierTask(cyclicBarrier, (i + 1) * 1000));
        }
    }
}
