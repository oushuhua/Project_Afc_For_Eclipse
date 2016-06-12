package com.afc.Utils;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afc.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/5/5.
 */
public class TitleHolder {
    @Bind(R.id.left_image)
    ImageView mLeftImage;
    @Bind(R.id.left_text)
    TextView mLeftText;
    @Bind(R.id.right_image)
    ImageView mRightImage;
    @Bind(R.id.title_text)
    public TextView mTitleText;
    @Bind(R.id.right_text)
    TextView mRightText;

    public TitleHolder(View view) {
        ButterKnife.bind(this, view);
    }

    public void defineTitle(String title) {
        mTitleText.setText(title);
    }

    public void defineLeft(int resId,String text, View.OnClickListener l) {
        mLeftImage.setVisibility(View.VISIBLE);
        mLeftText.setVisibility(View.VISIBLE);
        mLeftImage.setImageResource(resId);
        mLeftText.setText(text);
        mLeftImage.setOnClickListener(l);
    }

    public void defineRight(int resId, View.OnClickListener l) {
        mRightImage.setVisibility(View.VISIBLE);
        mRightImage.setImageResource(resId);
        mRightImage.setOnClickListener(l);
    }

    public void defineRight(String text, View.OnClickListener l) {
        mRightText.setVisibility(View.VISIBLE);
        mRightText.setText(text);
        mRightText.setOnClickListener(l);
    }
}
