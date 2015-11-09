package com.cocoa.vegetaexample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by cocoa on 2015/11/5.17:02
 * email:385811416@qq.com
 */
public abstract class BaseActivity extends FragmentActivity {

   public abstract void onNetChanged(String status);

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }
}
