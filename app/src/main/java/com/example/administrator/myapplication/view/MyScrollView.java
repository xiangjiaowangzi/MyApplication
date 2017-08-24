package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

import com.example.administrator.myapplication.Utils;

/**
 * Created by Lbin on 2017/7/7.
 */

public class MyScrollView extends ScrollView {

    private static final String TAG = "MyScrollView";

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        syso(widthMode , widthSize , heightMode , heightSize);


    }

    void syso(int withMode , int widthSize , int heightMode , int heightSize){
//        Utils.syso(TAG + " withMode " + getMode(withMode));
//        Utils.syso(TAG + " widthSize " + widthSize);
//        Utils.syso(TAG + " heightMode " + getMode(heightMode));
//        Utils.syso(TAG + " heightSize " + heightSize);
    }

    String getMode(int mode){
        if (mode == MeasureSpec.EXACTLY){
            return " MeasureSpec.EXACTLY " ;
        }else if(mode == MeasureSpec.AT_MOST){
            return " MeasureSpec.AT_MOST " ;
        }else if (mode == MeasureSpec.UNSPECIFIED){
            return " MeasureSpec.UNSPECIFIED ";
        }
        return " 日你麻痹 " ;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Utils.syso(TAG + " height " + getHeight());
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY,
                                   int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        Utils.syso(" maxOverScrollY " + maxOverScrollY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, -80, isTouchEvent);
    }
}
