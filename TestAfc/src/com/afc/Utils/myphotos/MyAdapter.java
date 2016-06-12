package com.afc.Utils.myphotos;

import java.util.List;

import com.afc.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MyAdapter extends CommonAdapter<String> {

	/**
	 * 用户选择的图片，存储为图片的完整路径
	 */
	public List<String> mSelectedImage;
	int surplusSize; //剩余可选图片的张数
	/**
	 * 文件夹路径
	 */
	private String mDirPath;

	public MyAdapter(Context context, List<String> mDatas,List<String> selectedImgs, int itemLayoutId,
			String dirPath,int surplusSize) {
		super(context, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
		this.mSelectedImage = selectedImgs;
		this.surplusSize = surplusSize;
		
		initButtonState();
	}

	@Override
	public void convert(final ViewHolder helper, final String item) {
		// 设置no_pic
		helper.setImageResource(R.id.id_item_image, R.mipmap.pictures_no);
		// 设置no_selected
		helper.setImageResource(R.id.id_item_select,
				R.mipmap.picture_unselected);
		// 设置图片
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);

		mImageView.setColorFilter(null);
		// 设置ImageView的点击事件
		mImageView.setOnClickListener(new OnClickListener() {
			// 选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v) {
				// 已经选择过该图片
				if (mSelectedImage.contains(mDirPath + "/" + item)) {
					mSelectedImage.remove(mDirPath + "/" + item);
					mSelect.setImageResource(R.mipmap.picture_unselected);
					mImageView.setColorFilter(null);
				} else
				// 未选择该图片
				{
					 if(mSelectedImage.size() >= surplusSize){
		                	Toast.makeText(mContext, "超过可选图片数量上限", Toast.LENGTH_SHORT).show();
		                }else{
		                	mSelectedImage.add(mDirPath + "/" + item);
							mSelect.setImageResource(R.mipmap.pictures_selected);
							mImageView.setColorFilter(Color.parseColor("#77000000"));
		                }
					
				}
				initButtonState();
			}
		});

		/**
		 * 已经选择过的图片，显示出选择过的效果
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelect.setImageResource(R.mipmap.pictures_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}
	
	private void initButtonState(){
		if(mContext instanceof PhotoSelectActivity){
			PhotoSelectActivity activity = (PhotoSelectActivity) mContext;
			if(mSelectedImage.size() > 0){
				activity.preview.setTextColor(Color.WHITE);
				activity.rightTextView.setTextColor(Color.WHITE);
				activity.rightTextView.setText("完成("+ mSelectedImage.size() +"/"+ surplusSize +")");
			}else{
				activity.preview.setTextColor(Color.parseColor("#999999"));
				activity.rightTextView.setTextColor(Color.parseColor("#999999"));
				activity.rightTextView.setText("完成("+ mSelectedImage.size() +"/"+ surplusSize +")");
			}
		}
		
	}
}
