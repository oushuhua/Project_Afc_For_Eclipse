package com.afc.product.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afc.App.BasicFragmentActivity;
import com.afc.R;
import com.afc.Utils.TitleHolder;
import com.afc.event.productidEntity;
import com.afc.product.fragment.ProductDetailsDataFrgm;
import com.afc.product.fragment.ProductDetailsEnterprise;
import com.afc.product.fragment.ProductDetailsFrgm;
import com.bumptech.glide.Glide;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/22.
 */
public class ProductDetailsActivity extends BasicFragmentActivity {

    private Context mContext;
    @Bind(R.id.pro_etails)
    TextView mProEtails;
    @Bind(R.id.pro_etails_data)
    TextView mProEtailsData;
    @Bind(R.id.pro_etails_enterprise)
    TextView getmProEtailsEnterprise;
    @Bind(R.id.pro_etails_Lin)
    LinearLayout mProEtailsLin;
    @Bind(R.id.product_Details_viewpager)
    ViewPager mProductDetailsViewPager;
    @Bind(R.id.cursor)
    View mCursor;
    @Bind(R.id.cursor1)
    View mCursor1;
    @Bind(R.id.cursor2)
    View mCursor2;

    private ArrayList<Fragment> fragmentList;
    private int currIndex=0;
    private String mProductID;
    private productidEntity proid= new productidEntity();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        ButterKnife.bind(this);
        mContext = ProductDetailsActivity.this;
        mProductID= getIntent().getStringExtra("productID");
        proid.setProID(mProductID);
        initTitle("产品详情");
        InitViewPager();
        InitTextView();
    }

    //设置标题
    private void initTitle(String title) {

        TitleHolder holder = new TitleHolder(ButterKnife.findById(this, R.id.top_titlebar));
        holder.defineTitle(title);
        holder.defineLeft(R.mipmap.back_btn, "返回", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /**
     * 初始化ViewPager页面标题栏
     */
    public void InitTextView() {

        mProEtails.setOnClickListener(new txListener(0));
        mProEtailsData.setOnClickListener(new txListener(1));
        getmProEtailsEnterprise.setOnClickListener(new txListener(2));

    }

    public class txListener implements View.OnClickListener {

        private int Index = 0;

        public txListener(int i) {

            Index = i;
        }

        @Override
        public void onClick(View v) {

            mProductDetailsViewPager.setCurrentItem(Index);
        }
    }


    /**
     * 初始化ViewPager
     */
    public void InitViewPager() {

        fragmentList = new ArrayList<Fragment>();

        Fragment PDFragment = new ProductDetailsFrgm();
        Fragment PDDFragment = new ProductDetailsDataFrgm();
        Fragment PDEFragment = new ProductDetailsEnterprise();

        fragmentList.add(PDFragment);
        fragmentList.add(PDDFragment);
        fragmentList.add(PDEFragment);

        mProductDetailsViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        mProductDetailsViewPager.setCurrentItem(0);
        mProductDetailsViewPager.setOnPageChangeListener(new MyOnPageChangeListener());
    }

    /**
     * ViewPager的适配器
     */
    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {

            super(fm);
            this.list = list;

        }

        @Override
        public int getCount() {

            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {

            return list.get(arg0);
        }

    }
    /**
     * ViewPager的滚动事件监听
     */
    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            if (arg0 == 0) {
                mProEtails.setTextColor(Color.parseColor("#1a9cf2"));
                mProEtailsData.setTextColor(Color.parseColor("#333333"));
                getmProEtailsEnterprise.setTextColor(Color.parseColor("#333333"));
                mCursor.setBackgroundResource(R.color.color_main);
                mCursor1.setBackgroundResource(R.color.afc_background);
                mCursor2.setBackgroundResource(R.color.afc_background);
                initTitle("产品详情");

            } else if (arg0 == 1) {
                mProEtails.setTextColor(Color.parseColor("#333333"));
                mProEtailsData.setTextColor(Color.parseColor("#1a9cf2"));
                getmProEtailsEnterprise.setTextColor(Color.parseColor("#333333"));
                mCursor.setBackgroundResource(R.color.afc_background);
                mCursor1.setBackgroundResource(R.color.color_main);
                mCursor2.setBackgroundResource(R.color.afc_background);
                initTitle("产品资料");
            } else if (arg0==2){
                mProEtails.setTextColor(Color.parseColor("#333333"));
                mProEtailsData.setTextColor(Color.parseColor("#333333"));
                getmProEtailsEnterprise.setTextColor(Color.parseColor("#1a9cf2"));
                mCursor.setBackgroundResource(R.color.afc_background);
                mCursor1.setBackgroundResource(R.color.afc_background);
                mCursor2.setBackgroundResource(R.color.color_main);
                initTitle("录影视频");

            }
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }

        @Override
        public void onPageSelected(int arg0) {

            // TODO Auto-generated method stub
            currIndex = arg0;

        }
    }

    public productidEntity getEntity() {
        return proid;
    }
}
