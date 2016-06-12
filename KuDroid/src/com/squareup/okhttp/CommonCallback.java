package com.squareup.okhttp;

import java.io.IOException;

import com.idroid.utils.Pools;
import com.lidroid.util.Logger;

/**
 * 
 * @author yiyi
 * @date 2015年11月6日
 */
@SuppressWarnings("rawtypes")
public class CommonCallback implements Callback {
	private RespondCallBack mCB;

	private CommonCallback(RespondCallBack respondCallBack) {
		mCB = respondCallBack;
	}

	public void setRespondCallBack(RespondCallBack respondCallBack) {
		mCB = respondCallBack;
	}

	@Override
	public void onFailure(Request arg0, IOException arg1) {
		if (!"Canceled".equalsIgnoreCase(arg1.getMessage()) && !"Socket closed".equalsIgnoreCase(arg1.getMessage()))
			OkHttp.uiHandler.post(new Runnable() {

				@Override
				public void run() {
					RespondCallBack callback = mCB;
					recycle();
					if (callback != null)
						callback.onFailure(-11, "网络故障，请检查网络!");

				}
			});
		else
			recycle();

	}

	@Override
	public void onResponse(final Response arg0) throws IOException {
		final String body = arg0.body().string();
		OkHttp.uiHandler.post(new Runnable() {

			@Override
			public void run() {
				RespondCallBack callback = mCB;
				recycle();
				if (callback != null)
					callback.onResponse(body);
				else
					Logger.e("找不到回调了" + body);

			}
		});

	}

	private static final Pools.SimplePool<CommonCallback> sPool = new Pools.SimplePool<CommonCallback>(10);

	public static CommonCallback obtain(RespondCallBack responseCallback) {
		CommonCallback instance = sPool.acquire();
		if (instance != null) {
			instance.setRespondCallBack(responseCallback);
			return instance;
		} else {
			return new CommonCallback(responseCallback);
		}
	}

	public void recycle() {
		// Clear state if needed.
		mCB = null;
		sPool.release(this);
	}

}
