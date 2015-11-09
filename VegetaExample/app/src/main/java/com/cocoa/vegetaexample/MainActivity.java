package com.cocoa.vegetaexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.cocoa.vegetaexample.activity.AliOssActivity;
import com.cocoa.vegetaexample.activity.ClearTopActivity;
import com.cocoa.vegetaexample.activity.GlideActivity;
import com.cocoa.vegetaexample.activity.NetworkListenerActivity;
import com.cocoa.vegetaexample.activity.OKhttpActivity;
import com.cocoa.vegetaexample.activity.TestActivity;
import com.cocoa.vegetaexample.util.AppManager;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends BaseActivity {

    //    private MyHandler my;
    private Handler handler;
    private Timer timer = new Timer();
    private TimerTask task;
    private ListView listView ;
    private LayoutInflater inflate;
    private Class[] activityNameArray ={TestActivity.class,AliOssActivity.class,GlideActivity.class,OKhttpActivity.class, ClearTopActivity.class,NetworkListenerActivity.class};
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflate= LayoutInflater.from(this);
        name = getIntent().getStringExtra("name");
        Log.e("---------",name+"------------");
        listView = (ListView) findViewById(R.id.main_listview);
        listView.setAdapter(new MainAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, activityNameArray[position]));
            }
        });
        AppManager.INSTANCE.addActivity(this);

//        Glide.with(this);
//        Log.e("-----", Looper.getMainLooper() + "");
//        Log.e("-----", Looper.myLooper() + "");
//         my = new MyHandler();
//        handler = new Handler();
////        new Thread(){
////
////            @Override
////            public void run() {
////                super.run();
////                while(true){
////                    try {
////                        Thread.sleep(1*1000);
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    }
////                    Message message = new Message();
////                    message.what = 0x1234;
////                    my.sendMessage(message);
////                }
////            }
////        }.start();
//
//
//         handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case 1:
////                        RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//                        Toast.makeText(getApplicationContext(), "未支付成功", Toast.LENGTH_SHORT).show();
//
//                        break;
//                    default:
//                        break;
//                }
//                super.handleMessage(msg);
//            }
//        };
//
//        /**
//         * 初始化定时器任务
//         */
//        Thread timerThread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                task = new TimerTask() {
//                    @Override
//                    public void run() {
//                        Message message = new Message();
//                        message.what = 1;
//                        handler.sendMessage(message);
//                    }
//                };
//                //启动定时器
//                timer.schedule(task, 2000, 3000);//第一个2000表示2秒后开始执行，第二个3000表示下一次执行的间隔
//            }
//        });
//        timerThread.start();
//
//    }
//
//
//
//    class MyHandler extends Handler {
//
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            int what = msg.what;
//            switch (what){
//                case 0x1234:
//
//                    Toast.makeText(MainActivity.this,"1111111",Toast.LENGTH_SHORT).show();
//                    break;
//
//                 default:
//                break;
//
//            }
//
//
//        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.e("---------", name + "------------");
    }

    @Override
    public void onNetChanged(String status) {

    }

    class MainAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return activityNameArray.length;
        }

        @Override
        public String getItem(int position) {
            return activityNameArray[position].getSimpleName();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view =  inflate.inflate(R.layout.item_main, null);
            TextView textView  = (TextView) view.findViewById(R.id.main_textview);
            textView.setText(getItem(position));
            return view;
        }
    }



}
