package com.cocoa.vegetaexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.R;
import com.android.vageta.expand.AppManager;


public class NetworkListenerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networklistener);
        AppManager.INSTANCE.addActivity(this);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NetworkListenerActivity.this, ClearTopActivity1.class));
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onNetChanged(String status) {
        TextView btn = (TextView)findViewById(R.id.btn);
        btn.setText(status);
    }
}
