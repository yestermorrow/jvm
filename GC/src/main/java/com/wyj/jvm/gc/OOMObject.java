package com.wyj.jvm.gc;

import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

public class OOMObject {

    static class OOMObject1 {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject1> list = new ArrayList<OOMObject1>();
        for (int i = 0; i < num; i++) {
            //稍作延迟，令监视曲线的变化更为明显
            Thread.sleep(50);
            list.add(new OOMObject1());
        }
        System.gc();
    }
    public static void main(String[] args) throws Exception {
        fillHeap(1000);
    }
}
