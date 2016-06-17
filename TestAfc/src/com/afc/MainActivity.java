package com.afc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afc.App.BasicActivity;
import com.afc.App.BasicFragmentActivity;
import com.afc.App.PageFragment;
import com.afc.module.mainactivity.HomepageFrgm;
import com.afc.product.activity.ProductActivity;
import com.afc.product.fragment.ProductListFrgm;
import com.afc.registered.activity.SetUpPasswordActivity;
import com.bumptech.glide.Glide;
import com.idroid.tab.TabFrgmAdapter;
import com.idroid.tab.TabSelectView;
import com.idroid.utils.DroidHolder;
import com.idroid.widget.Toaster;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BasicFragmentActivity {


    private Context mContext;
    private final int[] imageRes = new int[]
            {R.drawable.icon_project, R.drawable.icon_product, R.drawable.icon_message, R.drawable.icon_my};
    private final int[] textRes = new int[]
            {R.string.tab_1, R.string.tab_2, R.string.tab_3, R.string.tab_4};
    private final Class<? extends PageFragment>[] clss = new Class[]
            {HomepageFrgm.class,ProductActivity.class};
    private TabSelectView tabsView;
    private long firstTime = 0;
    private TabFrgmAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        initTabs();
        JPushInterface.clearAllNotifications(getApplicationContext());
        openEventBus();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;// 更新firstTime
                    return true;
                } else { // 两次按键小于2秒时，退出应用
                    Glide.get(this).clearMemory();
                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(new Intent(EXITAPP));
                }
                break;
            default:
                break;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);
        int position = intent.getIntExtra("positon", 1);
        tabsView.setCurrentItem(position);
        if (position == 2) {
            adapter.getItem(position).setArguments(intent.getExtras());
        }
    }
    private void initTabs() {

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new TabFrgmAdapter(getSupportFragmentManager(), clss);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(clss.length);
        tabsView = (TabSelectView) findViewById(R.id.tabs);
        tabsView.setViewPager(viewPager);
        tabsView.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                if (null == convertView) {
                    convertView = View.inflate(parent.getContext(), R.layout.tab_item, null);
                }
                ImageView image = DroidHolder.get(convertView, R.id.item_image);
                TextView textView = DroidHolder.get(convertView, R.id.item_text);

                image.setImageResource(imageRes[position]);
                textView.setText(textRes[position]);
                return convertView;
            }

            @Override
            public long getItemId(int position) {

                return 0;
            }

            @Override
            public Object getItem(int position) {

                return null;
            }

            @Override
            public int getCount() {

                return textRes.length;
            }
        });
        tabsView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        Toaster.show(mContext,"0000000000");
                        //MobclickAgent.onEvent(getApplicationContext(), Statistical.ClickID.Fujin);
                        break;
                    case 1:
                        Toaster.show(mContext, "11111111111");
                        break;
                    case 2:
                        Toaster.show(mContext,"22222222222");
                        break;
                    case 3:
                        Toaster.show(mContext,"33333333333");

                        break;

                    default:
                        break;
                }

            }
        });
        tabsView.setCurrentItem(0);
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {

                ((FrameLayout.LayoutParams) viewPager.getLayoutParams()).setMargins(0, 0, 0, tabsView.getHeight());
            }
        }, 100);

    }
}
