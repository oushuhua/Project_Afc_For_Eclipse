package com.afc.product.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.afc.R;
import com.afc.event.ProductListEntity;
import com.idroid.utils.DroidHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 */
public class ProductCodeAdapter  extends BaseAdapter {

    private Context mContext;
    private List<ProductListEntity> ProList;

    public ProductCodeAdapter(Context context, List<ProductListEntity> ProList) {
        super();
        this.mContext = context;
        this.ProList = ProList;
    }

    @Override
    public int getCount() {

        return ProList != null ? ProList.size() : 0;
    }

    @Override
    public Object getItem(int position) {

        return ProList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.product_main_list, null);
        }
        TextView mProMainTtile = DroidHolder.get(convertView, R.id.product_main_title);//标题
        ImageView mProMainCollection = DroidHolder.get(convertView, R.id.product_main_collection);//收藏图标
        TextView mProMainSize = DroidHolder.get(convertView, R.id.product_main_size);//规模
        TextView mProMainMonery = DroidHolder.get(convertView, R.id.product_main_monery);//价格
        TextView mProMainCatrgory = DroidHolder.get(convertView, R.id.product_main_catrgory);//类别
        TextView mProMainLimt = DroidHolder.get(convertView, R.id.product_main_limt);//期限
        TextView mProMainTimes = DroidHolder.get(convertView, R.id.product_main_times);//日期
        TextView mProMainProfit = DroidHolder.get(convertView, R.id.product_main_profit);//收益
        ImageView mProMainPhone = DroidHolder.get(convertView, R.id.product_main_phone);//电话
        TextView mProMainRight=DroidHolder.get(convertView,R.id.product_main_right);//右边显示期限
        TextView mProMainRightTime=DroidHolder.get(convertView,R.id.product_main_right_time);//右边显示具体时间
        TextView mProMainFenPei=DroidHolder.get(convertView,R.id.prouct_main_fenpei);//已分配显示

        ProductListEntity entity = (ProductListEntity) getItem(position);
        mProMainTtile.setText(entity.getTitle());
        if (entity.getProcess()==0){//项目类型 0为待启动 1为可购买 2为存续中 3为已完成
            mProMainSize.setText("规模");
            mProMainMonery.setText(entity.getStartScale());
            mProMainCatrgory.setText("类别:"+entity.getCategoryName());
            mProMainLimt.setText("期限");
            mProMainTimes.setText(entity.getStartTerm());
            mProMainProfit.setText("收益:"+entity.getProfitName());
        }

        if (entity.getProcess()==1){
            mProMainSize.setText("预期收益");
            mProMainMonery.setText(entity.getExpectProfit());
            mProMainCatrgory.setText("起投:"+entity.getBaseInvest());
            mProMainLimt.setText("本期规模");
            mProMainTimes.setText(entity.getCurrentScale());
            mProMainProfit.setText("期限"+entity.getActualTerm());
        }
        if (entity.getProcess()==2){
            mProMainSize.setText("最新净值");
            mProMainMonery.setText(entity.getNetworth());
            mProMainCatrgory.setText("起投:"+entity.getBaseInvest());
            mProMainLimt.setText("存续规模");
            mProMainTimes.setText(entity.getContinueScale());
            mProMainProfit.setText("期限"+entity.getActualTerm());

        }
        if (entity.getProcess()==3){
            mProMainSize.setText("规模");
            mProMainMonery.setText(entity.getStartScale());
            mProMainCatrgory.setText("类别:"+entity.getCategoryName());
            mProMainLimt.setText("最终受益"+entity.getFinalProfit());
            mProMainProfit.setText("收益:"+entity.getProfitName());

            mProMainRight.setVisibility(View.VISIBLE);
            mProMainRightTime.setVisibility(View.VISIBLE);
            mProMainFenPei.setVisibility(View.VISIBLE);
            mProMainPhone.setVisibility(View.GONE);
            mProMainCollection.setVisibility(View.GONE);

            mProMainRight.setText("期限");
            mProMainRightTime.setText(entity.getStartTerm());
        }else{
            mProMainCollection.setVisibility(View.VISIBLE);
            mProMainPhone.setVisibility(View.VISIBLE);
            mProMainRight.setVisibility(View.GONE);
            mProMainRightTime.setVisibility(View.GONE);
            mProMainFenPei.setVisibility(View.GONE);
        }


        return convertView;
    }
}
