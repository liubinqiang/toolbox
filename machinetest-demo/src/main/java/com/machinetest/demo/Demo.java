package com.machinetest.demo;

import com.machinetest.common.util.Cmd;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author liubinqiang
 */
public class Demo {
    public static void main(String[] args) {
        String result = Cmd.run("wmic cpu list brief");
        System.out.println(result);
    }
}
