package com.example.administrator.myapplication.view;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import com.example.administrator.myapplication.Utils;

/**
 * Created by Lbin on 2017/8/10.
 */

public class NestedScrollParentView extends FrameLayout implements NestedScrollingParent{

    private NestedScrollingParentHelper nestedScrollingParentHelper;


    public NestedScrollParentView(Context context) {
        super(context);
        init();
    }

    public NestedScrollParentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NestedScrollParentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    void init(){
        nestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    }

    /**
     * 一般作为接受
     * */
    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        Utils.syso("onNestedScrollAccepted " + " child " + child.getClass().getSimpleName() + " target " + target.getClass().getSimpleName() + " axrs " + axes);
        super.onNestedScrollAccepted(child, target, axes);
//        nestedScrollingParentHelper.onNestedScrollAccepted(child , target ,axes);
    }

    @Override
    public int getNestedScrollAxes() {
        int axes = super.getNestedScrollAxes() ;
        Utils.syso("getNestedScrollAxes" + "  axes "  + axes);
        return axes;
//        return nestedScrollingParentHelper.getNestedScrollAxes();
    }

    @Override
    public void onStopNestedScroll(View child) {
        Utils.syso("onStopNestedScroll ");
        super.onStopNestedScroll(child);
//        nestedScrollingParentHelper.onStopNestedScroll(child);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        super.onStartNestedScroll(child ,  target , nestedScrollAxes);
        Utils.syso("onStartNestedScroll " + " child " + child.getClass().getSimpleName() + " target " + target.getClass().getSimpleName() + " axrs " + nestedScrollAxes);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Utils.syso("onNestedPreScroll " + " tagrget " + target.getClass().getSimpleName() + " dx " + dx + " dy  "  + dy  + " consumed " + consumed[0] + " consumed[1] " + consumed[1]) ;
        super.onNestedPreScroll(target , dx , dy , consumed);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Utils.syso("onNestedScroll  " + " dxConsumed  " + dxConsumed + " dyConsumed " + dyConsumed + " dxUnconsumed " + dxUnconsumed + " dyUnconsumed " + dyUnconsumed);
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed){
        Utils.syso("onNestedFling"  +  " velocityX " + velocityX + " velocityY  " + velocityY);
        return super.onNestedFling(target , velocityX , velocityY , consumed);
    }

    public boolean onNestedPreFling(View target, float velocityX, float velocityY){
        Utils.syso("onNestedPreFling"  +  "target " + target .getClass().getSimpleName() + " velocityX " + velocityX + " velocityY  " + velocityY);
        return  super.onNestedPreFling(target , velocityX , velocityY);
    }
}
