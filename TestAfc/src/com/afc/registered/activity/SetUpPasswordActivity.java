package com.afc.registered.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afc.App.BasicActivity;
import com.afc.App.EncryMD5;
import com.afc.App.LbsMgr;
import com.afc.App.UrlPool;
import com.afc.Utils.TitleHolder;
import com.afc.event.RegisterEntity;
import com.idroid.widget.Toaster;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;
import com.afc.R;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置新密码.填写验证
 * Created by Administrator on 2016/5/11.
 */
public class SetUpPasswordActivity  extends BasicActivity{

    private Context mContext;
    @Bind(R.id.password_text2)
    TextView mPasswordText;//显示号码
    @Bind(R.id.register_edittext)
    EditText mRegisterEditText;//验证输入框
    @Bind(R.id.set_up_countdown)
    TextView mSetUpCountdown;//倒计时显示
    @Bind(R.id.register_edittext1)
    EditText mReisterPassword;//设置密码
    @Bind(R.id.change_btn)
    Button mChangeBtn;

    private String mPhone;//手机号码
    private CountDownTimer mTimer;//倒计时
    private String incomingType;//1为 填写验证 2为 设置新密码
    private RegisterEntity mRegisEntity;
    private String mMD5Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_password);
        ButterKnife.bind(this);
        mContext=this;
        Intent intent=getIntent();
        mPhone=intent.getStringExtra("phone");
        incomingType=intent.getStringExtra("incomingType");
        mPasswordText.setText(mPhone.substring(0, 3) + "***" + mPhone.substring(7, 11));
        initTitle();
        Verification();
    }
    //设置标题
    private void initTitle() {

        TitleHolder holder = new TitleHolder(ButterKnife.findById(this, R.id.top_titlebar));
        if ("1".equals(incomingType)){
            holder.defineTitle("填写验证");
            mChangeBtn.setText("下一步");

        }else{
            holder.defineTitle("设置新密码");
            mChangeBtn.setText("完成");
        }
        holder.defineLeft(R.mipmap.back_btn, "返回", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    @OnClick(R.id.change_btn)
    void ChangeBthCK(View v){
        if(TextUtils.isEmpty(mRegisterEditText.getText())){//判断不为空
            Toaster.show(mContext,"验证码不能为空");
            return;
        }
        if (TextUtils.isEmpty(mReisterPassword.getText())){
            Toaster.show(mContext,"密码不能为空");
            return;
        }
        try{
            mMD5Password=EncryMD5.getMD5(mReisterPassword.getText().toString());
            mRegisEntity.setPassWord(mMD5Password);
        }catch (Exception e){
            e.printStackTrace();
        }
        if ("1".equals(incomingType)){
            mRegisEntity.setUserName(mPhone);
            mRegisEntity.setVerifyCode(mRegisterEditText.getText().toString());
            mRegisEntity.setLatitude(Float.valueOf(LbsMgr.Location.getLatItude(mContext)));
            mRegisEntity.setLongitude(Float.valueOf(LbsMgr.Location.getLongItude(mContext)));
            Intent intent=new Intent();
            intent.setClass(SetUpPasswordActivity.this, InformationActivity.class);
            startActivity(intent);
        }else{
            Modify();
        }
    }




    //修改密码接口请求
    public void Modify(){
        mRegisterEditText.getText().toString();
        Params params=new Params();
        params.add("UserName",mPhone);
        params.add("Password",mMD5Password);
        params.add("Code",mReisterPassword.getText().toString());
        AuzHttp.post(UrlPool.ModifyPassword, params, new DataJson_Cb() {
            @Override
            public void onSuccess(String data) {

                try{
                    boolean BoolData = new JSONObject(data).getBoolean("flag");
                    if (BoolData){
                        Toaster.show(mContext,"修改成功");
                    }else{
                        Toaster.show(mContext,"修改失败，请重新尝试");
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

    //发送验证码
    public void Verification(){
        Params params=new Params();
        params.add("UserName",mPhone);
        AuzHttp.get(UrlPool.GetRegistCode,params,new DataJson_Cb(){

            @Override
            public void onSuccess(String data) {
                setTimerCount(59);
            }

            @Override
            public void onFailure(int errorCode, String msg) {

                Toaster.show(mContext,msg);
            }
        },TAG);

    }




    //倒计时方法
    private void setTimerCount(final int AllTime) {

        mTimer = new CountDownTimer(AllTime*1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mSetUpCountdown.setText("重发("+millisUntilFinished/1000+")");

            }

            @Override
            public void onFinish() {
                mSetUpCountdown.setText("重发");
                mTimer.cancel();

            }
        };
        mTimer.start();
    }
}
