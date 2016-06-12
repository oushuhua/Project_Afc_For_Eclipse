package com.afc.registered.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.afc.App.BasicActivity;
import com.afc.App.UrlPool;
import com.afc.R;
import com.afc.Utils.TitleHolder;
import com.idroid.widget.Toaster;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注册页面
 * Created by Administrator on 2016/5/6.
 */
public class RegisteredActivity extends BasicActivity{

    private Context mContext;
    @Bind(R.id.register_edittext)
    EditText mRegisterEditText;//手机号码输入控件
    @Bind(R.id.according_textView)
    TextView mAccrdingTextView;//手机号码放大显示控件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);
        ButterKnife.bind(this);
        mContext=this;
        initTitle();
        mRegisterEditText.addTextChangedListener(textWatcher);
    }

    //设置标题
    private void initTitle() {

        TitleHolder holder = new TitleHolder(ButterKnife.findById(this, R.id.top_titlebar));
        holder.defineTitle("注册");
        holder.defineLeft(R.mipmap.back_btn, "返回", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    //下一步按钮操作
    @OnClick(R.id.register_next_btn)
    void RegisterNextBtnCK(View v){
      if(TextUtils.isEmpty(mRegisterEditText.getText())){//判断不为空
          Toaster.show(mContext,"电话不能为空");
          return;
      }
        if (mRegisterEditText.getText().length()!=11){
            Toaster.show(mContext,"号码格式不正确");
            return;
        }
        IsMemberRegisted();
    }

    //接口请求，判断是号码否已注册
    public void IsMemberRegisted(){

        Params params = new Params();
        params.add("UserName", mRegisterEditText.getText().toString());
        //OkHttp
        AuzHttp.get(UrlPool.IsMemberRegisted,params, new DataJson_Cb() {

            @Override
            public void onSuccess(String data) {
                try {
                    boolean BoolData = new JSONObject(data).getBoolean("flag");
                    if (BoolData){
                        Toaster.show(mContext,"号码已注册，请登录");
                    }else{
                        Intent intent = new Intent();
                        intent.putExtra("phone", mRegisterEditText.getText().toString());
                        intent.putExtra("incomingType", 1);
                        intent.setClass(RegisteredActivity.this, SetUpPasswordActivity.class);
                        startActivity(intent);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                Toaster.show(mContext,msg);

            }
        },TAG);
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
            if (mRegisterEditText.getText().toString().length()>0){
                mAccrdingTextView.setVisibility(View.VISIBLE);
            }else{
                mAccrdingTextView.setVisibility(View.GONE);
            }
            mAccrdingTextView.setText(mRegisterEditText.getText());
        }
    };
}
