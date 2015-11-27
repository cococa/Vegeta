package com.cocoa.vegetaexample.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.android.vageta.util.BitmapUtil;
import com.android.vageta.util.SDCardManager;
import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.iwf.photopicker.PhotoPickerActivity;
import me.iwf.photopicker.utils.PhotoPickerIntent;

public class CompressImgActivity extends BaseActivity {
    private View choose_img;
    private static final int REQUEST_CODE = 0x1234;

    @Override
    public void onNetChanged(String status) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compressimg);
        choose_img = findViewById(R.id.choose_img);
        choose_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(CompressImgActivity.this);
                intent.setPhotoCount(1);
                intent.setShowCamera(true);
                intent.setShowGif(true);
                startActivityForResult(intent, REQUEST_CODE);

            }


        });
    }


    public void saveImg(String pathName) throws IOException {
        String nameFileString = SDCardManager.getDefauleSDCardPath() + "/Cheshang/upload_imgs/haha.jpg";
        String nameFileString1 = SDCardManager.getDefauleSDCardPath() + "/Cheshang/upload_imgs/haha1.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        int width = options.outWidth;
        int height = options.outHeight;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeFile(pathName);
        bitmap = bitmap.createScaledBitmap(bitmap,1200,900,true);
        FileOutputStream out = new FileOutputStream(new File(nameFileString));
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);

        FileOutputStream out1 = new FileOutputStream(new File(nameFileString1));
        Bitmap bitmap1= BitmapUtil.getSmallBitmap(nameFileString);
        bitmap1.compress(Bitmap.CompressFormat.JPEG, 80, out1);
        out.close();
        out1.close();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                try {
                    saveImg(photos.get(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
