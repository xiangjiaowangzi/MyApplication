package com.test.drag.myDrag;

import android.app.Service;
import android.content.Context;
import android.os.Vibrator;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Lbin on 2017/9/14.
 */

public class MyDragLayout extends LinearLayout {

    Context context ;
    ViewDragHelper mViewDragHelper ;
    Vibrator vibrator ;
    GestureDetectorCompat mGestureDetector ;

    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_START_DRAG = 1;
    public static final int ACTION_STATE_DRAG_ING = 2;
    public static final int ACTION_STATE_SWIPE = 3;

    int mActionState = ACTION_STATE_IDLE ;



    public MyDragLayout(Context context) {
        this(context , null);
    }

    public MyDragLayout(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public MyDragLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context ;
        init();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = getChildCount() ;
        log(" count " + count);
        if (count > 0){
            for (int i = 0 ; i < count ; i++){
                View child = getChildAt(i);
                child.setClickable(true);
                child.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        mGestureDetector.onTouchEvent(event);
                        return false;
                    }
                });
            }
        }
    }

    void init(){
        vibrator = (Vibrator) getContext().getSystemService(Service.VIBRATOR_SERVICE);
        mViewDragHelper = ViewDragHelper.create(this , new DragHelpCallBack());
        mGestureDetector = new GestureDetectorCompat(context , new ChildTouchLongListener());
    }

    private class ChildTouchLongListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            log(" onDown ");
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            log(" onLongPress ");
            mActionState = ACTION_STATE_START_DRAG ; // start Drag

        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        log(" onInterceptTouchEvent " + mActionState);
        if (mActionState > ACTION_STATE_IDLE){
//            boolean b = mViewDragHelper.shouldInterceptTouchEvent(ev) ;
//            log("b "+ b);
            return true ;
        }else{
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        log(" onTouchEvent " );
//        if (mActionState > ACTION_STATE_IDLE) {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mActionState = ACTION_STATE_IDLE ;
            }
            mViewDragHelper.processTouchEvent(event);
            return true ;
//        }
//        return super.onTouchEvent(event);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }



    private class DragHelpCallBack extends ViewDragHelper.Callback{

        @Override
        public void onEdgeTouched(int edgeFlags, int pointerId) {
            log("onEdgeTouched");
            super.onEdgeTouched(edgeFlags, pointerId);
        }

        @Override
        public boolean onEdgeLock(int edgeFlags) {
            log("onEdgeTouched");
            return super.onEdgeLock(edgeFlags);
        }

        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            log("tryCaptureView");
            return true;
        }


        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            log("onViewPositionChanged " + " x " + dx + " y " + dy);
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }

        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            log("onViewCaptured");
            super.onViewCaptured(capturedChild, activePointerId);
        }

        @Override
        public void onViewDragStateChanged(int state) {
            log("onViewDragStateChanged " + state);
            super.onViewDragStateChanged(state);
            mActionState = state ;
            log(" state " + state);
        }

        @Override
        public int getViewHorizontalDragRange(View child) {
            log("getViewHorizontalDragRange ");
            int appendChild = 0 ;
            return super.getViewHorizontalDragRange(child);
        }

        @Override
        public int getViewVerticalDragRange(View child) {
            log("getViewVerticalDragRange ");
            int appendChild = 0 ;
            return super.getViewVerticalDragRange(child);

        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            log("clampViewPositionHorizontal " + left);
            int appendChild = 0 ;
            final int leftX = getPaddingLeft();
            final int rightX = getWidth() - child.getWidth() - getPaddingRight() - appendChild;
            return Math.min(Math.max(left, leftX), rightX);
        }

        @Override
        public int getOrderedChildIndex(int index) {
            return super.getOrderedChildIndex(index);
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            log("clampViewPositionVertical " + top);
            int appendChild = 0 ;
            final int topY = (getPaddingTop());
//            int topY = 0 ;
            final int bottomY = getHeight() - child.getHeight() - getPaddingBottom() - appendChild ;
            return Math.min(Math.max(topY , top) , bottomY);
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            log("onViewReleased ");
            super.onViewReleased(releasedChild, xvel, yvel);
        }
    }

    void log(String s){
        Log.e("aa" , s ) ;
    }

}
