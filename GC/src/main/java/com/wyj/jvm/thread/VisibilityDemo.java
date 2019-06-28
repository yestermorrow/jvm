package com.wyj.jvm.thread;

import java.util.concurrent.TimeUnit;

public class VisibilityDemo {
    private volatile boolean flag = true;

    public static void main(String[] args) throws InterruptedException{
        VisibilityDemo demo1 = new VisibilityDemo();
        System.out.println("代码开始了");
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (demo1.flag){
                    i++;
//                    System.out.println(i);
                }
                System.out.println("over");
            }
        });
        thread1.start();
        TimeUnit.SECONDS.sleep(2);
        // 设置is为false；使上面的线程结束while循环
        demo1.flag = false;
        System.out.println("被置为false了");
    }
}
