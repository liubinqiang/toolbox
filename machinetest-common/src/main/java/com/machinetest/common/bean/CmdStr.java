package com.machinetest.common.bean;

/**
 * @author liubinqiang
 */
public class CmdStr {

    public static final String SYSTEM_INFO = "cat /etc/redhat-release";
    public static final String CPU_INFO = "cat /proc/cpuinfo |grep 'model name'";
    public static final String MEM_INFO = "free -m";


}
