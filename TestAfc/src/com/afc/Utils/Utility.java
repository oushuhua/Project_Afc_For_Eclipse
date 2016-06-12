package com.afc.Utils;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 
 * @author 订单页面中显示多条信息时ListView不能显示完全的方法
 *
 */
public class Utility {

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		//获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
		// pre-condition
		return;
		}

		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) { //listAdapter.getCount()返回数据项的数目
		View listItem = listAdapter.getView(i, null, listView);
		listItem.measure(0, 0); //计算子项View 的宽高
		totalHeight += listItem.getMeasuredHeight(); //统计所有子项的总高度
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		//listView.getDividerHeight()获取子项间分隔符占用的高度
		//params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
		}
	
    @SuppressWarnings("unused")
	public static void setListViewHeight(ExpandableListView listView) {  
        ListAdapter listAdapter = listView.getAdapter();  
        int totalHeight = 0;  
        int count = listAdapter.getCount();  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight  
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
        listView.requestLayout();  
    }  
    
    /**
     * 计算GridView的高度
     */
    public static void setGridViewHeightBasedOnChildren(GridView listView) {  
        // 获取listview的adapter  
           ListAdapter listAdapter = listView.getAdapter();  
           if (listAdapter == null) {  
               return;  
           }  
           // 固定列宽，有多少列  
           int col = 4;// listView.getNumColumns();  
           int totalHeight = 0;  
           // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，  
           // listAdapter.getCount()小于等于8时计算两次高度相加  
           for (int i = 0; i < listAdapter.getCount(); i += col) {  
            // 获取listview的每一个item  
               View listItem = listAdapter.getView(i, null, listView);  
               listItem.measure(0, 0);  
               // 获取item的高度和  
               totalHeight += listItem.getMeasuredHeight();  
           }  
      
           // 获取listview的布局参数  
           ViewGroup.LayoutParams params = listView.getLayoutParams();  
           // 设置高度  
           params.height = totalHeight;  
           // 设置margin  
           ((MarginLayoutParams) params).setMargins(10, 10, 10, 10);  
           // 设置参数  
           listView.setLayoutParams(params);  
       }  
    
}
