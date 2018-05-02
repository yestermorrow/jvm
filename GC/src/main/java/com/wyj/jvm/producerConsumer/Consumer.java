package com.wyj.jvm.producerConsumer;

import java.util.Date;
import java.util.concurrent.BlockingQueue;

/**
 * Created by wyj on 2018/5/2
 */
public class Consumer implements Runnable{

    private BlockingQueue<Plate> queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //获取数据
                Plate plate = this.queue.take();
                Thread.sleep(1000);
                System.out.println("当前消费线程：" + Thread.currentThread().getName() +
                        "， 洗盘子成功，消费数据为id: " + plate.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
