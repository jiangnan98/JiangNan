package com.bing.udp.test1;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/24
 * Time: 17:47
 * 启动器
 **/
public class start {

    public static void main(String[] args) {
        //启动UdpReceiver线程
        UdpReceiver receiver = new UdpReceiver();
        receiver.start();
        //启动UdpSender线程
        UdpSender sender = new UdpSender();
        sender.start();
    }
}
