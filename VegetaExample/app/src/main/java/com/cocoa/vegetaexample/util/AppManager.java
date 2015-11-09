package com.cocoa.vegetaexample.util;

import android.content.Context;

import com.cocoa.vegetaexample.BaseActivity;

import java.util.Stack;

/**
 * Created by cocoa on 2015/11/5.15:27
 * email:385811416@qq.com
 */
public enum AppManager {

    INSTANCE;

    private static Stack<BaseActivity> activityStack;

    private AppManager() {
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(BaseActivity activity) {
        if (activityStack == null) {
            activityStack = new Stack<BaseActivity>();
        }
        activityStack.add(activity);
    }


    public BaseActivity getLastActivity() {
        if (activityStack == null) {
            activityStack = new Stack<BaseActivity>();
        }
        return activityStack.lastElement();
    }


    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public BaseActivity currentActivity() {
        BaseActivity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        BaseActivity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(BaseActivity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (BaseActivity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
            System.exit(0);
        } catch (Exception e) {
            System.exit(1);
        }
    }
}
