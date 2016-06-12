package com.afc.Utils.ViewFlowUitil;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.afc.R;
import com.afc.event.ADEntity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.common.util.ListUtils;

/**
 * 
 * @ClassName: ImageAdapter
 * @Description: TODO(图片轮播相关)
 */
public class ADImageAdapter extends BaseAdapter {

	private List<ADEntity.Detail> adList;
	private ViewFlow viewFlow;

	public ADImageAdapter(List<ADEntity.Detail> adList2, ViewFlow viewFlow) {
		this.adList = adList2;
		this.viewFlow = viewFlow;
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE; // 返回很大的值使得getView中的position不断增大来实现循环
	}

	@Override
	public Object getItem(int position) {
		return this.adList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		@Bind(R.id.imgView)
		ImageView iv;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(parent.getContext(), R.layout.image_item, null);
			holder = new ViewHolder();
			ButterKnife.bind(holder, convertView);
			holder.iv.setMaxWidth(parent.getWidth());
			holder.iv.setMaxHeight(parent.getHeight());
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.iv.setScaleType(ScaleType.FIT_XY);
		
		if (!ListUtils.isEmpty(adList)) 
			Glide.with(parent.getContext().getApplicationContext()).load(adList.get(position % adList.size()).getImageSrc()).into(holder.iv);
			
		

		return convertView;
	}

}
