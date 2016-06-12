package com.afc.Utils.ViewFlowUitil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.afc.event.ADEntity;


//import com.kuparts.activity.shopping.ShoppingProductActivity;

/**
 * 处理广告图片跳转
 * 
 * @author yiyi
 * @date 2015年12月9日
 */
public class AdHandler {

	public static void handleIt(ADEntity.Detail cmd, Context context) {
		String classs = cmd.getType();
		String page = cmd.getPage();
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		/*if (classs.equals("活动")) {

			String url = cmd.getTargetUrl();
			String title = cmd.getTitle();
			intent.setClass(context, KuWebView.class);
			bundle.putString("title", title);
			bundle.putString("url", url);
			intent.putExtras(bundle);
			context.startActivity(intent);

		} else if ((classs.equals("配件") || classs.equals("用品")) && page.equals("列表")) {

			String url = cmd.getTouchId();
			String title = cmd.getTitle();
			intent.setClass(context, ShoppingListActivity.class);
			bundle.putString("id", url);
			bundle.putString("className", title);
			intent.putExtras(bundle);
			context.startActivity(intent);
		} else if ((classs.equals("配件") || classs.equals("用品")) && page.equals("详情")) {

			String url = cmd.getTouchId();
			String title = cmd.getTitle();
			intent.setClass(context, ShoppingProductActivity.class);
			bundle.putString("shopdetailsid", url);
			bundle.putString("proname", title);
			intent.putExtras(bundle);
			context.startActivity(intent);
		} else if (classs.equals("服务") && page.equals("详情")) {

			String url = cmd.getTouchId();
			String title = cmd.getTitle();
			bundle.putString("id", url);
			intent.putExtras(bundle);

		} else if (classs.equals("服务") && page.equals("列表")) {

			String url = cmd.getTouchId();
			String title = cmd.getTitle();
			bundle.putString("id", url);
			intent.putExtras(bundle);

		} else if (classs.equals("服务商") && page.equals("列表")) {

			String url = cmd.getTouchId();
			String title = cmd.getTitle();
			CityDeta city = new CityDeta(1, url, title);
			bundle.putSerializable("cityDeta", city);
			bundle.putInt("position", city.fildId);
			intent.putExtras(bundle);

		} else if (classs.equals("服务商") && page.equals("详情")) {

			String url = cmd.getTouchId();
			String title = cmd.getTitle();
			bundle.putString("id", url);
			bundle.putString("scopes", title);
			intent.putExtras(bundle);
		} else if (classs.equals("店铺")) {

			String url = cmd.getTouchId();
			bundle.putString("shopid", url);
			intent.putExtras(bundle);
		}*/
	}
}
