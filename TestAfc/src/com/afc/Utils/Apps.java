package com.afc.Utils;

import android.app.Application;

import com.afc.App.DbMgr;
import com.afc.App.LbsMgr;
import com.afc.BuildConfig;
import com.afc.db.dao.SortModelEntityDao;
import com.afc.db.entity.SortModelEntity;
import com.alipay.android.app.util.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.integration.okhttp.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.idroid.async.AsyncWorker;
import com.lidroid.util.Logger;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.LongTimeAuzHttp;
import com.squareup.okhttp.OkHttp;
import com.umeng.analytics.MobclickAgent;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import cn.trinea.android.common.util.PackageUtils;
import de.greenrobot.dao.query.QueryBuilder;

/**
 * Created by Administrator on 2016/5/5.
 */
public class Apps extends Application {
    // 获取注册时图形验证码的次数
    static int registernumber = 0;
    static int findpasswordnumber = 0;
    static int kpbnumber = 0;

    /*public static RefWatcher getRefWatcher(Context context) {
        Apps application = (Apps) context.getApplicationContext();
        return application.refWatcher;
    }*/

    //private RefWatcher refWatcher;

    @Override
    public void onCreate() {

        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
        //初始化
        EMClient.getInstance().init(this, options);
        //在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(true);

        if (BuildConfig.DEBUG) {
            LogUtils.i("开发版本，开启StrictMode");
//            StrictMode.setThreadPolicy(new
//                    StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectAll().penaltyLog().build());
//            StrictMode.setVmPolicy(new
//                    StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog()
//                    .penaltyDeath().build());
            //refWatcher = LeakCanary.install(this);
            //BlockCanary.install(this, new AppBlockCanaryContext()).start();
        }
        super.onCreate();
        new LbsMgr().init(getApplicationContext());
        LbsMgr.location();
        //XmppMgr.Host = BuildConfig.ImHost; IM相关代码
        AsyncWorker.execute(new Runnable() {
            @Override
            public void run() {
                DbMgr.init(getApplicationContext());

                deleteService_Ctity();

                JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
                JPushInterface.init(getApplicationContext()); // 初始化 JPush
                JPushInterface.setLatestNotificationNumber(getApplicationContext(), 5);

                //OkHttp.version = PackageUtils.getLocalVersionName(getApplicationContext());
                OkHttp.sOkHttpClient.interceptors().add(new OkHttpInterceptor(PackageUtils.getLocalVersionName
                        (getApplicationContext())));
                OkHttp.sOkHttpClient.setCache(new Cache(new File(getExternalCacheDir(), "kuservice"), 50 * 1024 * 1024));

                /**
                 * Glide网络方式直接使用okhttp库
                 */
                Glide.get(getApplicationContext()).register(GlideUrl.class, InputStream.class, new
                        OkHttpUrlLoader.Factory(LongTimeAuzHttp.longTimeOkHttpClient));

                if (BuildConfig.DEBUG) {
                    Logger.i("开发版本，关闭崩溃抓取。");
                    MobclickAgent.setCatchUncaughtExceptions(false);
                }

                //初始化本地文本数据
                LocalDataUtil.initAll(getApplicationContext());
            }
        });

    }

    /**
     * 每次启动时清理服务列表中最近访问中的数据的方法
     */
    private void deleteService_Ctity() {
        QueryBuilder<SortModelEntity> builder = DbMgr.daoSession.getSortModelEntityDao().queryBuilder();
        List<SortModelEntity> Soudeta_city = builder.limit(5).orderAsc(SortModelEntityDao.Properties._id).list();
        DbMgr.daoSession.getSortModelEntityDao().deleteAll();
        DbMgr.daoSession.getSortModelEntityDao().insertInTx(Soudeta_city);
        LogUtils.i("清理服务列表中最近访问中的数据..");
    }

    // 图形验证码的get，set方法
    public static int getRegisternumber() {

        return registernumber;
    }

    public static int getFindpasswordnumber() {

        return findpasswordnumber;
    }

    public static int getKpbnumber() {

        return kpbnumber;
    }

    public static void setRegisternumber(int registernumber) {

        Apps.registernumber = registernumber;
    }

    public static void setFindpasswordnumber(int findpasswordnumber) {

        Apps.findpasswordnumber = findpasswordnumber;
    }

    public static void setKpbnumber(int kpbnumber) {

        Apps.kpbnumber = kpbnumber;
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
        Glide.get(this).clearMemory();
    }

}