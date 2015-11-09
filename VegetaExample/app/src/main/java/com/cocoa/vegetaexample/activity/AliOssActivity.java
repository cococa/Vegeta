package com.cocoa.vegetaexample.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.OSSServiceProvider;
import com.alibaba.sdk.android.oss.callback.SaveCallback;
import com.alibaba.sdk.android.oss.model.AuthenticationType;
import com.alibaba.sdk.android.oss.model.ClientConfiguration;
import com.alibaba.sdk.android.oss.model.OSSException;
import com.alibaba.sdk.android.oss.model.OSSFederationToken;
import com.alibaba.sdk.android.oss.model.StsTokenGetter;
import com.alibaba.sdk.android.oss.storage.OSSFile;
import com.alibaba.sdk.android.oss.util.OSSLog;
import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.R;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;


/**
 * 一, document https://docs.aliyun.com/?spm=5176.383663.9.9.bGDDzJ#/pub/oss/sdk/android-sdk&install
 * 二, demo https://github.com/alibaba/dpa-demo-android?spm=5176.730001.3.79.KxGqgO
 * 三, sdk https://docs.aliyun.com/?spm=5176.383663.9.9.bGDDzJ#/pub/oss/sdk/sdk-download&android
 * 四,https://docs.aliyun.com/?spm=5176.383663.9.9.bGDDzJ#/pub/oss/sdk/android-sdk&data
 * <p/>
 * 1.build.gradle      compile 'com.aliyun.dpa:oss-android-sdk:1.4.0'
 * 2.androidManifest
 * <meta-data android:name="com.alibaba.app.ossak" android:value="<your access key>"></meta-data>
 * <meta-data android:name="com.alibaba.app.osssk" android:value="<your secret key>"></meta-data>
 * <meta-data android:name="com.alibaba.app.ossbucketname" android:value="<your bucket name>"></meta-data>
 * <p/>
 * 3.add permission  <uses-permission android:name="android.permission.INTERNET"></uses-permission>
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
 * <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
 * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"></uses-permission>
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
 * 4
 * <p/>
 * <p/>
 * http://img1.dongdalou.com/cars/bigDFile.dat
 */
public class AliOssActivity extends BaseActivity {
    private static final int REQUEST_CODE = 0x1234;
    private Context context;
    private String accessKey;
    private String screctKey;
    private String bucketName;

    private TextView choose_img;
    private OSSServiceProvider ossService;
    private String TAG = AliOssActivity.class.getSimpleName();
    private String SecurityToken;
    private String Expiration;
    private long dateLong;
    private String AccessKeySecret;
    private String AccessKeyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alioss);
        choose_img = (TextView) findViewById(R.id.choose_img);
        context = this;
        try {
            accessKey = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData.getString("com.alibaba.app.ossak");
            screctKey = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData.getString("com.alibaba.app.osssk");
            bucketName = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA).metaData.getString("com.alibaba.app.ossbucketname");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        AccessKeySecret= "ZhhJI4qjZNiwlMX7LZPQ6cRJEE5q6zXzTaplPXMd";
                AccessKeyId= "STS.GWAdOQ2ih8FRLEwPwVHz";
                SecurityToken="CAES4gIIARKAAWt0LRI6NarOc4/w7yFB6svCE2rGpBVVJQ/Lp7hFc0PR01yHRH6fMVmaydwXbOcaxwJqpSTrAmg+Cr2+rpqHzzwAn3B6QMA90P/QL+5qi9z22pomuvbl/akNVCFXYZJaF7vHIUome82Ghb0TebIKf9unNJiJ2NosNh54zAsceXvMGhhTVFMuR1dBZE9RMmloOEZSTEV3UHdWSHoiEjM2NTcxNDA0MjgxNDMzMDQzMCoIYXBwLXJ1bGUw1d71qoQqOgZSc2FNRDVCSgoBMRpFCgVBbGxvdxIbCgxBY3Rpb25FcXVhbHMSBkFjdGlvbhoDCgEqEh8KDlJlc291cmNlRXF1YWxzEghSZXNvdXJjZRoDCgEqShAxODUzODU1MDI5MjEwNjEzUgUyNjg0MloPQXNzdW1lZFJvbGVVc2VyYABqEjM2NTcxNDA0MjgxNDMzMDQzMHIIYXBwLXJ1bGU="
    ;



        Expiration = "2015-10-08 02:52:42";
        OSSLog.enableLog();// 开启log
        ossService = OSSServiceProvider.getService();

        ossService.setApplicationContext(getApplicationContext());
        ossService.setGlobalDefaultHostId("oss-cn-hangzhou.aliyuncs.com");
        ossService.setAuthenticationType(AuthenticationType.FEDERATION_TOKEN);
