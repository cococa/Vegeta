package com.android.vageta.util;

import java.util.Random;

/**
 * Created by cocoa on 2015/10/20.16:26
 * email:385811416@qq.com
 */
public class RandomUtil {

        /**
         * 产生随机数
         *
         * @param length
         * @return
         */
        public static String getRandomString(int length) { //length表示生成字符串的长度
            String base = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKMLNOPQRSTUVWXYZ";
            Random random = new Random();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < length; i++) {
                int number = random.nextInt(base.length());
                sb.append(base.charAt(number));
            }
            return sb.toString();
        }

}
