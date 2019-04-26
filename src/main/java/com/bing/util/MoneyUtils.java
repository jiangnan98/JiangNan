package com.bing.util;

import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;


/**
* @ClassName: MoneyUtils
* @Description: TODO(金额转换)
* @author Connie Lee
* @date 2018-05-28
*/
public class MoneyUtils {
	
	/**
     * 金额元转分
     * @see 注意:该方法可处理贰仟万以内的金额,且若有小数位,则不限小数位的长度
     * @see 注意:如果你的金额达到了贰仟万以上,则不推荐使用该方法,否则计算出来的结果会令人大吃一惊
     * @param amount  金额的元进制字符串
     * @return String 金额的分进制字符串
     */
    public static String moneyYuanToFen(String amount){
        if(isEmpty(amount)){
            return amount;
        }
        //传入的金额字符串代表的是一个整数
        if(-1 == amount.indexOf(".")){
            return Integer.parseInt(amount) * 100 + "";
        }
        //传入的金额字符串里面含小数点-->取小数点前面的字符串,并将之转换成单位为分的整数表示
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        //取到小数点后面的字符串
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        //amount=12.3
        if(pointBehind.length() == 1){
            return money_fen + Integer.parseInt(pointBehind)*10 + "";
        }
        //小数点后面的第一位字符串的整数表示
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        //小数点后面的第二位字符串的整数表示
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        //amount==12.03,amount=12.00,amount=12.30
        if(pointString_1 == 0){
            return money_fen + pointString_2 + "";
        }else{
            return money_fen + pointString_1*10 + pointString_2 + "";
        }
    }
    /**
     * 金额元转分
     * @see 注意:该方法可处理贰仟万以内的金额,且若有小数位,则不限小数位的长度
     * @see 注意:如果你的金额达到了贰仟万以上,则不推荐使用该方法,否则计算出来的结果会令人大吃一惊
     * @param amount  金额的元进制字符串
     * @return String 金额的分进制字符串
     */
    public static int moneyYuanToIntFen(String amount){
        if(isEmpty(amount)){
            return Integer.valueOf(amount);
        }
        //传入的金额字符串代表的是一个整数
        if(-1 == amount.indexOf(".")){
            return Integer.parseInt(amount) * 100 ;
        }
        //传入的金额字符串里面含小数点-->取小数点前面的字符串,并将之转换成单位为分的整数表示
        int money_fen = Integer.parseInt(amount.substring(0, amount.indexOf("."))) * 100;
        //取到小数点后面的字符串
        String pointBehind = (amount.substring(amount.indexOf(".") + 1));
        //amount=12.3
        if(pointBehind.length() == 1){
            return money_fen + Integer.parseInt(pointBehind)*10 ;
        }
        //小数点后面的第一位字符串的整数表示
        int pointString_1 = Integer.parseInt(pointBehind.substring(0, 1));
        //小数点后面的第二位字符串的整数表示
        int pointString_2 = Integer.parseInt(pointBehind.substring(1, 2));
        //amount==12.03,amount=12.00,amount=12.30
        if(pointString_1 == 0){
            return money_fen + pointString_2 ;
        }else{
            return money_fen + pointString_1*10 + pointString_2 ;
        }
    }
    /**
     * 判断输入的字符串参数是否为空
     * @return boolean 空则返回true,非空则flase
     */
    public static boolean isEmpty(String input) {
        return null==input || 0==input.length() || 0==input.replaceAll("\\s", "").length();
    }
    
    /**
     * 金额分转元
     * @see 注意:如果传入的参数中含小数点,则直接原样返回
     * @see 该方法返回的金额字符串格式为<code>00.00</code>,其整数位有且至少有一个,小数位有且长度固定为2
     * @param amount  金额的分进制字符串
     * @return String 金额的元进制字符串
     */
    public static String moneyFenToYuan(String amount){
        if(isEmpty(amount)){
            return amount;
        }
        if(amount.indexOf(".") > -1){
            return amount;
        }
        if(amount.length() == 1){
            return "0.0" + amount;
        }else if(amount.length() == 2){
            return "0." + amount;
        }else{
            return amount.substring(0, amount.length()-2) + "." + amount.substring(amount.length()-2);
        }
    }
    
    
    /**
    * @Title: StringToMoney
    * @Description: TODO(将String装换为元，最多保留两位小数)
    * @author Connie Lee
    * @date  2018-05-28
    * @param money
    * @return
    */
    public static String StringToMoney(String money) {
		DecimalFormat df = new DecimalFormat("#0.00");
		if(StringUtils.isBlank(money)){
			money="0";
		}
		double rtnVal = Double.parseDouble(money);
		return df.format(rtnVal);
	}
   
}
