package com.cocoa.vegetaexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.R;


public class ClearTopActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleartop);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(ClearTopActivity.this,ClearTopActivity1.class));
            }
        });

    }

    @Override
    public void onNetChanged(String status) {

    }
}
