package com.bing.util;

import com.bing.constants.LogEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/20
 * Time: 11:59
 * To change this template use File | Setting | File Template.
 **/
public class LogUtils {
    /**
     * 获取业务日志logger
     *
     * @return
     */
    public static Logger getBussinessLogger() {
        return LoggerFactory.getLogger(LogEnum.BUSSINESS.getCategory());
    }

    /**
     * 获取平台日志logger
     *
     * @return
     */
    public static Logger getPlatformLogger() {
        return LoggerFactory.getLogger(LogEnum.PLATFORM.getCategory());
    }

    /**
     * 获取数据库日志logger
     *
     * @return
     */
    public static Logger getDBLogger() {
        return LoggerFactory.getLogger(LogEnum.DB.getCategory());
    }


    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
    }
}
