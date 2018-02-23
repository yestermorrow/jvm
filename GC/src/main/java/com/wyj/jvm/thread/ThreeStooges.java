package com.wyj.jvm.thread;

import java.util.HashSet;
import java.util.Set;

public class ThreeStooges {

    private final Set<String> stooges = new HashSet<String>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }
    public boolean isStooges(String name) {
        return stooges.contains(name);
    }

    public static void main(String[] args) {
        ThreeStooges threeStooges = new ThreeStooges();
        threeStooges.stooges.add("WYJ");
        System.out.println(threeStooges.isStooges("WYJ"));
    }
}