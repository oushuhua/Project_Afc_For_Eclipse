package com.afc.product.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.afc.App.BasicFrgm;
import com.afc.App.UrlPool;
import com.afc.BuildConfig;
import com.afc.R;
import com.afc.event.FileEntity;
import com.afc.event.ProductListEntity;
import com.afc.event.productidEntity;
import com.afc.product.activity.ProductDetailsActivity;
import com.afc.product.adapter.ProDataFileAdapter;
import com.alibaba.fastjson.JSON;
import com.idroid.widget.Toaster;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.common.util.ListUtils;

/**
 * Created by Administrator on 2016/5/22.
 */
public class ProductDetailsDataFrgm extends BasicFrgm {

    private Context mContext;
    @Bind(R.id.pro_details_data_listview)
    ListView mProDetailsDataListView;

    private List<FileEntity> fileentity;
    private ProDataFileAdapter mProDataFileadapter;
    private List<FileEntity> ProListentity=new ArrayList<FileEntity>();
    private  int productCount;
    private productidEntity proid;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_details_data_frgm, container, false);
        ButterKnife.bind(this,rootView);
        mContext=getActivity();
        openEventBus();
        proid = ((ProductDetailsActivity) getActivity()).getEntity();
        mProDataFileadapter=new ProDataFileAdapter(mContext,ProListentity);
        mProDetailsDataListView.setAdapter(mProDataFileadapter);
        JSONdata(proid.getProID());
        return rootView;
    }

    private void JSONdata(String productID){
        Params params=new Params();
        params.add("ProductID",productID);
        AuzHttp.get(UrlPool.ProductFile, params, new DataJson_Cb() {
            @Override
            public void onSuccess(String data) {
                productCount = JSON.parseObject(data).getIntValue("count");
                List<FileEntity> ProList=JSON.parseArray(get(data, "list"), FileEntity.class);
                if (ProList!=null){
                    ProListentity.addAll(ProList);
                }
                mProDataFileadapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                Toaster.show(mContext,msg);
            }
        },TAG);
    }
}
