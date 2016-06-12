package com.afc.product.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afc.App.BasicFrgm;
import com.afc.App.PageFragment;
import com.afc.R;
import com.afc.Utils.TitleHolder;
import com.afc.event.ETag;
import com.afc.product.fragment.ProductListFrgm;
import com.afc.product.fragment.ShoppingAdFrgm;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * 产品
 * Created by Administrator on 2016/5/15.
 */
public class ProductActivity extends PageFragment {

    private Context mContext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.product_main_activity, container, false);
        ButterKnife.bind(this, root);
        mContext = getActivity();
        TitleHolder holder = new TitleHolder(root);
        holder.defineTitle("产品");
        generateChilds(root);
        return root;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            EventBus.getDefault().post(Boolean.valueOf(true), ETag.ShoppingResume);
        }
    }

    /**
     * 代码添加frgm，xml直接添加frgm时会在应用崩溃重启时出现异常情况
     *
     * @param root
     */
    private void generateChilds(View root) {
        final String frgmTag = "isFrgmAdded";
        if (getChildFragmentManager().findFragmentByTag(frgmTag) != null)
            return;

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();

        ft.add(R.id.content_layout, new ShoppingAdFrgm(), frgmTag);
        ft.add(R.id.content_layout, new ProductListFrgm());
       /* ft.add(R.id.content_layout, new FittingsGroupFrgm());
        ft.add(R.id.content_layout, new ShoppingRecomFrgm());
*/
        ft.commitAllowingStateLoss();
    }


}
