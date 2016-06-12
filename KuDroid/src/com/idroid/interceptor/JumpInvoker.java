package com.idroid.interceptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class JumpInvoker implements Invoker, Parcelable {

	private String mTargetAction;
	private Bundle mData;

	public JumpInvoker(String targetAction, Bundle data) {
		super();
		this.mTargetAction = targetAction;
		if (data == null)
			mData = new Bundle();
		else
			mData = data;
	}

	public JumpInvoker(Parcel in) {
		mTargetAction = in.readString();
		mData = in.readBundle();
	}

	@Override
	public void invoke(Context ctx) {
		if (mTargetAction == null)
			return;
		Intent it = new Intent(mTargetAction);
		it.putExtras(mData);
		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ctx.startActivity(it);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(mTargetAction);
		dest.writeBundle(mData);
	}

	public static final Parcelable.Creator<JumpInvoker> CREATOR = new Parcelable.Creator<JumpInvoker>() {

		public JumpInvoker createFromParcel(Parcel in) {
			return new JumpInvoker(in);
		}

		public JumpInvoker[] newArray(int size) {
			return new JumpInvoker[size];
		}
	};

}