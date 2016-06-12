package com.afc.module.mainactivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afc.App.PageFragment;
import com.afc.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/11.
 */
public class HomepageFrgm extends PageFragment {

    private Context mContext;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frgm_homepage,null);
        mContext = getActivity();
        ButterKnife.bind(this, root);
        openEventBus();
        return root;
    }
}
