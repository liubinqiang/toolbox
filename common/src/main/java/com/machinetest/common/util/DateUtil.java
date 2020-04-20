package com.machinetest.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author liubinqiang
 */
public class DateUtil {
    public static Date getDate(String dateStr) {
        if (StringUtil.isNullOrEmpty(dateStr)) {
            return new Date(0);
        } else {
            return new Date(dateStr);
        }
    }

    public static String dateToStr(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(date);
    }
}
