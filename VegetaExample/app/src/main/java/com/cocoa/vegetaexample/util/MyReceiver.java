package com.cocoa.vegetaexample.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.android.vageta.expand.AppManager;

/**
 * Created by cocoa on 2015/11/5.15:29
 * email:385811416@qq.com
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        // TODO Auto-generated method stub
//        //Toast.makeText(context, intent.getAction(), 1).show();
//        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
//        Toast.makeText(context, "mobile:" + mobileInfo.isConnected() + "\n" + "wifi:" + wifiInfo.isConnected()
//                + "\n" + "active:" + activeInfo.getTypeName(), Toast.LENGTH_SHORT).show();

        String status = NetworkUtil.getConnectivityStatusString(context);

//        Toast.makeText(context, status, Toast.LENGTH_LONG).show();

        AppManager.INSTANCE.getLastActivity().onNetChanged(status);


    }  //如果无网络连接activeInfo为null

}