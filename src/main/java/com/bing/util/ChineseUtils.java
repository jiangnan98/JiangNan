package com.bing.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * User: NinetyOne
 * Date: 2019/4/26
 * Time: 14:59
 * To change this template use File | Setting | File Template.
 **/
public class ChineseUtils {
    /**
     * 得到 全拼
     *
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert.toUpperCase();
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i]&0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }
    /**
     * 判断字符串中是否包含中文
     * @param str
     * 待校验字符串
     * @return 是否为中文
     * @warn 不能校验是否为中文标点符号
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String cnStr = "李流冰";
        System.out.println(getPingYin(cnStr));
        System.out.println(getPinYinHeadChar(cnStr));
        System.out.println(getPinYinHeadChar(cnStr.substring(0,1)));
    }
}
