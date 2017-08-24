package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.administrator.myapplication.Utils;

/**
 * Created by Lbin on 2017/7/7.
 */

public class MyFramelayout extends FrameLayout {

    private static final String TAG = "MyFramelayout";

    public MyFramelayout(Context context) {
        super(context);
    }

    public MyFramelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFramelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
//        int count = getChildCount();
//        for (int i = 0 ; i < count ; i++){
//            View view = getChildAt(i);
//            Utils.syso(" view " + view.getClass().getSimpleName());
//        }
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
        Utils.syso(TAG + " withMode " + getMode(withMode));
        Utils.syso(TAG + " widthSize " + widthSize);
        Utils.syso(TAG + " heightMode " + getMode(heightMode));
        Utils.syso(TAG + " heightSize " + heightSize);

        Utils.syso(TAG + " messaureWidth " + getMeasuredWidth());
        Utils.syso(TAG + " messaureHeight " + getMeasuredHeight());

        if (getMeasuredHeight() == heightSize){
            Utils.syso(TAG + " 一模一样 ");
        }else{
            Utils.syso(TAG + " 不一样了 ");
        }

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
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Utils.syso(TAG + " height " + getHeight());
    }
}
