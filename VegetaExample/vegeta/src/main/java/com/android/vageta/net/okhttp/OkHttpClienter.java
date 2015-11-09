package com.android.vageta.net.okhttp;


import com.android.vageta.net.Encrypt;
import com.android.vageta.net.HttpCallback;
import com.android.vageta.net.IHttp;
import com.android.vageta.util.EncryptUtil;
import com.android.vageta.util.RandomUtil;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.Map;

/**
 * Created by android on 2015/10/20.14:45
 * email:385811416@qq.com
 * okHttp 的实现，展示只实现了post的异步方法，
 */
public class OkHttpClienter extends Encrypt implements IHttp  {

    private OkHttpClient mOkHttpClient;
    private String url;
    private Map<String, String> map;

    private static final String PUB_KEY = "c8b697c75a0050c4d8513bf1b73be184";

    public OkHttpClienter(OkHttpClient okHttpClient, String url, Map<String, String> map) {
        mOkHttpClient = okHttpClient;
        this.url = url;
        this.map = map;
    }


    @Override
    public void get(final HttpCallback mHttpCallback) {

    }

    @Override
    public void post(final HttpCallback mHttpCallback) {

    }

    @Override
    public void postAsync(final HttpCallback mHttpCallback) {
        String curTime = String.valueOf(System.currentTimeMillis() / 1000);
        String random = RandomUtil.getRandomString(8);
        String packageName =map.get(PARAMETER_PACKAGE);
        String className =map.get(PARAMETER_CLASS);
        map.put("checksum", getChecksum(curTime, packageName, className));
        String bodyStr = new Gson().toJson(map);
        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream;charset=utf-8"), bodyStr);

        Request.Builder builder = new Request.Builder()
                .url(url)
                .post(body);
        builder.header("Random", random);
        builder.header("UTC-Timestemp", curTime);
        builder.header("Signature", EncryptUtil.sha1(bodyStr + curTime + random + PUB_KEY));
        builder.header("Content-Type", "application/json; charset=utf-8");
        builder.header("User-Agent", "advisor/11.10.0 (Android; MI 4LTE; 4.4.4)");

        Request request = builder.build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                mHttpCallback.onFail("error");
            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    if (!response.isSuccessful()) {
                        mHttpCallback.onFail("error");
                    }
                    mHttpCallback.onSuccess(response.body().string());

                } catch (Exception e) {
                    mHttpCallback.onFail("error");
                }

//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//                Headers responseHeaders = response.headers();
//                for (int i = 0; i < responseHeaders.size(); i++) {
//                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
//
//                System.out.println(response.body().string());
            }
        });
    }


    @Override
    public void getAsync(final HttpCallback mHttpCallback) {

    }
}
