package com.example.administrator.myapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.administrator.myapplication.Utils;

/**
 * Created by Lbin on 2017/7/25.
 */

public class TouchView extends View {

    private static final String TAG = "TouchView";

    public TouchView(Context context) {
        super(context);
    }

    public TouchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public boolean onTouchEvent(MotionEvent event) {
        syos(" onTouchEvent ");
        syo(event);
        boolean b = super.onTouchEvent(event) ;
        syos(" onTouchEvent " + b);
        return b;
    }

    void syos(String s){
        Utils.syso(TAG + " : " + s);
    }

    void syo(MotionEvent e) {
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                syos("ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                syos("ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                syos("ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                syos("ACTION_CANCEL");
                break;
        }
    }
}
