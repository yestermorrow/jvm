package com.wyj.jvm.util;

import java.util.HashMap;

public class MyHashMap extends HashMap {
    public MyHashMap(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    @Override
    public Object put(Object key, Object value) {
        return super.put(key, value);
    }
}
