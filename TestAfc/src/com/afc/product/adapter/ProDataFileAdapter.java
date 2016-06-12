package com.afc.product.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.afc.R;
import com.afc.event.FileEntity;
import com.afc.event.ProductListEntity;
import com.idroid.utils.DroidHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/5/22.
 */
public class ProDataFileAdapter extends BaseAdapter {

    private Context mContext;
    private List<FileEntity> ProList;

    public ProDataFileAdapter(Context context, List<FileEntity> ProList) {
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

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.product_data_file_list, null);
        }
        TextView mDataFileText= DroidHolder.get(convertView,R.id.data_file_text);
        FileEntity entity = (FileEntity) getItem(position);
        mDataFileText.setText(entity.getName());
        return convertView;
    }

}
