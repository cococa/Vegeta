package com.cocoa.vegetaexample.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cocoa.vegetaexample.BaseActivity;
import com.cocoa.vegetaexample.MainActivity;
import com.cocoa.vegetaexample.R;


public class ClearTopActivity1 extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleartop);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ClearTopActivity1.this, MainActivity.class);
                it.putExtra("name","shenjun");

                it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(it);

            }
        });

    }

    @Override
    public void onNetChanged(String status) {

    }
}
