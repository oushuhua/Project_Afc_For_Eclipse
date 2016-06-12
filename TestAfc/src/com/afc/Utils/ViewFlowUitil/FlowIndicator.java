
package com.afc.Utils.ViewFlowUitil;


import com.afc.Utils.ViewFlowUitil.ViewFlow;

/**
 * 
 * @ClassName: FlowIndicator
 * @Description: TODO(广告轮播相关)
 * @date 2014-12-24 下午2:47:07
 */
public interface FlowIndicator extends ViewFlow.ViewSwitchListener {


	/**
	 * Set the current ViewFlow. This method is called by the ViewFlow when the
	 * FlowIndicator is attached to it.
	 * 
	 * @param view
	 */
	public void setViewFlow(ViewFlow view);

	/**
	 * The scroll position has been changed. A FlowIndicator may implement this
	 * method to reflect the current position
	 * 
	 * @param h
	 * @param v
	 * @param oldh
	 * @param oldv
	 */
	public void onScrolled(int h, int v, int oldh, int oldv);
}
