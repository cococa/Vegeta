package com.android.vageta.expand;


import android.util.Log;

import com.cocoa.vegeta.BuildConfig;

/**
 *
 * Created by cocoa on 2015/11/10.16:46
 * email:385811416@qq.com
 */
public class L {

    public static boolean isDebug = BuildConfig.DEBUG;

    public static String TAG = "vegeta_tag";

    public static void v(String msg) {
        if (isDebug)
            Log.v(TAG, msg);
    }

    public static void v(String msg, Throwable t) {
        if (isDebug)
            Log.v(TAG, msg, t);
    }

    public static void d(String msg) {
        if (isDebug)
            Log.d(TAG, msg);
    }

    public static void d(String msg, Throwable t) {
        if (isDebug)
            Log.d(TAG, msg, t);
    }

    public static void i(String msg) {
        if (isDebug)
            Log.i(TAG, msg);
    }

    public static void i(String msg, Throwable t) {
        if (isDebug)
            Log.i(TAG, msg, t);
    }

    public static void w(String msg) {
        if (isDebug)
            Log.w(TAG, msg);
    }

    public static void w(String msg, Throwable t) {
        if (isDebug)
            Log.w(TAG, msg, t);
    }

    public static void e(String msg) {
        if (isDebug)
            Log.e(TAG, msg);
    }

    public static void e(String msg, Throwable t) {
        if (isDebug)
            Log.e(TAG, msg, t);
    }


}
