package com.cocoa.vegetaexample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.Constant;
import com.cocoa.vegetaexample.R;


public class GlideActivity extends BaseActivity {

    private static Activity mActivity;
    private ListView glideListview ;
    private GlideAdapter mGlideAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glidetest);
        mActivity = this;
        glideListview = (ListView) findViewById(R.id.glide_listview);
        mGlideAdapter = new GlideAdapter(Constant.IMAGES,mActivity);
        glideListview.setAdapter(mGlideAdapter);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("--------","----onDestroy-------");
    }

    @Override
    public void onNetChanged(String status) {

    }
}
