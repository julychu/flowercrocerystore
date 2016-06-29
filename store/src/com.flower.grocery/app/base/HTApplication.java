package com.flower.grocery.app.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.flower.common.SFApplication;

import java.util.HashSet;
import java.util.Set;

public class HTApplication extends SFApplication {
    //    public static String appVersionToUpgrade = "";
    private static boolean TuHasInit = false;
    private boolean isNewVersionLaunched = false;
    private String processName;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("Application", "应用程序被启动");

        ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == android.os.Process.myPid()) {
                processName = appProcess.processName;
            }
        }
        if (TextUtils.isEmpty(processName) || !processName.equalsIgnoreCase("com.sfht.m:push")) {
            initExceptJpush();
            initJpush(true);
        } else {
            initJpush(false);
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread thread, Throwable ex) {
                    try {
                        //异常以后由UT来收集
                        //UTCenter.getInstance().
//                        LogReporter.getInstance().saveExceptionSync(ex);
                    } catch (Throwable e) {}
                    android.os.Process.killProcess(android.os.Process.myPid());
                }
            });
        }
//        userFeatureSave();
    }

//    RPC.Interceptor rpcInterceptor = new RPC.Interceptor() {
//        @Override
//        public boolean intercept(RPC.Request<?> req, RPC.Response<?> res) {
//            if (res == null) {return false;}
//
//            Object hostObject = res.getHostObject();
//            if (hostObject == null) {return false;}
//
//            boolean cancel = false;
//            if (hostObject != null) {//这对一些ui做处理，当页面离开时，不需要继续回调的
//                if (hostObject instanceof Activity) {//Acivity如果已经结束生命周期，则不在返回
//                    cancel = ((Activity) hostObject).isFinishing();
//                } else if (hostObject instanceof Fragment) {//Fragment则比较难判断，只能有activity的时候可以下结论，
//                    Activity activity = ((Fragment) hostObject).getActivity();
//                    if (activity != null && activity.isFinishing()) {
//                        cancel = true;
//                    }
////                                else if (activity == null || ((Fragment) hostObject).isDetached()) {
////                                    cancel = true;
////                                }
//                } else if (hostObject instanceof View) {
//                    Context context = ((View) hostObject).getContext();
//                    if (context == null) {
//                        cancel = true;
//                    } else if (context instanceof Activity) {
//                        cancel = ((Activity) context).isFinishing();
//                    }
//                }
//            }
//
//            return cancel;
//        }
//    };
//
//    UTCenter.Preset ut_preset = new UTCenter.Preset() {
//        @Override
//        public String ut_aid() {
//            return GlobalConfig.getAppId();
//        }
//
//        @Override
//        public String ut_did() {
//            return "" + DeviceManager.shareInstance().deviceID();
//        }
//
//        @Override
//        public String ut_uid() {
//            if (UserCenter.shareInstance().isLogin()) {
//                return "" + UserCenter.shareInstance().UID();
//            } else {
//                return "";
//            }
//        }
//
//        @Override
//        public long ut_at() {
//            return Utils.getServerTime();
//        }
//    };

    private final String LOG_FILE_SPACE = ",";
    private final String LOG_FILE_EXT = ".txt";

