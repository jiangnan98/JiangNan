package com.bing.socket;

import java.net.ServerSocket;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/25
 * Time: 11:16
 * socket服务器
 **/
public class SocketService {

    public static void main(String[] args){
        try{
            ServerSocket serverSocket = new ServerSocket(28888);
        }catch (Exception e){
            System.out.println("服务器启动失败");
        }
    }


}
