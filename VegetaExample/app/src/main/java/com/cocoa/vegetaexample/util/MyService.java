package com.cocoa.vegetaexample.util;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by cocoa on 2015/11/5.16:00
 * email:385811416@qq.com
 */
public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
//        while(true)
        return null;
    }
}
