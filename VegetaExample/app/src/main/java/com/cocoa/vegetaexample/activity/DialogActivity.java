package com.cocoa.vegetaexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.vageta.widget.SimpleDialog;
import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.BuildConfig;
import com.cocoa.vegetaexample.R;


/**
 * Created by cocoa on 2015/11/10.09:56
 * email:385811416@qq.com
 */
public class DialogActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleartop);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BuildConfig.DEBUG) {
                    final SimpleDialog mMaterialDialog = new SimpleDialog(DialogActivity.this);
                    mMaterialDialog.setTitle("提示");
                    mMaterialDialog.setMessage("确定要删除吗?");
                    mMaterialDialog.setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();
                        }
                    });
                    mMaterialDialog.setNegativeButton("取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mMaterialDialog.dismiss();

                        }
                    });

                    mMaterialDialog.show();
                }
            }
        });

    }

    @Override
    public void onNetChanged(String status) {

    }
}
