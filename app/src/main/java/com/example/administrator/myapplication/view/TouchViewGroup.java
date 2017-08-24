package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.administrator.myapplication.Utils;

/**
 * Created by Lbin on 2017/7/25.
 */

public class TouchViewGroup extends FrameLayout {

    private static final String TAG = "TouchViewGroup";

    public TouchViewGroup(Context context) {
        super(context);
    }

    public TouchViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        syos(" dispatchTouchEvent ");
        syo(ev);
        boolean b = super.dispatchTouchEvent(ev) ;
        syos(" dispatchTouchEvent " + b);
        return b;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        syos(" onInterceptTouchEvent ");
        syo(ev);
        boolean b = super.onInterceptTouchEvent(ev) ;
        syos(" onInterceptTouchEvent " + b);
        return b;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        syos(" onTouchEvent ");
        syo(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN)return true;
        return super.onTouchEvent(event);
    }

    void syo(MotionEvent e){
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN :
                syos("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE :
                syos("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP :
                syos("ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL :
                syos("ACTION_CANCEL");
                break;
        }
    }

    void syos(String s){
        Utils.syso(TAG + " : " + s);
    }

}
