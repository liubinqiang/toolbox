package com.machinetest.demo.juc;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liubinqiang
 */
public class Demo {
    private static final HashSet<String> abc = new HashSet<String>();

    public static void main(String[] args) {
        abc.add("ddd");
        System.out.println(abc.size());

    }
}
