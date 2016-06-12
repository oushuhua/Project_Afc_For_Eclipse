package com.afc.product.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afc.App.BasicFrgm;
import com.afc.App.UrlPool;
import com.afc.R;
import com.afc.Utils.ViewFlowUitil.ADImageAdapter;
import com.afc.Utils.ViewFlowUitil.CircleFlowIndicator;
import com.afc.Utils.ViewFlowUitil.ViewFlow;
import com.afc.event.ADEntity;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.OkHttp;
import com.squareup.okhttp.Params;
import com.squareup.okhttp.RespondCallBack;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/15.
 */
public class ShoppingAdFrgm extends BasicFrgm {

    private Context mContext;

    @Bind(R.id.viewflow)
    ViewFlow viewFlow;
    @Bind(R.id.indicatorLayout)
    LinearLayout indicatorLayout;

    private boolean hasRequest = false;
    private List<ADEntity.Detail> adList = new ArrayList<ADEntity.Detail>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.product_add_advertising, container, false);
        mContext = getActivity();
        ButterKnife.bind(this, root);
        mContext.registerReceiver(receiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        openEventBus();
        return root;
    }

    protected BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = connectivityManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()) {
                    if (adList.size() == 0){
                        //reqDatas();
                    }
                }
            }
        }
    };

    private void reqDatas() {

        if (hasRequest)
            return;
        hasRequest = true;
        Params params = new Params();
        params.add("fromApp", "1");

        JSONArray array = new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("pageId", 3);
        obj.put("localId", 1);
        obj.put("categoryId", 1);
        array.add(obj);

        params.add("list", array);
        OkHttp.post(UrlPool.QueryCarouselAds, params, new RespondCallBack<List<ADEntity>>() {

            @Override
            protected List<ADEntity> convert(String json) {

                String list = JSON.parseObject(json).getString("list");
                return JSON.parseArray(list, ADEntity.class);

            }

            @Override
            public void onSuccess(List<ADEntity> data) {

                hasRequest = false;
                if (getActivity() == null || getActivity().isFinishing())
                    return;

                adList.clear();
                for (int i = 0; i < data.size(); i++) {
                    ADEntity temp = data.get(i);
                    if (temp.getLocalID() == 1) {
                        adList.addAll(temp.getDetail());
                    }
                }

                if (adList.size() > 0) {
                    initAD();
                }

            }

            @Override
            public void onFailure(int errorCode, String msg) {

                hasRequest = false;

            }
        }, TAG);

    }

    private void initAD() {

        // 广告 图片轮播 Begin
        // adSize=adList.size();
        viewFlow.adList = adList;
        viewFlow.setAdapter(new ADImageAdapter(adList, viewFlow));
        viewFlow.setmSideBuffer(adList.size());
        LinearLayout includeLayout = (LinearLayout) View.inflate(mContext, R.layout.include_circle_flow_indicator, null);
        indicatorLayout.removeAllViews();
        indicatorLayout.addView(includeLayout);

        CircleFlowIndicator indic = (CircleFlowIndicator) includeLayout.findViewById(R.id.viewflowindic);
        viewFlow.setFlowIndicator(indic);
        viewFlow.setTimeSpan(3000);
        viewFlow.setSelection(1); // 设置初始位置
        if (adList.size() > 1) {
            viewFlow.startAutoFlowTimer();
        }
        viewFlow.startAutoFlowTimer(); // 启动自动播放

    }
}