//        ossService.setAuthenticationType(AuthenticationType.ORIGIN_AKSK);
        ossService.setGlobalDefaultStsTokenGetter(new StsTokenGetter() {
            @Override
            public OSSFederationToken getFederationToken() {
                // 为指定的用户拿取服务其授权需求的FederationToken
                try {
                    dateLong = transferStringDateToLong("yy-MM-dd HH:mm:ss", Expiration);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                AccessKeyId= AccessKeyId;
                dateLong=dateLong;

                return new OSSFederationToken(AccessKeyId, AccessKeySecret, SecurityToken, dateLong);
            }

        });
        ossService.setCustomStandardTimeWithEpochSec(System.currentTimeMillis() / 1000); // epoch时间，从1970年1月1日00:00:00 UTC经过的秒数

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectTimeout(15 * 1000); // 设置建连超时时间，默认为30s
        conf.setSocketTimeout(15 * 1000); // 设置socket超时时间，默认为30s
        conf.setMaxConnections(50); // 设置全局最大并发连接数，默认为50个
        conf.setMaxConcurrentTaskNum(10); // 设置全局最大并发任务数，默认10个
        ossService.setClientConfiguration(conf);


        choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(AliOssActivity.this);
                intent.setPhotoCount(1);
                intent.setShowCamera(true);
                intent.setShowGif(true);
                startActivityForResult(intent, REQUEST_CODE);

// if you want to Preview Photo
//    Intent intent = new Intent(mContext, PhotoPagerActivity.class);
//    intent.putExtra(PhotoPagerActivity.EXTRA_CURRENT_ITEM, position);
//    intent.putExtra(PhotoPagerActivity.EXTRA_PHOTOS, photoPaths);
//    startActivityForResult(intent, REQUEST_CODE);
//    onActivityResult


            }
        });


    }

    private static Long transferStringDateToLong(String formatDate, String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        Date dt = sdf.parse(date);
        return dt.getTime();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                new Task().execute(photos.get(0));
//                resumableUpload(photos.get(0));
            }
        }
    }


    // 断点上传
    public void resumableUpload(String fileString) {

        try {
            OSSFile bigfFile = ossService.getOssFile(ossService.getOssBucket(bucketName), "cars/test223.png");

            bigfFile.setUploadFilePath(fileString, "application/octet-stream");

//            bigfFile.setUploadFilePath(fileString, "application/octet-stream");
            bigfFile.ResumableUploadInBackground(new SaveCallback() {

                @Override
                public void onSuccess(String objectKey) {
                    Log.d(TAG, "[onSuccess] - " + objectKey + " upload success!");
                }

                @Override
                public void onProgress(String objectKey, int byteCount, int totalSize) {
                    Log.d(TAG, "[onProgress] - current upload " + objectKey + " bytes: " + byteCount + " in total: " + totalSize);
                }

                @Override
                public void onFailure(String objectKey, OSSException ossException) {
                    Log.e(TAG, "[onFailure] - upload " + objectKey + " failed!\n" + ossException.toString());
                    ossException.printStackTrace();
                    ossException.getException().printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNetChanged(String status) {

    }


    class Task extends AsyncTask<String,String,String >{

        @Override
        protected String doInBackground(String... params) {
            try {
                OSSFile bigfFile = ossService.getOssFile(ossService.getOssBucket(bucketName), "cars/test123.png");

                bigfFile.setUploadFilePath(params[0], "application/octet-stream");
                bigfFile.enableUploadCheckMd5sum();
                bigfFile.upload();

            } catch (Exception e) {
                e.printStackTrace();
            }

            Log.e("----------------","--------onSuccess---------");
            return null;
        }
    }

}
