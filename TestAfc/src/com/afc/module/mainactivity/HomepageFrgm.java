package com.afc.module.mainactivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;

import com.afc.R;
import com.afc.App.PageFragment;
import com.afc.App.UrlPool;
import com.afc.View.pulltoswiplistview.PullToRefreshSwipeMenuListView;
import com.afc.View.pulltoswiplistview.RefreshTime;
import com.afc.db.entity.project.ProjectListEntity;
import com.afc.event.ProductListEntity;
import com.afc.product.adapter.ProjectAdapter;
import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.AuzHttp;
import com.squareup.okhttp.DataJson_Cb;
import com.squareup.okhttp.Params;

/**
 * Created by Administrator on 2016/5/11.
 */
public class HomepageFrgm extends PageFragment implements
		PullToRefreshSwipeMenuListView.IXListViewListener {

	@Bind(R.id.list)
	private PullToRefreshSwipeMenuListView mListView;
	private Context mContext;
	private int mPageIndex;
	private int productCount;// 接口请求回来的总条数
	private List<ProjectListEntity> mSmaintenancelist = new ArrayList<ProjectListEntity>();

	private ProjectAdapter mProListAdapter;
	private int projectStatus;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.frgm_homepage, null);
		mContext = getActivity();
		ButterKnife.bind(this, root);
		projectStatus = getArguments() != null ? getArguments().getInt(
				"productType") : 0;
		openEventBus();
		mProListAdapter = new ProjectAdapter(mSmaintenancelist, mContext);
		mListView.setAdapter(mProListAdapter);
		requestData(projectStatus);
		return root;
	}

	private void requestData(int Status) {
		Params params = new Params();
		params.add("MemberId", 0);
		params.add("Category", 0);
		params.add("Status", Status);
		params.add("PageSize", 10);
		params.add("PageIndex", mPageIndex);
		AuzHttp.get(UrlPool.QueryProjectList, params, new DataJson_Cb() {
			@Override
			public void onSuccess(String data) {
				productCount = JSON.parseObject(data).getIntValue("Count");
				List<ProjectListEntity> ProList = JSON.parseArray(
						get(data, "list"), ProjectListEntity.class);
				if (mPageIndex == 1) {
					mSmaintenancelist.clear();
				}
				if (ProList != null) {
					mSmaintenancelist.addAll(ProList);
				}
				Log.v("tt", "onSuccess data: " + data + " ProList size: "
						+ ProList.size());
				mProListAdapter.notifyDataSetChanged();
				SimpleDateFormat df = new SimpleDateFormat("MM-dd HH:mm",
						Locale.getDefault());
				RefreshTime.setRefreshTime(mContext, df.format(new Date()));
				mListView.setRefreshTime(RefreshTime.getRefreshTime(mContext));
				mListView.onLoadComplete();
			}

			@Override
			public void onFailure(int errorCode, String msg) {
				Log.v("tt", "onFailure errorCode: " + errorCode + " msg: "
						+ msg);
			}
		}, TAG);
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mPageIndex = 1;
		requestData(projectStatus);
	}

	@Override
	public void onLoadMore() {
		// TODO Auto-generated method stub
		mPageIndex++;
		requestData(projectStatus);
	}

}
