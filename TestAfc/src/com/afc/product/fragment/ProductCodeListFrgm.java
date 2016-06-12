package com.afc.product.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.afc.App.BasicFrgm;
import com.afc.App.UrlPool;
import com.afc.R;
import com.afc.Utils.Utility;
import com.afc.View.pulltoswiplistview.PullToRefreshSwipeMenuListView;
import com.afc.View.pulltoswiplistview.RefreshTime;
import com.afc.event.ProductListEntity;
import com.afc.product.activity.ProductDetailsActivity;
import com.afc.product.adapter.ProductCodeAdapter;
import com.afc.registered.activity.SetUpPasswordActivity;
import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/15.
 */
public class ProductCodeListFrgm extends BasicFrgm implements PullToRefreshSwipeMenuListView.IXListViewListener {



    private PullToRefreshSwipeMenuListView mListView;
    private Context mContext;
    private int productType;
    private ProductCodeAdapter mProListAdapter;
    private List<ProductListEntity> mSmaintenancelist = new ArrayList<ProductListEntity>();

    private int mPageIndex=1;
    private int productCount;//接口请求回来的总条数

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_swipmenu_listview,container, false);
        mContext = getActivity();
        ButterKnife.bind(mContext,root);
        productType = getArguments().getInt("productType");
        mListView = (PullToRefreshSwipeMenuListView) root.findViewById(R.id.swipmenu_listview);
        mListView.setDividerHeight(0);
        mProListAdapter = new ProductCodeAdapter(mContext,mSmaintenancelist);
        mListView.setAdapter(mProListAdapter);
        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(false);
        mListView.setHeaderDividersEnabled(false);
        mListView.setFooterDividersEnabled(false);
        mListView.setXListViewListener(this);
        ProDataJson(productType);
        setLienest();
        return root;
    }

    private void ProDataJson(int Process){
        Params params=new Params();
        params.add("Process",Process);
        params.add("PageSize",10);
        params.add("PageIndex",mPageIndex);
        AuzHttp.get(UrlPool.QueryProductList, params, new DataJson_Cb() {
            @Override
            public void onSuccess(String data) {
                productCount = JSON.parseObject(data).getIntValue("Count");
                List<ProductListEntity> ProList=JSON.parseArray(get(data, "list"), ProductListEntity.class);
                if (mPageIndex == 1) {
                    mSmaintenancelist.clear();
                }
                if (ProList!=null){ //有数据
                    mSmaintenancelist.addAll(ProList);
                }
                mProListAdapter.notifyDataSetChanged();
                SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault());
                RefreshTime.setRefreshTime(mContext, df.format(new Date()));
                mListView.setRefreshTime(RefreshTime.getRefreshTime(mContext));
                mListView.onLoadComplete();
            }

            @Override
            public void onFailure(int errorCode, String msg) {

            }
        },TAG);
    }

    private  void setLienest(){
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("productID",mSmaintenancelist.get(position-1).getPid());
                intent.setClass(mContext, ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        ProDataJson(productType);
    }

    @Override
    public void onLoadMore() {
        mPageIndex++;
        ProDataJson(productType);
    }

    }
