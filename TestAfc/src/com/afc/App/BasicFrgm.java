package com.afc.App;

import android.support.v4.app.Fragment;

import com.squareup.okhttp.OkHttp;

import org.simple.eventbus.EventBus;

/**
 * Created by Administrator on 2016/5/5.
 */
public class BasicFrgm extends Fragment {
    public final String TAG = toString();
    private boolean enableEventBus = false;

    public void openEventBus() {
        this.enableEventBus = true;
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (enableEventBus) {
            EventBus.getDefault().unregister(this);
        }
        OkHttp.sOkHttpClient.cancel(TAG);
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (Host.DEBUG) {
            RefWatcher refWatcher = App.getRefWatcher(getActivity());
            refWatcher.watch(this);
        }*/
    }
}
