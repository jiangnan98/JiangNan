package com.bing.udp.test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/24
 * Time: 17:46
 * 客户端
 **/
public class UdpSender extends Thread {
    @Override
    public void run() {
        try {
            //建立UDP的服务
            DatagramSocket socket = new DatagramSocket();
            //准备数据包发送
            //从系统输入读取输入
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in)) ;
            String line = null;
            while((line = in.readLine()) != null){
                //我做测试,写的是本机地址,群聊需要写广播地址,比如:192.168.137.255
                DatagramPacket data = new DatagramPacket(line.getBytes(), line.getBytes().length, InetAddress.getByName("127.0.0.1"), 9090);
                //发送数据
                socket.send(data);
            }
            //关闭socket
            socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
