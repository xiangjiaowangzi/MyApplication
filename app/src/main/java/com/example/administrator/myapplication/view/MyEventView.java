package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.myapplication.Utils;

/**
 * Created by Lbin on 2017/7/10.
 */

public class MyEventView extends View {
    public MyEventView(Context context) {
        super(context);
    }

    public MyEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyEventView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                Utils.syso(" MotionEvent.ACTION_DOWN ");
                return true ;
            case MotionEvent.ACTION_MOVE:
                Utils.syso(" MotionEvent.ActionMove ");
                break;
            case MotionEvent.ACTION_UP:
                Utils.syso(" MotionEvent.ACTION_UP ");
                break;
        }
        return super.onTouchEvent(event);
    }
}
