package com.machinetest.programtool.service;

import com.machinetest.programtool.bean.PsInfoBean;
import com.machinetest.programtool.bean.ServerInfoBean;

import java.util.List;

/**
 * @author liubinqiang
 */
public interface IJavaService {
    ServerInfoBean getServerInfo();

    List<PsInfoBean> getPsInfos();
}
