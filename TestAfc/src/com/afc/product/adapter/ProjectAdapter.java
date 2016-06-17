package com.afc.product.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.afc.db.entity.project.ProjectListEntity;

/**
 * @author houen.bao
 * @date Jun 17, 2016 2:59:01 PM
 */
public class ProjectAdapter extends BaseAdapter {

	private List<ProjectListEntity> ProList;
	private Context mContext;

	public ProjectAdapter(List<ProjectListEntity> proList, Context mContext) {
		super();
		ProList = proList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ProList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ProList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView txt = new TextView(mContext);
		txt.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		txt.setText(""+ProList.get(position).getTitle());
		return txt;
	}

}
