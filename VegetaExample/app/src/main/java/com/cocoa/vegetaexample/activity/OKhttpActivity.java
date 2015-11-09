package com.cocoa.vegetaexample.activity;

import android.os.Bundle;


import com.android.vageta.util.EncryptUtil;
import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.R;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;


public class OKhttpActivity extends BaseActivity {

    private String url = "http://fapis.365eche.com:8181/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        initViews();
    }


    public void initViews() {
//        findViewById(R.id.HELLO_OKHTTP).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String userAgent = "advisor/11.10.0 (Android; MI 4LTE; 4.4.4)";//代码里自己获取初始化
//                apiConfig.setUrl(url)
//                        .setUserAgent(userAgent)
//                        .setPUBKEY("2d44d8e2fc0b9517b5152ede2cf042b1")//PUBKEY向系统管理员索取，每个app不一样。
//                        .setPackageKeyMap(pkgKeyMap);//package_key是包的访问密钥（向管理员索取）,不同包可以不一样。
//                OkHttpClientManager.getInstance().init(apiConfig);
//
//
//            }
//        });
    }

    private final OkHttpClient client = new OkHttpClient();

    public void run() throws Exception {
        String bodyStr = "{\"package\":\"app_client\",\"class\":\"CHECK_APP_UPGRADE\",\"system\":\"android\",\"app_id\":\"1\",\"version_code\":\"1.0.0\"}";
        String curTime = String.valueOf(System.currentTimeMillis() / 1000);
        String random = "5CkitZnm";

        RequestBody body = RequestBody.create(MediaType.parse("application/octet-stream;charset=utf-8"), bodyStr);
        Request.Builder builder = new Request.Builder()
                .url("http://fapis.365eche.com:8181/")
                .post(body);
        builder.header("Random", random);
        builder.header("UTC-Timestemp", curTime);
        builder.header("Signature", EncryptUtil.sha1(bodyStr + curTime + random + "2d44d8e2fc0b9517b5152ede2cf042b1"));
        builder.header("Content-Type", "application/json; charset=utf-8");
        builder.header("User-Agent", "advisor/11.10.0 (Android; MI 4LTE; 4.4.4)");

        Request request = builder.build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                System.out.println(response.body().string());
            }
        });
    }

    @Override
    public void onNetChanged(String status) {

    }
}