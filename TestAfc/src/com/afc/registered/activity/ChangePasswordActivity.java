package com.afc.registered.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afc.App.BasicActivity;
import com.afc.R;
import com.afc.Utils.TitleHolder;
import com.squareup.okhttp.Params;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 修改密码
 * Created by Administrator on 2016/5/11.
 */
public class ChangePasswordActivity extends BasicActivity {

    private Context mConext;
    @Bind(R.id.original_password)
    EditText mOriginalPass;
    @Bind(R.id.original_password_next)
    EditText mOriginalPassNext;
    @Bind(R.id.true_password)
    EditText mTruePass;
    @Bind(R.id.change_btn)
    Button mChangeBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        initTitle();
        mConext=this;
    }

    //设置标题
    private void initTitle() {

        TitleHolder holder = new TitleHolder(ButterKnife.findById(this, R.id.top_titlebar));
        holder.defineTitle("修改密码");
        holder.defineLeft(R.mipmap.back_btn, "返回", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    //确认修改
    @OnClick(R.id.change_btn)
    void  ButtonCK(View v){

    }

    public void ButtonChang(){
        Params params=new Params();
        params.add("","");
    }

}
