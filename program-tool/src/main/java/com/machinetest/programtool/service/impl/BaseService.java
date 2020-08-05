package com.machinetest.programtool.service.impl;

import com.machinetest.programtool.util.Cmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @author liubinqiang
 */
public class BaseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    protected static String run(String cmd) {
        try {
            return Cmd.run(cmd);
        } catch (IOException e) {
            LOGGER.error("执行命令报错：", e);
            return "";
        }
    }
}
