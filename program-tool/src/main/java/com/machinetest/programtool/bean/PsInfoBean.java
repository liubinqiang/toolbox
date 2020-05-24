package com.machinetest.programtool.bean;

import lombok.Data;

/**
 * @author liubinqiang
 */
@Data
public class PsInfoBean {
    public PsInfoBean() {
    }

    public PsInfoBean(String[] pItems) {
        this.user = pItems[0];
        this.pid = pItems[1];
        this.cpu = pItems[2];
        this.mem = pItems[3];
        this.vsz = pItems[4];
        this.rss = pItems[5];
        this.stat = pItems[7];
        this.time = pItems[9];
    }

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
