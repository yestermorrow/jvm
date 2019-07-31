package com.wyj.jvm.thread;

import java.util.concurrent.TimeUnit;

public class zuo {
    private static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            public void run() {
                print();
            }
        }).start();
        TimeUnit.SECONDS.sleep(5);
        flag = false;
        System.out.println("flag set to false");
    }

    private static void print() {
        System.out.println(System.getProperty("java.vm.info"));
        while (flag) {
//            System.out.println("can not see flag change");
//            System.out.println(System.getProperty("java.vm.info"));
        }
        System.out.println("print over ");

        System.out.println(System.getProperty("java.vm.info"));
    }
}
