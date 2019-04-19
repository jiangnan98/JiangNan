package com.bing.util;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IPUtil {
	/**
	 * 获取本地IP地址
	 *
	 * @throws SocketException
	 */
	public static String getLocalIP() {
		if (isWindowsOS()) {
			return getWindowsLocalIP();
		} else {
			return getLinuxLocalIp();
		}
	}

	public static String localIP2String() {
		String ip = getLocalIP();
		if (ip != null) {
			String[] ips = ip.split("\\.");
			String ipnumber = "";
			for (String i : ips) {
				String ix = Integer.toHexString(Integer.parseInt(i));
				if (ix.length() < 2)
					ix = "0" + ix;
				ipnumber = ipnumber + ix;
			}
			return ipnumber;
		}
		return null;
	}

	/**
	 * 判断操作系统是否是Windows
	 *
	 * @return
	 */
	public static boolean isWindowsOS() {
		boolean isWindowsOS = false;
		String osName = System.getProperty("os.name");
		if (osName.toLowerCase().indexOf("windows") > -1) {
			isWindowsOS = true;
		}
		return isWindowsOS;
	}

	/**
	 * 获取本地Host名称
	 */
	public static String getWindowsLocalIP() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
		}
		return null;
	}

	/**
	 * 获取Linux下的IP地址
	 *
	 * @return IP地址
	 * @throws SocketException
	 */
	private static String getLinuxLocalIp() {
		String ip = null;
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				String name = intf.getName();
				if (!name.contains("docker") && !name.contains("lo")) {
					for (Enumeration<InetAddress> enumIpAddr = intf
							.getInetAddresses(); enumIpAddr.hasMoreElements();) {
						InetAddress inetAddress = enumIpAddr.nextElement();
						if (!inetAddress.isLoopbackAddress()) {
							String ipaddress = inetAddress.getHostAddress()
									.toString();
							if (!ipaddress.contains("::")
									&& !ipaddress.contains("0:0:")
									&& !ipaddress.contains("fe80")) {
								ip = ipaddress;
								System.out.println(ipaddress);
							}
						}
					}
				}
			}
		} catch (SocketException ex) {
			return null;
		}
		return ip;
	}

}
