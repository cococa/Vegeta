package com.android.vageta;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;



/**
 * Created by cocoa on 2015/10/20.14:21
 * email:385811416@qq.com
 */
public abstract class BaseFragmentActivity extends FragmentActivity {

    public Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    public abstract void onNetChanged(String status);

}
