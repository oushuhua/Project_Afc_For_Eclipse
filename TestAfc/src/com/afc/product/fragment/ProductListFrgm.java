package com.afc.product.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.afc.App.BasicFrgm;
import com.afc.R;
import com.idroid.widget.Toaster;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/15.
 */
public class ProductListFrgm extends BasicFrgm {

    private Context mContext;
    @Bind(R.id.vp_serviceorder_list)
    ViewPager mSerListPager;
    @Bind(R.id.rp_order_type)
    RadioGroup mRadios;

    /**
     * 订单状态（0:待启动，1:可购买2：存续中3：已完成）  //0x表示为服务退款订单,单独接口请求
     */
    private static final int[] PRODUCT_CODE = {0,1,2,3};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frgm_product_list, container, false);
        ButterKnife.bind(this, root);
        mContext = getActivity();
        openEventBus();
        initViews();
        FrgmView();
        return root;
    }

    private void initViews() {
        mRadios.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radio01:
                        mSerListPager.setCurrentItem(0);
                        break;
                    case R.id.radio02:
                        mSerListPager.setCurrentItem(1);
                        break;
                    case R.id.radio03:
                        mSerListPager.setCurrentItem(2);
                        break;
                    case R.id.radio04:
                        mSerListPager.setCurrentItem(3);
                        break;
                    default:
                        break;
                }
            }
        });
    }



    private void FrgmView(){

        List<Fragment> fragment = new ArrayList<Fragment>();
        for (int i = 0; i < PRODUCT_CODE.length; i++) {
            ProductCodeListFrgm mPageFragment = new ProductCodeListFrgm();
            Bundle bundle = new Bundle();
            bundle.putInt("productType", PRODUCT_CODE[i]);
            mPageFragment.setArguments(bundle);
            fragment.add(mPageFragment);
        }
        MainProductListFrgmAdapter maintenanceAdapter = new MainProductListFrgmAdapter(getChildFragmentManager(), fragment);//getSupportFragmentManager()
        mSerListPager.setAdapter(maintenanceAdapter);
        mSerListPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                // TODO Auto-generated method stub

                Toaster.show(mContext,"position"+position);
                /*if (mRadios == null)
                    return;
                View tabbutton = mRadios.getChildAt(position);
                mRadios.check(mRadios.getChildAt(position).getId());
                if (tabbutton.getRight() > widthPixel) {
                    mMaintenanceTabScorlview.scrollTo((int) tabbutton.getX(), (int) tabbutton.getY());
                } else {
                    mMaintenanceTabScorlview.scrollTo(0, 0);
                }*/
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

                // TODO Auto-generated method stub

            }
        });
    }

    class  MainProductListFrgmAdapter extends FragmentPagerAdapter{
        private List<Fragment> fragmentList;

        public MainProductListFrgmAdapter(FragmentManager fm, List<Fragment> fragmentList) {

            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {

            // TODO Auto-generated method stub
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {

            // TODO Auto-generated method stub
            return fragmentList.size();
        }
    }
}
