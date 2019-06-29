package com.wyj.jvm.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class LockDemo {
    volatile int i = 0;

    private static Unsafe unsafe;
    static long valueOffset; // 属性偏移量，用于JVM去定位属性在内存中的地址
    static {
        try {
            // 由于java的保护机制，unsafe对象不能直接获取，只能通过反射
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe) theUnsafe.get(null);

            // CAS是硬件原语 ------ java语言 无法直接改内存。曲线通过对象及属性的定位方式
            valueOffset = unsafe.objectFieldOffset(LockDemo.class.getDeclaredField("i"));

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public void add() {
        int current;
        int value;
        // CAS操作结果是个boolean类型变量，有成功有失败，用do-while循环，当失败就重新获取
        do {
            //  i++; // 三次操作
//            current = i; // 读取当前值
            current = unsafe.getIntVolatile(this, valueOffset);// 效果等同于current = i
            value = current + 1; // 计算
        } while (!unsafe.compareAndSwapInt(this, valueOffset, current, value));

        // unsafe.compareAndSwapInt(对象，对象的属性偏移量，当前值，目标值);// CAS 底层API
        // boolean success = unsafe.compareAndSwapInt(this, valueOffset, current, value);

//        if (current ==i) {
//            i = value; // 赋值
//        } else {
//            // 值发生变化，修改失败
//        }

    }

    public static void main(String[] args) throws InterruptedException{
        LockDemo ld = new LockDemo();
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
