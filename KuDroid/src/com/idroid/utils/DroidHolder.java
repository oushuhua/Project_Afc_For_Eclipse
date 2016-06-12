package com.idroid.utils;

import android.util.SparseArray;
import android.view.View;
import butterknife.ButterKnife;

/**
 * 使用方式
 * if(convertView == null){
			convertView = View.inflate(parent.getContext(), R.layout.imlist_item, null);
		}
		
		TextView nicknameText = DroidHolder.get(convertView, R.id.nickname_text);
		nicknameText.setText("text");
		return convertView;
 */
/**
 * Viewholder工具类化
 * @author yiyi
 * @date 2016年2月24日
 */
public class DroidHolder {
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View convertView, int id) {
		SparseArray<View> sparseArray = (SparseArray<View>) convertView.getTag();
		if (sparseArray == null) {
			sparseArray = new SparseArray<View>();
			convertView.setTag(sparseArray);
		}
		View childView = sparseArray.get(id);
		if (childView == null) {
			childView = ButterKnife.findById(convertView, id);
			sparseArray.put(id, childView);
		}
		return (T) childView;
	}
}
