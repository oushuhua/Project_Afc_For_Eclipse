package com.afc.registered.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afc.App.BasicActivity;
import com.afc.R;
import com.afc.Utils.TitleHolder;
import com.idroid.widget.Toaster;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 找回密码
 * Created by Administrator on 2016/5/11.
 */
public class BackPasswordActivity  extends BasicActivity{

    private Context mContext;
    @Bind(R.id.back_edittext)
    EditText mBackEditText;//号码输入框
    @Bind(R.id.accrding_TextView)
    TextView mAccrdingText;
    @Bind(R.id.back_btn)
    Button mBackBtn;//下一步
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_parssword);
        ButterKnife.bind(this);
        initTitle();
        mBackEditText.addTextChangedListener(textWatcher);
    }

    //设置标题
    private void initTitle() {

        TitleHolder holder = new TitleHolder(ButterKnife.findById(this, R.id.top_titlebar));
        holder.defineTitle("找回密码");
        holder.defineLeft(R.mipmap.back_btn, "返回", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
    @OnClick(R.id.back_btn)
    void BackPasswordBtnCK(View v){
        if(TextUtils.isEmpty(mBackEditText.getText())){//判断不为空
            Toaster.show(mContext, "电话不能为空");
            return;
        }
        if (mBackEditText.getText().length()!=11){
            Toaster.show(mContext,"号码格式不正确");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("phone", mBackEditText.getText().toString());
        intent.putExtra("incomingType", 2);
        intent.setClass(BackPasswordActivity.this, SetUpPasswordActivity.class);
        startActivity(intent);
    }
    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //int number=Integer.valueOf(mRegisterEditText.getText().toString());
            if (mBackEditText.getText().toString().length()>0){
                mAccrdingText.setVisibility(View.VISIBLE);
            }else{
                mAccrdingText.setVisibility(View.GONE);
            }
            mAccrdingText.setText(mBackEditText.getText());
        }
    };
}
