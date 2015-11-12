package com.android.vageta.util;

import java.util.Random;

/**
 * Created by cocoa on 2015/10/20.16:26
 * email:385811416@qq.com
 */
public class RandomUtil {


    public static final String UPPER_CASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LOWER_CASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final String NUMBERS = "0123456789";
    public static final String TOTAL_CHARS = UPPER_CASE_LETTERS + LOWER_CASE_LETTERS + NUMBERS;


    /**
     * 产生随机数
     *
     * @param length  生成字符串的长度
     * @return
     */
    public static String getRandomString(int length) {
        int cahr_len = TOTAL_CHARS.length();
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(cahr_len);
            sb.append(TOTAL_CHARS.charAt(number));
        }
        return sb.toString();
    }

}