//    LogIO.FileNameConstruction logFileConstruction = new LogIO.FileNameConstruction() {
//
//        @Override
//        public String log_constructFileName(long time) {
//            // 日志文件名: appid,deviceid,userid,version,channel,clientIP,datetime.txt
//            // 例: 4,174883021066740,1060985,2.6.5,AppStore,127.0.0.1,20160517.txt
//            StringBuilder builder = new StringBuilder(DeviceManager.shareInstance().appID());
//            builder.append(LOG_FILE_SPACE);
//
//            builder.append(DeviceManager.shareInstance().deviceID());
//            builder.append(LOG_FILE_SPACE);
//
//            builder.append(UserCenter.shareInstance().UID());
//            builder.append(LOG_FILE_SPACE);
//
//            builder.append(Res.appVersion());
//            builder.append(LOG_FILE_SPACE);
//
//            builder.append(GlobalConfig.getAppChannel());//采用原始渠道
//            builder.append(LOG_FILE_SPACE);
//
//            builder.append(Res.getLocalIpAddress());//采用原始渠道
//            builder.append(LOG_FILE_SPACE);
//
//            SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
//            Date date = new Date(Utils.getServerTime());
//            builder.append(sdf.format(date));
//
//            builder.append(LOG_FILE_EXT);
//
//            return builder.toString();
//        }
//    };

    private void initExceptJpush() {
//        //设置客户端ua，必须主线程开始时设置
//        try {
//            WebSettings settings = new WebView(Res.context()).getSettings();
//            String ua = settings.getUserAgentString();
//            OKHTTPAccessor._agent = ua;
//            WebPageLoadHelper.userAgent = ua;
//        } catch (Throwable e) {
//        }
//
//        //RPC的拦截器设置
//        RPC.setInterceptor(rpcInterceptor);
//
//        //风控sdk 接入
//        FMAgent.init(this, !GlobalConfig.isDebug());
//
//        //自定义埋点
//        LogReporter.getInstance().setFileNameConstruction(logFileConstruction);
//        UTCenter.getInstance().setPreset(ut_preset);
//
//        //能力位控制
//        CapabilityCenter.shareInstance().applicationDidLaunch(this);
//
//        //配置运行环境日志
////        LogReporter.loadLogManager();
//        Utils.checkReportedLogs(true);
//        APPLog.configAPPLog(GlobalConfig.isDebug());
//
//        //清除所有cookie
//        CookieSyncManager.createInstance(this);
//        android.webkit.CookieManager.getInstance().removeAllCookie();
////        Res.clearWebViewCache();//清空缓存 启动删除webview.db存在风险
//
//        //通知中心
//        BroadcastCenter.shareInstance().applicationDidLaunch(this);
//
//        //设备管理优先初始化，api请求依赖
//        DeviceManager.shareInstance().applicationDidLaunch(this);
//
//        //广告中心数据初始化 移到splash中初始化
//        ADCenter.shareInstance().applicationDidLaunch(this);
//
//        //主题风格初始化 移到splash中初始化
//        AppThemeCenter.shareInstance().applicationDidLaunch(this);
//
//        //用户模块第二优先初始化，
//        UserCenter.shareInstance().applicationDidLaunch(this);
//
//
//        //购物车模块
//        ShoppingCartManager.shareInstance().applicationDidLaunch(this);
//
//        //消息某块
//        NoticeCenter.shareInstance().applicationDidLaunch(this);
//
//        //Navigator
//        Navigator.shareInstance().applicationDidLaunch(this);
//
//        //微信sdk
//        WXSDKProxy.shareInstance().applicationDidLaunch(this);
//
//        //红点以及数据提示中心模块控制
//        BadgesCenter.shareInstance().applicationDidLaunch(this);
//        //GROWING IO
//        GrowingIOProxy.init(this);

        init();

        //涂图sdk
        initTUSdk();
        registerBroadCast();
    }

    private void registerBroadCast() {
//        BroadcastCenter.Method<HTApplication> method = new BroadcastCenter.Method<HTApplication>() {
//            @Override
//            public void onReceive(HTApplication observer, Context context, Intent intent) {
//                String action = intent.getAction();
//                if (Constants.USER_LOGINED_NOTIFICATION.equals(action) || Constants.USER_LOGOUT_NOTIFICATION.equals(action)
//                        || Constants.RESET_JPUSH_ALIAS_NOTIFICATION.equals(action)) {
//                    resetTagAndAlias();
//                }
//                if ((Constants.USER_LOGINED_NOTIFICATION.equals(action) || Constants.USER_LOGOUT_NOTIFICATION.equals(action))) {
//                    AppManager.getInstance().setGrowingIoInfo();//登录信息有变化，重新设置growingIo的信息
//                }
////                if (Constants.USER_LOGINED_NOTIFICATION.equals(action)) {
////                    if(SharedPreferenceService.getInstance().get(Constants.USER_FEATURE_SAVED,0) != 0) {//已经统计过了就不需要了
////                        BroadcastCenter.shareInstance().removeObserver(HTApplication.this);
////                        return;
////                    }
////                    HashMap<String,Object> hashMap = new HashMap<>();
////                    hashMap.put("pageTitle", "标题");
////                    Navigator.shareInstance().openViewWithIdentifierAndParams(context, Constants.PAGE_USER_FRETURE_SAVE_FIRST, hashMap);
////                    //这里不取消注册，因为有可能统计失败，需要第二次打开
////                }
//            }
//        };
//        BroadcastCenter.shareInstance().addObserver(this, Constants.USER_LOGINED_NOTIFICATION, method);
//        BroadcastCenter.shareInstance().addObserver(this, Constants.USER_LOGOUT_NOTIFICATION, method);
//        BroadcastCenter.shareInstance().addObserver(this, Constants.RESET_JPUSH_ALIAS_NOTIFICATION, method);
    }



    /**
     * 涂图sdk是否可用
     *
     * @return
     */
    public static boolean isTuAvailable() {
        return TuHasInit;
    }

    private void initTUSdk() {
//        try {
//            TuSdk.enableDebugLog(GlobalConfig.isDebug());
//            TuSdk.init(this.getApplicationContext(), Res.metaData(GlobalConfig.TUSDK_APPKEY));
//            TuHasInit = true;
//        } catch (Throwable e) {
//            TuHasInit = false;
//        }
//        TuSdk.messageHub().setStatus(this, R.string.lsq_initing);// 异步方式初始化滤镜管理器
    }

    @Override
    public boolean startInstrumentation(ComponentName className, String profileFile, Bundle arguments) {
        return super.startInstrumentation(className, profileFile, arguments);
    }

    private void init() {
//        AnalyticsConfig.setChannel(Res.metaData("UMENG_CHANNEL"));
//        MobclickAgent.setCatchUncaughtExceptions(!GlobalConfig.isDebug());
//        MobclickAgent.setCatchUncaughtExceptions(false);
//        MobclickAgent.openActivityDurationTrack(false);//禁止UMeng默认的页面统计方式
//        startLocation();

        //debug下，记录最后一次crash的栈
        /*Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable ex) {

                if (!GlobalConfig.isDebug()) {
                    MobclickAgent.reportError(HTApplication.this, ex);//上报到友盟
                }

                if (!GlobalConfig.isDebug()) {
                    UTCenter.getInstance().onError(AppManager.getInstance().currentActivity(), ex);
                } else {//DEBUG下，将异常存到文件，启动的时候显示出来，起到快速查看的作用
                    StringBuilder builder = new StringBuilder();
                    //异常名
                    builder.append("exception: " + ex.getClass().getName() + "\n");

                    StackTraceElement[] stackTrace = ex.getStackTrace();
                    for (int i = 1; i < stackTrace.length; i++) {
                        builder.append("\tat ");
                        builder.append(stackTrace[i - 1].toString()).append("\n");
                    }

                    Store.caches().store("sfht.exception.log",builder.toString().getBytes());
                    ex.printStackTrace();
                }

                if (!GlobalConfig.isDebug()) {
                    MobclickAgent.onKillProcess(HTApplication.this);
                }

                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });*/

            //启动的是否将数据show出来
//        if (GlobalConfig.isDebug()) {
//            byte[] data = Store.caches().data("sfht.exception.log");
//            if (data != null) {
//                String string = new String(data);
//                if (!TextUtils.isEmpty(string)) {
//                    TaskQueue.mainQueue().executeDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            Activity activity = AppManager.getInstance().currentActivity();
//                            if (activity != null) {
//                                Intent intent = new Intent(activity, ExceptionActivity.class);
//                                activity.startActivity(intent);
//                            }
//                        }
//                    }, 8000);
//                }
//            }
//        }
    }

    private void startLocation() {
//        LocationBiz.startLocate(new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//                if (aMapLocation != null) {
//                    uploadLocation(aMapLocation);
//                    APPLog.info("getLatitude() = " + aMapLocation.getLatitude() + " ;getLongitude = " + aMapLocation.getLongitude()
//                            + ";address = " + aMapLocation.getAddress());
//                    LocationBiz.stopLocation(this);
//
//                    UTCenter.getInstance().onLocationChanged(AppManager.getInstance().currentActivity(),aMapLocation.getLongitude(),aMapLocation.getLatitude());
//                }
//            }
//
//            @Override
//            public void onLocationChanged(Location location) {
//                UTCenter.getInstance().onLocationChanged(AppManager.getInstance().currentActivity(),location.getLongitude(),location.getLatitude());
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        });
    }

