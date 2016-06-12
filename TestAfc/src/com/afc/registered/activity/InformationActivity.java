package com.afc.registered.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afc.App.BasicActivity;
import com.afc.App.EncryMD5;
import com.afc.App.UrlPool;
import com.afc.R;
import com.afc.Utils.AccountMgr;
import com.afc.Utils.TitleHolder;
import com.afc.Utils.myphotos.BusinessModule;
import com.afc.Utils.myphotos.ImgUpload;
import com.afc.Utils.myphotos.PhotoSelectUtil;
import com.afc.Utils.myphotos.UpLoadListener;
import com.afc.event.RegisterEntity;
import com.afc.event.ResultLogin;
import com.afc.event.UpLoadImgPojo;
import com.afc.event.UserInfo;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.idroid.widget.Toaster;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;

import org.simple.eventbus.Subscriber;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.common.util.ArrayUtils;

/**
 * 填写基本信息
 * Created by Administrator on 2016/5/11.
 */
public class InformationActivity extends BasicActivity {

    private Context mContext;
    @Bind(R.id.info_add_images)
    TextView mInfoAddImages;//添加头像
    @Bind(R.id.name_EditText)
    EditText mNameEditText;//个人昵称
    @Bind(R.id.phone_EditText)
    EditText mPhoneEditText;//推荐人电话
    @Bind(R.id.loginBtn)
    Button mLoginBtn;//提交
    @Bind(R.id.info_logo_images)
    ImageView mInfoLogoImg;

    private RegisterEntity register;
    private PhotoSelectUtil photoSelectUtil;//图片现在的类
    private List<String> mListStr = new ArrayList<String>();//获取图片后用于保存图片的信息
    List<String> patlist=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforation);
        ButterKnife.bind(this);
        mContext=this;
        initTitle();
        openEventBus();
    }


    //设置标题
    private void initTitle() {

        TitleHolder holder = new TitleHolder(ButterKnife.findById(this, R.id.top_titlebar));
        holder.defineTitle("填写基本信息");
        holder.defineLeft(R.mipmap.back_btn, "返回", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();

            }
        });
        holder.defineRight("跳过", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterMember();

            }
        });
    }

    public void RegisterMember(){
        Params params=new Params();
        params.add("name","");
        params.add("UserName",register.getUserName());
        params.add("PassWord",register.getPassWord());
        params.add("NickName",mNameEditText.getText().toString());
        params.add("Longitude", register.getLongitude());
        params.add("Latitude", register.getLatitude());
        params.add("HeadPicture", register.getHeadPicture());
        params.add("RefPerson", mPhoneEditText.getText().toString());
        params.add("VerifyCode", register.getVerifyCode());
        AuzHttp.post(UrlPool.RegisterMember, params, new DataJson_Cb() {
            @Override
            public void onSuccess(String data) {
                ResultLogin pojo = JSON.parseObject(data, ResultLogin.class);

                String loginID=pojo.getPid();
                AccountMgr.putUser(getApplicationContext(), new UserInfo(null, "0.00",
                        loginID, null, null, null));//保存数据
            }

            @Override
            public void onFailure(int errorCode, String msg) {
                Toaster.show(mContext,msg);

            }
        }, TAG);
    }

    @OnClick(R.id.loginBtn)
    void LoginBtnCK(View v){
        RegisterMember();
    }



    @OnClick(R.id.info_add_images)
    void AddImagesCK(View v){
        photoSelectUtil = new PhotoSelectUtil(this);
        photoSelectUtil.getPhoto(1);
    }

    @Subscriber(tag = "OnPhotoCheckOK")
    private void OnPhotoCheckOK(List<String> photoPathList) {
        //选择图片后回调的方法，图片上传的操作在这里写方法
        AddIamgeData(photoPathList);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x201) {
            if (null == photoSelectUtil.getPhotoFile() || !photoSelectUtil.getPhotoFile().exists()) {
                return;
            }
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Toaster.show(this, "外部储存不可用！");
                return;
            }

            //获取图片的完整路径
            String mZhizhaoStr = photoSelectUtil.getPhotoFile().getAbsolutePath();
            patlist.add(mZhizhaoStr);
            AddIamgeData(patlist);
        }
    }

    //图片上传
    public void AddIamgeData(List<String> photoPathList) {

        File file=new File(photoPathList.get(0).toString());
        String FileMD5= EncryMD5.getFileMD5(file);
        new ImgUpload(mContext, photoPathList, new UpLoadListener() {

            @Override
            public void onComplete(boolean isSuccess, String response) {

                StringBuffer photoBackNames = new StringBuffer();
                if (isSuccess) {
                    String str = JSON.parseObject(response).getString("resultList");
                    List<UpLoadImgPojo> list = JSON.parseArray(str, UpLoadImgPojo.class);
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).getIsSuccess()) {
                            mListStr.add(list.get(i).getName());
                            register.setHeadPicture(mListStr.get(0));
                        }else {
                            Toaster.show(getApplicationContext(), "图片上传失败");
                            return;
                        }
                    }
                    Glide.with(mContext).load(mListStr.get(0)).error(R.mipmap.foin_image_log)
                            .placeholder(R.mipmap.foin_image_log).into(mInfoLogoImg);
                    mInfoAddImages.setVisibility(View.GONE);
                    return;
                } else {
                    Toaster.show(getApplicationContext(), "图片上传失败");
                }

            }
        }, TAG,FileMD5,0,7,file.getName()).setBusinessModule(BusinessModule.WeiXiuBao).doUpload();

    }


}
