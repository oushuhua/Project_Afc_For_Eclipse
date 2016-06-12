package com.afc.App;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;

import com.squareup.okhttp.OkHttp;

import org.simple.eventbus.EventBus;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BasicFragmentActivity extends FragmentActivity {
    public final String TAG = toString();
    private boolean enableEventBus = false;
    protected static final String EXITAPP = "com.kuparts.exit_app";
    protected BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(EXITAPP)) {
                BasicFragmentActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IntentFilter intent = new IntentFilter(EXITAPP);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(receiver, intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(receiver);
        if (enableEventBus) {
            EventBus.getDefault().unregister(this);
        }
        OkHttp.sOkHttpClient.cancel(TAG);
        /*if (BuildConfig.DEBUG) {
            RefWatcher refWatcher = App.getRefWatcher(this);
            refWatcher.watch(this);
        }*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MobclickAgent.onPause(this);
    }

    public void openEventBus() {
        this.enableEventBus = true;
        EventBus.getDefault().register(this);
    }
}
