package com.afc.App;

/**
 * Created by Administrator on 2016/5/5.
 */
public class PageFragment extends BasicFrgm {

    @Override
    public void onResume() {
        super.onResume();
        //MobclickAgent.onPageStart(getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        //MobclickAgent.onPageEnd(getClass().getSimpleName());
    }
}
