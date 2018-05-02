package com.wyj.jvm.producerConsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Provider implements Runnable{

    /**
     * 共享缓存区
     */
    private BlockingQueue<Plate> queue;
    /**
     * 多线程间是否启动变量，有强制从主内存中刷新的功能。即时返回线程状态
     */
    private volatile boolean isRunning = true;
    /**
     * id生成器
     */
    private static AtomicInteger count = new AtomicInteger();

    public Provider(BlockingQueue<Plate> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(1000);
                //计数
                int id = count.incrementAndGet();
                Plate plate = new Plate(Integer.toString(id), "盘子" + id);
                System.out.println("当前线程：" + Thread.currentThread().getName() + "，获取了盘子，id为："
                + id + "，进行装载到公共缓冲区中...");
                if (!this.queue.offer(plate, 2, TimeUnit.SECONDS)) {
                    System.out.println("提交缓冲区数据失败...");
                    // do something... 比如重新提交
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void stop() {
        this.isRunning = false;
    }
}
