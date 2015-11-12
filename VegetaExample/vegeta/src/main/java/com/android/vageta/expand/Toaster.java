package com.android.vageta.expand;

import android.content.Context;
import android.widget.Toast;

import com.cocoa.vegeta.R;

/**
 * 改进Toast重复创建的类
 * Created by cocoa on 2015/11/12.14:35
 * email:385811416@qq.com
 */
public class Toaster {

    private static Toast mToast;


    private static void initToast(Context mCotext, int res, int duration) {
//        synchronized (Toaster.class) {
//            if (mToast == null) {
        mToast = Toast.makeText(mCotext, res, duration);
//            }
//        }

    }

    private static void initToast(Context mCotext, String res, int duration) {
//        synchronized (Toaster.class) {
//            if (mToast == null) {
        mToast = Toast.makeText(mCotext, res, duration);
//            }
//        }
    }

    public static Toast makeText(Context mCotext, int res, int duration) {
        if (mToast == null) {
            initToast(mCotext, res, duration);
        } else {
            mToast.setText(res);
        }
        return mToast;
    }

    public static Toast makeText(Context mCotext, String res, int duration) {
        if (mToast == null) {
            initToast(mCotext, res, duration);
        } else {
            mToast.setText(res);
        }
        return mToast;
    }


}
