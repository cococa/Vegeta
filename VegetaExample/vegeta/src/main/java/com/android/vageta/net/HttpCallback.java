package com.android.vageta.net;

/**
 * Created by cocoa on 2015/10/20.14:39
 * email:385811416@qq.com
 * 通用的网络请求回调
 */
public interface HttpCallback {

    public void onFail(String errorMsg);//暂时只返回错误msg

    public void onSuccess(String responeStr);//暂时只返回respone的body-msg


}
