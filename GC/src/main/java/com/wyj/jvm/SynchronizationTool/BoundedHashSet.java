package com.wyj.jvm.SynchronizationTool;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by wyj on 2018/5/10
 *
 * 使用Semaphore为容器设置边界
 * @param <T>
 */
public class BoundedHashSet<T> {

    private final Set<T> set;
    private final Semaphore sem;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<T>());
        sem = new Semaphore(bound);
    }

    public boolean add(T o) throws InterruptedException {
        sem.acquire();
        boolean wasAdded = false;
        try {
            wasAdded = set.add(o);
            return wasAdded;
        } finally {
            if (!wasAdded)
                sem.release();
        }
    }

    public boolean remove(Object o) {
        boolean wasRemoved = set.remove(o);
        if (wasRemoved)
            sem.release();
        return wasRemoved;
    }

    public static void main(String[] args) throws InterruptedException {
        BoundedHashSet boundedHashSet = new BoundedHashSet(5);
        for (int i = 0; i <= 10; i++) {
            boundedHashSet.add(i);
            System.out.println(i);}
    }
}