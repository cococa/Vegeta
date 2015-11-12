package com.android.vageta.util;

import java.nio.charset.Charset;


/**
 * 字符编码的工具类
 * Created by cocoa on 2015/10/20.17:30
 * email:385811416@qq.com
 */
public final class CharsetUtil {

    /**
     * 根据字符编码获取Charset，默认UTF-8
     * @param charset
     * @return
     */
    public Charset getCharset(String charset) {
        if (charset == null || "".equals(charset)) {
            // return	Charset.defaultCharset();
            return Charset.forName("UTF-8");  //暂时不用default
        }
        return Charset.forName(charset);
    }


}