package com.wyj.jvm.util;

import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap(9, 0.75f);
        hashMap.put(1, "哈哈");
        Object value = hashMap.get(1);
    }

    @Test
    public void testHashMap() throws Exception {
        System.out.println("=====================");
        Map<String, String> m = new HashMap<String, String>();
        for (int i = 0; i < 18; i++) {
            m.put((char) (i + 65) + (char) (i + 66) + (char) (i + 67) + "", i + ">>>http://blog.csdn.net/unix21/");
        }
        System.out.println("====================");
    }
}

