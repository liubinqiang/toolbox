package com.machinetest.programtool.service;

import com.machinetest.common.bean.CmdStr;
import com.machinetest.common.util.Cmd;
import com.machinetest.common.util.DateUtil;
import com.machinetest.common.util.StringUtil;
import com.machinetest.common.util.Util;
import com.machinetest.programtool.bean.PropertyKeys;
import com.machinetest.programtool.bean.PsInfoBean;
import com.machinetest.programtool.bean.ServerInfoBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author liubinqiang
 */
@Service
public class JavaServiceImpl extends BaseService implements IJavaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaServiceImpl.class);

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
        List<PsInfoBean> ps = new ArrayList<>();
        String psStr = run(CmdStr.PS);
        if (!StringUtil.isNullOrEmpty(psStr)) {
            String[] psArr = psStr.split(CmdStr.SEPARATOR);
            LOGGER.info("包含java的进程总数：{}", psArr.length);
            for (String p : psArr) {
                LOGGER.info("进程信息：{}", p);
                if (p.contains(PS_JAR) || p.contains(PS_TOMCAT)) {
                    p = StringUtil.manyBlanksToOne(p);
                    String[] pItems = p.split(" ");
                    LOGGER.info("进程信息：{}", pItems.length);
                    if (pItems.length >= 11) {
                        PsInfoBean info = new PsInfoBean();
                        info.setUser(pItems[0]);
                        info.setPid(pItems[1]);
                        info.setCpu(pItems[2]);
                        info.setMem(pItems[3]);
                        info.setVsz(pItems[4]);
                        info.setRss(pItems[5]);
                        info.setStat(pItems[7]);
                        info.setTime(pItems[9]);
                        String comKey = p.contains(PS_TOMCAT) ? PS_TOMCAT : PS_JAR;
                        Optional<String> comOpt = Arrays.stream(pItems).filter(i -> i.contains(comKey)).findFirst();
                        if (comOpt.isPresent()) {
                            info.setCommand(comOpt.get());
                        }
                        String startTimeStr = run(CmdStr.getPsStartTime(info.getPid()));
                        if (!StringUtil.isNullOrEmpty(startTimeStr)) {
                            startTimeStr = startTimeStr.replace(info.getPid(), "");
                            startTimeStr = startTimeStr.trim();
                            LOGGER.info("开始时间：{}", startTimeStr);
                            info.setStartTime(DateUtil.dateToStr(DateUtil.getDate(startTimeStr)));
                        }
                        String liveTimeStr = run(CmdStr.getPsLiveTime(info.getPid()));
                        if (!StringUtil.isNullOrEmpty(liveTimeStr)) {
                            liveTimeStr = liveTimeStr.replace(info.getPid(), "");
                            liveTimeStr = liveTimeStr.trim();
                            LOGGER.info("liveTimeStr：{}", liveTimeStr);
                            info.setLiveTime(liveTimeStr);
                        }
                        ps.add(info);
                    }
                }
            }
        }
        return ps;
    }
}
