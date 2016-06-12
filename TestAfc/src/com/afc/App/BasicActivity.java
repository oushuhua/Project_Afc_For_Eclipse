package com.afc.App;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.afc.Utils.AccountMgr;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.OkHttp;

import org.simple.eventbus.EventBus;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BasicActivity extends Activity {
    public final String TAG = toString();
    /**
     * 是否开启eventbus，默认关闭；打开后无法关闭，会在类销毁时自动关闭
     */
    private boolean enableEventBus = false;
    protected static final String EXITAPP = "com.kuparts.exit_app";
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals(EXITAPP)) {
                BasicActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        IntentFilter intent = new IntentFilter(EXITAPP);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, intent);
        if (AccountMgr.isLogon(this)) {
            AuzHttp.SIG = AccountMgr.getAuzStr(getApplicationContext());
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        // 参数tag不要动，必须为toString()
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
        if (enableEventBus) {
            EventBus.getDefault().unregister(this);
        }
        OkHttp.sOkHttpClient.cancel(TAG);
       /* if (Host.DEBUG) {
            RefWatcher refWatcher = App.getRefWatcher(this);
            refWatcher.watch(this);
        }*/
    }

    @Override
    protected void onResume() {

        super.onResume();
        //MobclickAgent.onResume(this);友盟调用方法
    }

    @Override
    protected void onPause() {

        super.onPause();
        //MobclickAgent.onPause(this);友盟调用方法
    }

    public void openEventBus() {

        this.enableEventBus = true;
        EventBus.getDefault().register(this);
    }
}
