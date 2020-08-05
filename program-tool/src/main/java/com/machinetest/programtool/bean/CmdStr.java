package com.machinetest.programtool.bean;

/**
 * @author liubinqiang
 */
public class CmdStr {

    public static final String SEPARATOR = "<br/>";

    public static final String SYSTEM_INFO = "cat /etc/redhat-release";
    public static final String CPU_INFO = "cat /proc/cpuinfo |grep 'model name'";
    public static final String MEM_INFO = "free -m";
    public static final String PS = "ps -aux |grep java";

    public static final String DATETIME = "date +'%Y-%m-%d %T'";
    public static final String START_DATETIME = "who -b";

    public static final String PS_START_TIME = "ps -eo pid,lstart | grep ";
    public static final String PS_LIVE_TIME = "ps -eo pid,etime | grep ";


    public static String getPsThreadCount(String pid) {
        return "ps huH p " + pid + " | wc -l";
    }

    public static String getPsStartTime(String pid) {
        return PS_START_TIME + pid;
    }

    public static String getPsLiveTime(String pid) {
        return PS_LIVE_TIME + pid;
    }
}
