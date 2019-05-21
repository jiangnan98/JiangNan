package com.bing.util;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/5/20
 * Time: 16:37
 * To change this template use File | Setting | File Template.
 **/
public class ArrUtil {
    public static Object[] arrayTest1(Object[] arr){
        //用来记录去除重复之后的数组长度和给临时数组作为下标索引
        int t = 0;
        Object[] tempArr = new Object[arr.length];
        for(int i = 0; i < arr.length; i++){
            //声明一个标记，并每次重置
            boolean isTrue = true;
            for(int j=i+1;j<arr.length;j++){
                //如果有重复元素，改变标记状态并结束当次内层循环
                if(arr[i]==arr[j]){
                    isTrue = false;
                    break;
                }
            }
            //判断标记是否被改变，如果没被改变就是没有重复元素
            if(isTrue){
                tempArr[t] = arr[i];
                //到这里证明当前元素没有重复，那么记录自增
                t++;
            }
        }
        //声明需要返回的数组，这个才是去重后的数组
        Object[]  newArr = new Object[t];
        //用arraycopy方法将刚才去重的数组拷贝到新数组并返回
        System.arraycopy(tempArr,0,newArr,0,t);
        return newArr;
    }
    public static void main(String[] args){
        String[] a = new String[]{"1","2","3","4","4"};
        Object[] b = arrayTest1(a);
        StringBuffer stringBuffer = new StringBuffer();
        for (Object s : b) {
            stringBuffer.append(s).append(",");
        }
        System.out.println(stringBuffer.toString());
    }
}
