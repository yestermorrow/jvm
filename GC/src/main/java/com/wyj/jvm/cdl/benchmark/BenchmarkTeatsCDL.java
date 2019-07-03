package com.wyj.jvm.cdl.benchmark;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

// 高并发测试场景  2000并发操作
public class BenchmarkTeatsCDL {

    @Test
    public void benchmarkTest() throws IOException, Exception {
        CountDownLatch countDownLatch = new CountDownLatch(2000); // 2000个参与的线程
        for(int i =0; i < 2000; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.countDown();// -1
                    // TODO 并发执行这段代码
                    // service.method;
                    countDownLatch.await(); // 等待计数器归0
                } catch (Exception e) {

                }
            }).start();
        }

    }
}
