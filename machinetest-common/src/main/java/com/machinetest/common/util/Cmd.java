package com.machinetest.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author liubinqiang
 */
public class Cmd {

    public static String run(String cmd) {
        String result = "";
        try {
            String[] cmds = {"/bin/sh", "-c", cmd};
            Process p = Runtime.getRuntime().exec(cmds);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                result = result + temp + "<br/>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
