package com.afc.Utils.myphotos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by WangKun on 2016/4/6.
 */
public class PhotoUploadEditView extends View {
    private PhotoUploadEditManager mPhotoUploadEditManager;
    private boolean isBinded = false;

    // 绘图参数
    private Paint mPaint = new Paint();
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private int mPaddingBottom;
    private int mWid;
    private int mHei;
    private int mImageLen;
    private int mDeleteLen;
    private int mSpaceLen;

    // 控件属性
    private int mMaxImageNum = 6;
    private int mColumNum = 3;
    private Bitmap mAddIcon;
    private Bitmap mDeleteIcon;

    private int mDownX;
    private int mDownY;

    public PhotoUploadEditView(Context context) {
        super(context);
    }

    public PhotoUploadEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPaddingLeft = getPaddingLeft();
        mPaddingRight = getPaddingRight();
        mPaddingTop = getPaddingTop();
        mPaddingBottom = getPaddingBottom();

        //设置宽度为match parent（除非指定具体数值）
        mWid = MeasureSpec.getSize(widthMeasureSpec);

        mSpaceLen = mWid / 100;
        mImageLen = (mWid - mPaddingLeft - mPaddingRight - mSpaceLen * (mColumNum - 1)) / mColumNum;
        mDeleteLen = mImageLen / 4;

        //设置高度
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            mHei = specSize;
        } else {
            // 图片数量
            int nums;
            if (null == mPhotoUploadEditManager) {
                nums = 1;
            } else {
                nums = mPhotoUploadEditManager.getImageNums();
                if (nums < mMaxImageNum) {
                    nums++;
                }
            }

            //图片占用的行数
            int hang = nums / mColumNum;
            if (nums % mColumNum != 0) {
                hang++;
            }
            //根据行数设置控件高度
            mHei = hang * mImageLen + (hang - 1) * mSpaceLen + mPaddingTop + mPaddingBottom;
        }
        setMeasuredDimension(mWid, mHei);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int nums;
        if (null == mPhotoUploadEditManager) {
            nums = 1;
        } else {
            nums = mPhotoUploadEditManager.getImageNums();
            if (nums < mMaxImageNum) {
                nums++;
            }
        }

        for (int i = 0; i < nums; i++) {
            int x = (i + 1) % mColumNum;//第x列
            int y = (i + 1) / mColumNum;//第y行，从0开始

            int left;
            int top;
            if (0 == x) {
                left = (mColumNum - 1) * (mImageLen + mSpaceLen);
                top = (y - 1) * (mImageLen + mSpaceLen);
            } else if (1 == x) {
                left = (x - 1) * mImageLen;
                top = y * (mImageLen + mSpaceLen);
            } else {
                left = (x - 1) * (mImageLen + mSpaceLen);
                top = y * (mImageLen + mSpaceLen);
            }
            left = left + mPaddingLeft;
            top = top + mPaddingTop;
            Rect imgRect = new Rect(left, top, left + mImageLen, top + mImageLen);
            Rect deleteRect = new Rect(left + 3 * mDeleteLen, top, left + mImageLen, top + mDeleteLen);

            if ((nums - 1 == i) && (mPhotoUploadEditManager.getImageNums() < mMaxImageNum)) {
                if (null != mAddIcon) {
                    canvas.drawBitmap(mAddIcon, null, imgRect, mPaint);
                }
            } else {
                if (null != mPhotoUploadEditManager) {
                    Bitmap bm = mPhotoUploadEditManager.getBitmapFromPath(mPhotoUploadEditManager.getImagePath(i), mImageLen);
                    if (null != bm) {
                        canvas.drawBitmap(bm, null, imgRect, mPaint);
                    }
                    if (null != mDeleteIcon) {
                        canvas.drawBitmap(mDeleteIcon, null, deleteRect, mPaint);
                    }
                }
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null == mPhotoUploadEditManager) {
            return super.onTouchEvent(event);
        }

        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            mDownX = (int) event.getX();
            mDownY = (int) event.getY();
            return true;
        }

        if (MotionEvent.ACTION_UP == event.getAction()) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            if (x < mPaddingLeft || x > mWid - mPaddingRight || y < mPaddingTop || y > mHei - mPaddingBottom) {
                return super.onTouchEvent(event);
            }
            if ((Math.abs(x - mDownX) > mDeleteLen) && (Math.abs(y - mDownY) > mDeleteLen)) {
                return super.onTouchEvent(event);
            }

            int multipleX = (x - mPaddingLeft) / (mImageLen + mSpaceLen); //点击处在第（multipleX+1）列
            int multipleY = (y - mPaddingTop) / (mImageLen + mSpaceLen);//点击处在第（multipleY+1）行
            int index = multipleY * mColumNum + multipleX;

            int imageNums = mPhotoUploadEditManager.getImageNums();
            if (imageNums < mMaxImageNum) {
                if (index >= 0 && index < imageNums + 1) {
                    if (imageNums == index) {
                        mPhotoUploadEditManager.startSelectPhoto();
                    } else {
                        clickImage(index, multipleX, multipleY, x, y);
                    }
                }
            } else {
                if (index >= 0 && index < imageNums) {
                    clickImage(index, multipleX, multipleY, x, y);
                }
            }
        }

        return super.onTouchEvent(event);
    }

    private void clickImage(int index, int multipleX, int multipleY, int x, int y) {
        Log.i("WangKun", "remove:" + index);
        int clickItemLeft = multipleX * (mImageLen + mSpaceLen) + mPaddingLeft;
        int clickItemTop = multipleY * (mImageLen + mSpaceLen) + mPaddingTop;
        if (x > clickItemLeft + 3 * mDeleteLen && y < clickItemTop + mDeleteLen) {
            if (null != mDeleteIcon) {
                mPhotoUploadEditManager.removeImage(index);
                requestLayout();
                invalidate();
            } else {
                mPhotoUploadEditManager.startGalerry(index);
            }
        } else {
            // 进入图片预览界面
            mPhotoUploadEditManager.startGalerry(index);
        }
    }

    /**
     * 设置最大图片数量（默认为6张）
     */
    public void setMaxImageNum(int maxImageNum) {
        mMaxImageNum = maxImageNum;
    }

    /**
     * 获取最大图片数量
     */
    public int getMaxImageNum() {
        return mMaxImageNum;
    }

    /**
     * 设置列数（默认为3列）
     */
    public void setColumNum(int columNum) {
        mColumNum = columNum;
        requestLayout();
        invalidate();
    }

    /**
     * 设置删除图标
     */
    public void setDeleteIcon(int resId) {
        Bitmap deleteBm = BitmapFactory.decodeResource(getResources(), resId);
        if (null != deleteBm) {
            mDeleteIcon = deleteBm;
        }
    }

    /**
     * 设置添加图标
     */
    public void setAddIcon(int resId) {
        Bitmap addBm = BitmapFactory.decodeResource(getResources(), resId);
        if (null != addBm) {
            mAddIcon = addBm;
        }
    }

    /**
     * 设置背景
     */
    public void setViewBackgroundColor(int color) {
        setBackgroundColor(color);
    }

    public void bindManager(PhotoUploadEditManager manager) {
        if (null == manager || isBinded) {
            //Log.i(ServiceConst.TAG, "bind failed!!! PhotoUploadEditManager is null or PhotoSelectorView has bind other view!!!");
            return;
        }
        mPhotoUploadEditManager = manager;
        isBinded = true;
    }

    public void unBindManager() {
        mPhotoUploadEditManager = null;
        isBinded = false;
    }

    public int getImageLenth() {
        return mImageLen;
    }
}
