package com.bing.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/24
 * Time: 17:39
 * udp服务器
 **/
public class UDPUtils {

    /**
     * 基于UDP服务器端程序的编写
     */
    public static void recv() {
        try {
            //创建数据报套接字对象，绑定端口号为6000
            DatagramSocket ds = new DatagramSocket(8001);
            //构建数据包接收数据：
            //创建字节数组
            byte[] buf = new byte[100];
            //创建数据包对象，它的长度不能超过数组的长度，我们把它设为100
            DatagramPacket dp = new DatagramPacket(buf, 100);
            //接收数据
            ds.receive(dp);
            //打印数据
            //getLength方法返回实际接收数；getData方法返回数据，返回格式为字节数组
            System.out.println(new String(buf, 0, dp.getLength()));

            //给客户端答复
            String str = "Welcome you!";
            //getAddress()、getPort()方法，可获得发送数据时的ip地址、端口号
            DatagramPacket dpSend = new DatagramPacket(
                    str.getBytes(),
                    str.length(),
                    dp.getAddress(),
                    dp.getPort());
            ds.send(dpSend);
            ds.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        recv();
//        DatagramSocket datagramSocket=null;
//        try {
//            //监视8081端口的内容
//            datagramSocket=new DatagramSocket(8081);
//            byte[] buf=new byte[1024];
//
//            //定义接收数据的数据包
//            DatagramPacket datagramPacket = new DatagramPacket(buf, 0, buf.length);
//            datagramSocket.receive(datagramPacket);
//
//            //从接收数据包取出数据
//            String data=new String(datagramPacket.getData() , 0 ,datagramPacket.getLength());
//            System.out.println(data);
//        } catch (SocketException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            datagramSocket.close();
//        }
    }

}
