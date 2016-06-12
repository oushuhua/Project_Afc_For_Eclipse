package com.mrwujay.cascade.activity;

import com.kuparts.lib.R;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

public class ShowAddressDialog extends BaseActivity implements OnClickListener,
		OnWheelChangedListener {
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	private Button mBtnConfirm;
	

	public interface ICommand {

		void cmdRunOk(Dialog dialog,String address,String codeName);
	}

	private static ShowAddressDialog showDialog;
	private Context context;
	private ICommand cmd;
	
	// 单例
	private ShowAddressDialog(Context context, ICommand cmd) {
		super(context);
		this.context=context;
		this.cmd=cmd;
	}

	public static ShowAddressDialog getInstance(Context context, ICommand cmd) {
		showDialog = new ShowAddressDialog(context, cmd);		
		return showDialog;
		
	}

	private Dialog dialog;
	
	public Dialog showDialog() {
		
		LayoutInflater inflater = LayoutInflater.from(context);
		View v = inflater.inflate(R.layout.activity_main_master, null);// 得到加载view
		
		setUpViews(v);
		setUpListener();
		setUpData();
		
		dialog = new Dialog(context, R.style.loading_dialog);// 创建自定义样式dialog
		dialog.setCancelable(true);// 不可以用“返回键”取消
		dialog.setContentView(v, new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		
		return dialog;
		//loadingDialog.show();
		
		
		
	}

	private void setUpViews(View v) {
		mViewProvince = (WheelView) v.findViewById(R.id.id_province);
		mViewCity = (WheelView) v.findViewById(R.id.id_city);
		mViewDistrict = (WheelView) v.findViewById(R.id.id_district);
		mBtnConfirm = (Button) v.findViewById(R.id.btn_confirm);
	}

	private void setUpListener() {
		// 添加change事件
		mViewProvince.addChangingListener(this);
		// 添加change事件
		mViewCity.addChangingListener(this);
		// 添加change事件
		mViewDistrict.addChangingListener(this);
		// 添加onclick事件
		mBtnConfirm.setOnClickListener(this);
	}

	private void setUpData() {
		initProvinceDatas();
		mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(
				context, mProvinceDatas));
		// 设置可见条目数量
		mViewProvince.setVisibleItems(7);
		mViewCity.setVisibleItems(7);
		mViewDistrict.setVisibleItems(7);
		updateCities();
		updateAreas();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {
		// TODO Auto-generated method stub
		if (wheel == mViewProvince) {
			updateCities();
		} else if (wheel == mViewCity) {
			updateAreas();
		} else if (wheel == mViewDistrict) {
			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentProviceName
					+ mCurrentCityName)[newValue];
			// mCurrentZipCode = mZipcodeDatasMap.get(
			// mCurrentProviceName+mCurrentCityName+mCurrentDistrictName);
		}

	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {

		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentProviceName
				+ mCurrentCityName);
		// mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict
				.setViewAdapter(new ArrayWheelAdapter<String>(context, areas));
		mViewDistrict.setCurrentItem(0);

		mCurrentDistrictName = mDistrictDatasMap.get(mCurrentProviceName
				+ mCurrentCityName)[0];

	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(context, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}

	@Override
	public void onClick(View v) {
		cmd.cmdRunOk(dialog,mCurrentProviceName+">"+mCurrentCityName+">"+mCurrentDistrictName,mZipcodeDatasMap.get(mCurrentProviceName
				+ mCurrentCityName + mCurrentDistrictName));
		
	}

	/*private void showSelectedResult() {
	}*/
}
