package com.flower.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * 一句话功能简述<br>
 * 功能详细描述
 *
 * @version 1.0
 * @author： RWJ
 * @date：202014/10/28 10:41
 */
public class SFApplication extends Application{
    public static String PACKAGENAME = "";
    public static SFApplication application = null;

//    public static ActivityLifecycleInterface activityLifecycleCallbacks = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        PACKAGENAME = this.getPackageName();

        //最新配置好资源拾取模块
//        Res.initialization(application);

//        activityLifecycleCallbacks = new ActivityLifecycleInterface() {
//            @Override
//            public void onActivityCreated(Activity var1, Bundle var2) {
//                SFApplication.this.onActivityCreated(var1,var2);
//            }
//
//            @Override
//            public void onActivityResumed(Activity var1) {
//                SFApplication.this.onActivityResumed(var1);
//            }
//
//            @Override
//            public void onActivityPaused(Activity var1) {
//                SFApplication.this.onActivityPaused(var1);
//            }
//
//            @Override
//            public void onActivityDestroyed(Activity var1) {
//                SFApplication.this.onActivityDestroyed(var1);
//            }
//        };
    }

    protected void onActivityCreated(Activity var1, Bundle var2){

    }

    protected void onActivityResumed(Activity var1) {

    }

    protected void onActivityPaused(Activity var1) {

    }

    protected void onActivityDestroyed(Activity var1) {

    }

    public void cancelWXDelayedRespond() {}
}