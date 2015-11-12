package com.cocoa.vegetaexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.vageta.expand.Toaster;
import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.R;


public class ClearTopActivity extends BaseActivity {

    private int number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleartop);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               startActivity(new Intent(ClearTopActivity.this,ClearTopActivity1.class));
                number++;
                Toaster.makeText(mContext, R.string.abc_action_bar_home_description + "---"+number, Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void onNetChanged(String status) {

    }
}
