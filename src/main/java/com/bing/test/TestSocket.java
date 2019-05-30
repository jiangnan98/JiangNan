package com.bing.test;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/25
 * Time: 11:46
 * 测试线程如何强行关闭
 **/
public class TestSocket {

    Map<String,String> mapList = new HashMap<>();
    
    public static void main(String[] args){
        new TestSocket();
    }

    public TestSocket(){
        new AA().start();
    }
    class AA extends Thread{
        @Override
        public void run() {
            while(this.isAlive()) {
                System.out.println(this.isAlive());
            }
        }
    }
}
