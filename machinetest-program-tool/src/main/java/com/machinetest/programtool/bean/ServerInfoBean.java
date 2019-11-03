package com.machinetest.programtool.bean;

import lombok.Data;

/**
 * @author liubinqiang
 */
@Data
public class ServerInfoBean {
    private String os_arch;
    private String java_vm_name;
    private String java_runtime_version;
    private String os_name;
    private String os_version;
    private String user_name;
    private String java_version;

    private int availableProcessors;
    private String freeMemory;
    private String maxMemory;
    private String totalMemory;

    private String systemInfo;
    private String cpuInfo;
    private String memInfo;

    private String systemDateTime;
    private String systemStartDateTime;
}
