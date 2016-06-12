package com.afc.product.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.afc.App.BasicActivity;
import com.afc.R;
import com.afc.Utils.TitleHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/5/22.
 */
public class InvestmentApplyActivity  extends BasicActivity{

    private Context mContext;
    @Bind(R.id.invsetment_apply_edittext)
    EditText mInvsetmentApplyEdittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_apply);
        ButterKnife.bind(this);
        mContext = InvestmentApplyActivity.this;
        initTitle();
    }

    //设置标题
    private void initTitle() {

        TitleHolder holder = new TitleHolder(ButterKnife.findById(this, R.id.top_titlebar));
        holder.defineTitle("投资申请");
        holder.defineLeft(R.mipmap.back_btn, "返回", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.invsetment_apply_button)
    void InvsetmentApplyCK(View v){

    }
}
