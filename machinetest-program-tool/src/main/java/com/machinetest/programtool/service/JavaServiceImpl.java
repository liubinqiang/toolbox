package com.machinetest.programtool.service;

import com.machinetest.common.bean.CmdStr;
import com.machinetest.common.util.Cmd;
import com.machinetest.common.util.Util;
import com.machinetest.programtool.bean.PropertyKeys;
import com.machinetest.programtool.bean.ServerInfoBean;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @author liubinqiang
 */
@Service
public class JavaServiceImpl implements IJavaService {
    @Override
    public ServerInfoBean getServerInfo() {
        ServerInfoBean info = new ServerInfoBean();
        Properties ps = System.getProperties();
        info.setJava_runtime_version(ps.getProperty(PropertyKeys.JAVA_RUNTIME_VERSION));
        info.setJava_version(ps.getProperty(PropertyKeys.JAVA_VERSION));
        info.setJava_vm_name(ps.getProperty(PropertyKeys.JAVA_VM_NAME));
        info.setOs_arch(ps.getProperty(PropertyKeys.OS_ARCH));
        info.setOs_name(ps.getProperty(PropertyKeys.OS_NAME));
        info.setOs_version(ps.getProperty(PropertyKeys.OS_VERSION));
        info.setUser_name(ps.getProperty(PropertyKeys.USER_NAME));

        Runtime r = Runtime.getRuntime();
        info.setAvailableProcessors(r.availableProcessors());
        info.setFreeMemory(Util.bytesToView(r.freeMemory()));
        info.setMaxMemory(Util.bytesToView(r.maxMemory()));
        info.setTotalMemory(Util.bytesToView(r.totalMemory()));

        info.setSystemInfo(Cmd.run(CmdStr.SYSTEM_INFO));
        info.setCpuInfo(Cmd.run(CmdStr.CPU_INFO));
        info.setMemInfo(Cmd.run(CmdStr.MEM_INFO));
        return info;
    }
}
