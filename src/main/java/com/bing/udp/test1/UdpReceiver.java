package com.bing.udp.test1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/24
 * Time: 17:47
 * 服务端
 **/
public class UdpReceiver extends Thread {
    @Override
    public void run() {
        try {
            //建立UDP的服务,监听端口
            DatagramSocket socket = new DatagramSocket(9090);
            //接受数据包
            byte[] temp = new byte[1024];
            DatagramPacket data = new DatagramPacket(temp, temp.length);
            Boolean flag = true;
            while (flag) {
                socket.receive(data);
                //输出发送方的相关信息
                String senderAddress = data.getAddress().getHostAddress();
                String senderHostName = data.getAddress().getHostName();
                System.out.println(senderHostName + "(" + senderAddress + ")" + " say: " + new String(temp, 0, data.getLength()));
            }
            //关闭资源
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}