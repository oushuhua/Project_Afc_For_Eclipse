package com.afc.registered.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afc.App.BasicActivity;
import com.afc.App.EncryMD5;
import com.afc.App.UrlPool;
import com.afc.R;
import com.idroid.widget.Toaster;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 * Created by Administrator on 2016/5/11.
 */
public class LoginActivity extends BasicActivity {

    private Context mContext;
    @Bind(R.id.login_edittext)
    EditText mLoginEditText;
    @Bind(R.id.login_password_edittext)
    EditText mLoginPasswordEdtText;
    @Bind(R.id.login_Button)
    Button mLoginButton;
    @Bind(R.id.register_next)
    Button mReginsterNext;

    private String mMD5Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mContext=this;
    }

    @OnClick(R.id.login_Button)
    void LoginButtonCK(View v){

        if (TextUtils.isEmpty(mLoginEditText.getText())){
            Toaster.show(mContext,"用户不能为空");
            return;
        }
        if(TextUtils.isEmpty(mLoginPasswordEdtText.getText())){
            Toaster.show(mContext,"密码不能为空");
            return;
        }
        Login();
    }

    public void Login(){
        Params params=new Params();
        params.add("UserName",mLoginEditText.getText().toString());
        try{
            mMD5Password= EncryMD5.getMD5(mLoginPasswordEdtText.getText().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        params.add("PWD",mMD5Password);
        AuzHttp.post(UrlPool.LOGIN, params, new DataJson_Cb() {
            @Override
            public void onSuccess(String data) {
                try{
                    boolean BoolData = new JSONObject(data).getBoolean("flag");
                    if (BoolData){
                        //登录后的跳转

                    }else{
                        Toaster.show(mContext,"登录失败");
                    }
                    return;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                Toaster.show(mContext,msg);

            }
        },TAG);
    }

    @OnClick(R.id.register_next)
    void RegisterNextCK(View v){
        Intent intent=new Intent(mContext,RegisteredActivity.class);

    }




}
