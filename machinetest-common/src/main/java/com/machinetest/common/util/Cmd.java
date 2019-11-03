package com.machinetest.common.util;

import com.machinetest.common.bean.CmdStr;

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
                result = result + temp + CmdStr.SEPARATOR;
            }
            if (result.length() > CmdStr.SEPARATOR.length()) {
                result = result.substring(0, result.length() - CmdStr.SEPARATOR.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
