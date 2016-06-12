package com.idroid.async;

import java.lang.ref.WeakReference;

import android.os.AsyncTask;

/**
 * 避免内存问题
 * 
 * @author yi
 *
 * @param <T>
 */
public abstract class DroidTask<T> extends AsyncTask<Void, Void, T> {
	protected WeakReference<Object> mTarget;

	public DroidTask(Object target) {
		mTarget = new WeakReference<Object>(target);
	}

	@Override
	protected final void onPreExecute() {
		final Object target = mTarget.get();
		if (target != null) {
			this.onPreExecute(target);
		}
	}

	@Override
	protected final T doInBackground(Void... params) {
		final Object target = mTarget.get();
		if (target != null) {
			return this.doInBackground();
		} else {
			return null;
		}
	}

	@Override
	protected final void onPostExecute(T result) {
		final Object target = mTarget.get();
		if (target != null) {
			this.onPostExecute(target, result);
		}
	}

	protected void onPreExecute(Object target) {
	}

	protected abstract T doInBackground();

	protected void onPostExecute(Object target, T result) {
	}
}
