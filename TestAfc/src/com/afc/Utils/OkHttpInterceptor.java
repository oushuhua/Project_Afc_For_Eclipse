package com.afc.Utils;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by Administrator on 2016/5/5.
 */
public class OkHttpInterceptor implements Interceptor {
    private String mCurrentVersion = "1.0.0";

    public OkHttpInterceptor(String version){
        mCurrentVersion = version;
    }


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (originalRequest.header("FromApp") != null) {
            return chain.proceed(originalRequest);
        }


        Request compressedRequest = originalRequest.newBuilder()
                .header("FromApp", "0")
                .header("AppVersion", mCurrentVersion)
                .build();
        return chain.proceed(compressedRequest);

    }

}
