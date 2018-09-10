package com.wyj.jvm.SynchronizationTool;

/**
 * Created by wyj on 2018/9/10
 */
public class Synchronized {

    public static void main(String[] args) {

        // 对 Synchronized Class 对象进行加锁
        synchronized (Synchronized.class) {
        }
        m();
    }

    public static synchronized void m() {
    }
}