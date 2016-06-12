package com.afc.product.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.afc.App.BasicFrgm;
import com.afc.App.UrlPool;
import com.afc.BuildConfig;
import com.afc.R;
import com.afc.event.ProductListEntity;
import com.afc.event.productidEntity;
import com.afc.product.activity.InvestmentApplyActivity;
import com.afc.product.activity.ProductDetailsActivity;
import com.alibaba.fastjson.JSON;
import com.idroid.widget.Toaster;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;

import org.simple.eventbus.Subscriber;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *产品详情
 * Created by Administrator on 2016/5/22.
 */
public class ProductDetailsFrgm extends BasicFrgm {

    private Context mContext;

    @Bind(R.id.product_details_title)
    TextView mProDateTitle;//标题
    @Bind(R.id.pro_details_earnings)
    TextView mProDateEarnings;//收益
    @Bind(R.id.pro_details_raise_monery)
    TextView mProDetaRaisemonery;//招募
    @Bind(R.id.pro_details_raise_monery_all)
    TextView mroDateRaiseMoneryAll;//总招募
    @Bind(R.id.pro_details_qitou)
    TextView mProDetaQitou;//起投
    @Bind(R.id.pro_details_times)
    TextView mProdetailsTimes;//时间
    @Bind(R.id.product_details_webview)
    WebView mProDetailsWebview;
    @Bind(R.id.pro_details_review_content)
    TextView mProDetailReviewContent;//点评
    @Bind(R.id.pro_details_button)
    Button mProDetailsButton;//购买
    private ProductListEntity mProEntity;
    private productidEntity proid;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_details_grgm, container, false);
        ButterKnife.bind(this,rootView);
        mContext=getActivity();
        proid = ((ProductDetailsActivity) getActivity()).getEntity();
        JsonData(proid.getProID());
        return rootView;
    }


    private void JsonData(String proID){
        Params params=new Params();
        params.add("ID",proID);
        AuzHttp.get(UrlPool.ProductDetail, params, new DataJson_Cb() {
            @Override
            public void onSuccess(String data) {

                mProEntity= JSON.parseObject(data,ProductListEntity.class);
                Data();

            }

            @Override
            public void onFailure(int errorCode, String msg) {

            }
        },TAG);
    }

    private void Data(){

        mProDateTitle.setText(mProEntity.getTitle());
        mProDateEarnings.setText(mProEntity.getFinalProfit());
        mProDetaRaisemonery.setText(mProEntity.getCurrentScale());
        mroDateRaiseMoneryAll.setText(mProEntity.getNetworth());
        mProDetaQitou.setText(mProEntity.getBaseInvest());
        mProdetailsTimes.setText(mProEntity.getActualTerm());
        mProDetailReviewContent.setText(mProEntity.getPmComment());
    }


    @OnClick(R.id.pro_details_button)
    void ButtonCK(View v){
        Intent intent=new Intent(mContext, InvestmentApplyActivity.class);
        startActivity(intent);
    }

}
