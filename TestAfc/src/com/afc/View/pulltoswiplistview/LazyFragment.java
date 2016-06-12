package com.afc.View.pulltoswiplistview;

import android.support.v4.app.Fragment;

/**
 * 实现懒加载的Fragment
 * 
 * @author zhangbo
 *
 */
public abstract class LazyFragment extends Fragment {

	protected boolean isVisible;

	/**
	 *  此处实现Fragment的懒加载,在OnCreateView之前被调用
	 */
	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (getUserVisibleHint()) {
			isVisible = true;
			onVisible();
		} else {
			isVisible = false;
			onInvisible();
		}
	}

	/**
	 * Fragment可见时调用
	 */
	protected void onVisible() {
		lazyLoad();
	}

	/**
	 * 在Fragment可见时执行加载数据的操作
	 */
	protected abstract void lazyLoad();

	/**
	 * Fragment不可见是调用
	 */
	protected void onInvisible() {
	}

}
