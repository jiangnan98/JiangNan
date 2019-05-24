package com.bing.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/24
 * Time: 17:41
 * UDP客户端
 **/
public class UDPClient {

    /**
     * 基于UDP客户端程序的编写
     */
    public static void send() {
        try {
            //创建一个数据报对象。
            DatagramSocket ds = new DatagramSocket();
            //要发送的数据
            String str = "Hello,this is zhangsan";
            //构造一个发送数据包：
            //InetAddress.getByName("localhost"):获得本地ip地址
            //端口号指定为6000
            DatagramPacket dp = new DatagramPacket(
                    str.getBytes(),
                    str.length(),
                    InetAddress.getByName("localhost"),
                    8001);
            //发送数据包
            ds.send(dp);

            //创建字节数组
            byte[] buf = new byte[100];
            //构建接收数据的数据包
            DatagramPacket dpRecv = new DatagramPacket(buf, 100);
            //接收数据
            ds.receive(dpRecv);
            //打印数据
            System.out.println(new String(buf, 0, dpRecv.getLength()));
            //关闭数据报套接字
            ds.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        send();
//        String data="hello UDP";
//        DatagramSocket datagramSocket=null;
//        try {
//            //实例化套接字，并指定发送端口
//            datagramSocket=new DatagramSocket(8001);
//            //指定数据目的地的地址，以及目标端口
//            InetAddress destination=InetAddress.getByName("localhost");
//            DatagramPacket datagramPacket=
//                    new DatagramPacket(data.getBytes(), data.getBytes().length,destination,8081);
//            //发送数据
//            datagramSocket.send(datagramPacket);
//        } catch (SocketException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally{
//            datagramSocket.close();
//        }
    }
}
