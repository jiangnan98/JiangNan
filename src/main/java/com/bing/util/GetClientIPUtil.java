package com.bing.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 类说明:获得客户端ip地址
 *
 * @author LiuYafei E-mail:18837195325@139.com
 * @time 2016年2月1日 下午3:07:13
 */
public class GetClientIPUtil {
    private final static Logger logger = LoggerFactory.getLogger(GetClientIPUtil.class);

    public static String getIp(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if ("127.0.0.1".equals(ipAddress)) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    logger.error("get client ip fail,error:", e.getMessage(), e);
                }
            }
        }
        if (ipAddress != null) { // "***.***.***.***".length()
            if (ipAddress.indexOf("%") > -1) {
                ipAddress = ipAddress.split("%")[0];
            } else if (ipAddress.length() > 15 && ipAddress.indexOf(",") > -1) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
}
