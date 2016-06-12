package com.afc.App;

import android.widget.ListView;

import com.afc.R;
import com.idroid.widget.ListViewDecorator;

/**
 * 给listview封装加载样式
 * Created by Administrator on 2016/5/5.
 */
public class LswLoader extends ListViewDecorator {

    public LswLoader(ListView listview) {
        super(listview);
    }

    /**
     * 默认给出加载图片与加载文字
     */
    public void loading() {
        loading(R.mipmap.load, null);
    }

}