//    private void uploadLocation(AMapLocation aMapLocation) {
//        LocationBiz.asyncUploadLbs("" + aMapLocation.getLatitude(), "" + aMapLocation.getLongitude(), Res.localized(R.string.home_location), new HtAsyncWorkViewCB<Boolean>() {
//            @Override
//            public void onSuccess(Boolean result) {
//                super.onSuccess(result);
//                APPLog.info("上传定位结果 = " + result);
//            }
//
//            @Override
//            public void onFailure(Exception e) {
//                super.onFailure(e);
//                APPLog.info("上传定位结果失败！");
//            }
//        });
//    }

    private void initJpush(boolean isMainProcess) {
//        try {
//            JPushInterface.setDebugMode(GlobalConfig.isDebug());
//            JPushInterface.init(this);
//            if (isMainProcess) {
//                Set<String> tags = new HashSet<String>();
//                tags.add("a_" + Utils.getVersionName(this).replace('.', '_'));
//                tags.add("registerDevice");
//                tags.add(UserCenter.shareInstance().isLogin() ? "login" : "logout");
//
//                String alias = "";
//                if (UserCenter.shareInstance().UID() > 0) {
//                    alias = String.valueOf(UserCenter.shareInstance().UID());
//                }
//
//                JPushInterface.setAliasAndTags(this, alias, tags, new TagAliasCallback() {
//                    @Override
//                    public void gotResult(int i, String s, Set<String> set) {
//                        dealSetJpushAliasResult(i, s, set);
//                    }
//                });
//            }
//
//        } catch (Throwable e) {
////            e.printStackTrace();
//            JPushInterface.stopPush(this);
//        }
    }

    @Override
    protected void onActivityCreated(Activity var1, Bundle var2) {
        super.onActivityCreated(var1, var2);
    }

    @Override
    protected void onActivityResumed(Activity var1) {
//        Log.e("WX","onActivityResumed" + var1);
//        WXSDKProxy.shareInstance().onApplicationResumed(this);
    }

    @Override
    protected void onActivityPaused(Activity var1) {
//        Log.e("WX","onActivityPaused" + var1);
    }

    @Override
    protected void onActivityDestroyed(Activity var1) {
//        WXSDKProxy.shareInstance().onApplicationActivityDestroyed(this, var1);
    }

    /**
     * 是否是新版本的启动
     *
     * @return
     */
    public boolean isNewVersionLaunched() {
        return isNewVersionLaunched;
    }

    public void setIsNewVersionLaunched(boolean value) {
        isNewVersionLaunched = value;
    }

    private void initImageLoader() {
//        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
//                .cacheInMemory(false)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .cacheOnDisk(true)
//                .build();
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//                .defaultDisplayImageOptions(defaultOptions)
//                .denyCacheImageMultipleSizesInMemory()
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//                .diskCache(new UnlimitedDiskCache(StorageUtils.getOwnCacheDirectory(this, AppConstants.APP_IMAGE)))
//                .diskCacheSize(100 * 1024 * 1024).tasksProcessingOrder(QueueProcessingType.LIFO)
//                .memoryCache(new LruMemoryCache(2 * 1024 * 1024)).memoryCacheSize(2 * 1024 * 1024)
//                .threadPoolSize(3)
//                .build();
//        ImageLoader.getInstance().init(config);
    }

    //获取应用的data/data/....File目录
    public String getFilesDirPath() {
        return getFilesDir().getAbsolutePath();
    }

    //获取应用的data/data/....Cache目录
    public String getCacheDirPath() {
        return getCacheDir().getAbsolutePath();
    }

    private void resetTagAndAlias() {
        Set<String> tags = new HashSet<String>();
//        tags.add("a_" + Utils.getVersionName(this).replace('.', '_'));
        tags.add("registerDevice");
        String alias = "";
//        UserInfo user = UserCenter.shareInstance().user();
//        if (user != null && UserCenter.shareInstance().isLogin()) {
//            alias = String.valueOf(user.userId);
//            tags.add("login");
//        } else {
//            alias = "";
//            tags.add("loginout");
//        }
//        JPushInterface.setAliasAndTags(this, alias, tags, new TagAliasCallback() {
//            @Override
//            public void gotResult(int i, String s, Set<String> set) {
//                dealSetJpushAliasResult(i, s, set);
//            }
//        });
    }

    private void dealSetJpushAliasResult(int i, String s, Set<String> set) {
//        AppManager.getInstance().setIsJpushSetAliasSuc(i == 0);
        Log.d("JPushLog", "code=" + i + ",alias=" + s + "tags=" + set.toString());
    }

}
