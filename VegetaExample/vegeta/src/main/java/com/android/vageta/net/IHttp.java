package com.android.vageta.net;

import java.util.Map;

/**
 * Created by cocoa on 2015/10/20.14:38
 * email:385811416@qq.com
 * 通用的http接口，后续有需要再增加
 */
public interface IHttp {

    public static final String PARAMETER_PACKAGE = "package";
    public static final String PARAMETER_CLASS = "class";

    public void get(final HttpCallback mHttpCallback);

    public void post(final HttpCallback mHttpCallback);

    public void postAsync(HttpCallback mHttpCallback);

    public void getAsync(final HttpCallback mHttpCallback);


}
