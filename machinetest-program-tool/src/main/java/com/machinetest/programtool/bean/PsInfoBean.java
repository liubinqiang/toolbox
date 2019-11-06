package com.machinetest.programtool.bean;

import lombok.Data;

/**
 * @author liubinqiang
 */
@Data
public class PsInfoBean {
    private String user;
    private String pid;
    private String cpu;
    private String mem;
    private String vsz;
    private String rss;
    private String stat;
    private String time;
    private String command;

    private String startTime;
    private String liveTime;

    private String threadCount;
}
