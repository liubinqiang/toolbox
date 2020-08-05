package com.machinetest.programtool.service.impl;


import com.machinetest.programtool.bean.CmdStr;
import com.machinetest.programtool.util.StringUtil;
import com.machinetest.programtool.bean.PsInfoBean;
import com.machinetest.programtool.bean.ServerInfoBean;
import com.machinetest.programtool.service.IJavaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author liubinqiang
 */
@Slf4j
@Service
public class JavaServiceImpl extends BaseService implements IJavaService {

    private static final String PS_JAR = ".jar";
    private static final String PS_TOMCAT = "-Dcatalina.base=";

    @Override
    public ServerInfoBean getServerInfo() {
        ServerInfoBean info = new ServerInfoBean();

        /*Properties ps = System.getProperties();
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
        info.setTotalMemory(Util.bytesToView(r.totalMemory()));*/

        info.setSystemInfo(run(CmdStr.SYSTEM_INFO));
        info.setCpuInfo(run(CmdStr.CPU_INFO));
        info.setMemInfo(run(CmdStr.MEM_INFO));
        info.setSystemDateTime(run(CmdStr.DATETIME));
        info.setSystemStartDateTime(run(CmdStr.START_DATETIME));
        return info;
    }

    @Override
    public List<PsInfoBean> getPsInfos() {
        String psStr = run(CmdStr.PS);
        return getPsInfo(psStr);
    }

    private List<PsInfoBean> getPsInfo(String psStr) {
        List<PsInfoBean> ps = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(psStr)) {
            String[] psArr = psStr.split(CmdStr.SEPARATOR);
            log.info("包含java的进程总数：{}", psArr.length);
            try {
                for (String p : psArr) {
                    log.info("进程信息：{}", p);
                    if (p.contains(PS_TOMCAT) || p.contains(PS_JAR)) {
                        p = StringUtil.manyBlanksToOne(p);
                        String[] pItems = p.split(" ");
                        log.info("进程信息：{}", pItems.length);
                        if (pItems.length >= 11) {
                            PsInfoBean info = new PsInfoBean(pItems);
                            String comKey = p.contains(PS_TOMCAT) ? PS_TOMCAT : PS_JAR;
                            Optional<String> comOpt = Arrays.stream(pItems).filter(i -> i.contains(comKey)).findFirst();
                            if (comOpt.isPresent()) {
                                String com = comOpt.get();
                                if (PS_TOMCAT.equals(comKey)) {
                                    com = com.replace(PS_TOMCAT, "");
                                } else {
                                    String[] coms = com.split("/");
                                    com = coms[coms.length - 1];
                                }
                                info.setCommand(com);
                            }
                            info.setStartTime(getStartTime(info.getPid()));
                            info.setLiveTime(getLiveTime(info.getPid()));
                            info.setThreadCount(run(CmdStr.getPsThreadCount(info.getPid())));
                            ps.add(info);
                        }
                    }
                }
            } catch (Exception ex) {
                log.error("读取线程信息报错：", ex);
            }
        }
        return ps;
    }

    private String getLiveTime(String pid) {
        String liveTimeStr = run(CmdStr.getPsLiveTime(pid));
        if (!StringUtil.isNullOrEmpty(liveTimeStr)) {
            liveTimeStr = liveTimeStr.replace(pid, "");
            liveTimeStr = liveTimeStr.trim();
            log.info("liveTimeStr：{}", liveTimeStr);
        }
        return liveTimeStr;
    }

    private String getStartTime(String pid) {
        String startTimeStr = run(CmdStr.getPsStartTime(pid));
        log.info("获取开始时间：{}", startTimeStr);
        if (!StringUtil.isNullOrEmpty(startTimeStr)) {
            startTimeStr = startTimeStr.replace(pid, "");
            startTimeStr = startTimeStr.trim();
        }
        log.info("处理后开始时间：{}", startTimeStr);
        return startTimeStr;
    }

}
