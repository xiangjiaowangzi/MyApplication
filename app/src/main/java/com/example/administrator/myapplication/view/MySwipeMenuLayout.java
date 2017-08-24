package com.example.administrator.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.OverScroller;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Utils;

/**
 * Created by Lbin on 2017/7/10.
 */

public class MySwipeMenuLayout extends FrameLayout {

    private static final String TAG = "MySwipeMenuLayout";
    public static final int DEFAULT_SCROLLER_DURATION = 200;

    // view Id
    private int menuViewId ;
    private int contentViewId ;

    //配置属性
    private int mTouchSlp ;
    private int mMinScaleFlingVelocity ;
    private int mMaxScaleFlingVelocity ;
    private VelocityTracker mVelocityTracker ;

    private OverScroller mScroller ;

    private int mScrollerDuration = DEFAULT_SCROLLER_DURATION;

    View menuView ;
    View contentView ;

    private float mOpenPercent = 0.5f;

    /**
     * check the menu is open
     * */
    private boolean isMenuOpen ;

    /**
     * check the swipe enable
     * */
    private boolean isSwipeEnable = true ;

    /**
     * the view is in Draygging
     * */
    private boolean isDragging ;

    public MySwipeMenuLayout(Context context) {
        this(context , null);
    }

    public MySwipeMenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs , 0);
    }

    public MySwipeMenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SwipeMenuLayout);
        menuViewId = a.getResourceId(R.styleable.SwipeMenuLayout_menuViewId , menuViewId);
        contentViewId = a.getResourceId(R.styleable.SwipeMenuLayout_contentViewId , contentViewId);
        syso("menuViewId " + menuViewId);
        syso("contentViewId " + contentViewId);
        a.recycle();

        ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlp = configuration.getScaledTouchSlop();
        mMinScaleFlingVelocity = configuration.getScaledMinimumFlingVelocity();
//        mMaxScaleFlingVelocity = configuration.getScaledMaximumFlingVelocity();
        mMaxScaleFlingVelocity = 1000 ; //改为1000
        mScroller = new OverScroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (menuViewId != 0){
            menuView = findViewById(menuViewId);
        }
        if (contentViewId != 0){
            contentView = findViewById(contentViewId);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        syso(" onLayout ");

        int parentWidth = ViewCompat.getMeasuredWidthAndState(this);

        if (contentView != null){
            int contentViewWidth = ViewCompat.getMeasuredWidthAndState(contentView);
            syso(" contentViewWidth " + contentViewWidth + "  , " + ViewCompat.getMeasuredWidthAndState(contentView));
            int contentViewHeight = ViewCompat.getMeasuredHeightAndState(contentView);
            MarginLayoutParams params = (MarginLayoutParams)contentView.getLayoutParams();
            int left = contentView.getPaddingLeft() + params.leftMargin ;
            int top = contentView.getPaddingTop() + params.topMargin ;
            int right = left + contentViewWidth - contentView.getPaddingRight() - params.rightMargin ;
            int bottom = top + contentViewHeight - contentView.getPaddingBottom() - params.bottomMargin ;
            contentView.layout(left , top , right  , bottom);
            syso(" contentView 宽高 "  + left + " , " +  right  + " , " +  top  + " , " +  bottom ) ;
        }

        if (menuView != null){
            int width = ViewCompat.getMeasuredWidthAndState(menuView);
            int height = ViewCompat.getMeasuredHeightAndState(menuView);
            MarginLayoutParams params = (MarginLayoutParams)menuView.getLayoutParams();
            int left = parentWidth + menuView.getPaddingLeft() + params.leftMargin ;
            int top = menuView.getPaddingTop() + params.topMargin ;
            int right = left + width - menuView.getPaddingRight() - params.rightMargin ;
            int bottom = top + height - menuView.getPaddingBottom() - params.bottomMargin ;
            menuView.layout(left , top , right , bottom);
            syso(" menuView 宽高 "  + left + " , " +  right  + " , " +  top  + " , " +  bottom ) ;
        }
    }

    int downX ; // 按下的坐标
    int dowmY ;
    int moveX ;
    int moveY ;
    int lastX ;
    int lastY ; // 最后的坐标

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        sysoXY(" onInterceptTouchEvent isSwipeEnable "+ isSwipeEnable);
        boolean isIntercepted = super.onInterceptTouchEvent(ev);
        if (!isSwipeEnable)return isIntercepted ;
        int mask = ev.getAction();
        switch (mask){
            case MotionEvent.ACTION_DOWN :
                downX = (int) ev.getX();
                dowmY = (int) ev.getY();
                lastX = downX ;
                lastY = dowmY ;
                isIntercepted = false ;
                sysoXY(" ACTION_DOWN " + isIntercepted);
                break;
            case MotionEvent.ACTION_MOVE :
                moveX = (int) (ev.getX() - downX);
                moveY = (int) (ev.getY() - dowmY);
                sysoXY(" ACTION_MOVE ");
                if (Math.abs(moveX) > mTouchSlp && Math.abs(moveX) > Math.abs(moveY)){
                    isIntercepted = true ; // 滑动了
                    sysoXY(" 开始打断滑动 ");
                }
                break;
            case MotionEvent.ACTION_UP :
                isIntercepted = false;
                sysoXY(" ACTION_UP ");
                if (isMenuOpen() && checkResetMenuLayout((int) ev.getX())){
                    sysoXY(" 点击了menu的item " );
                    closeMenu();
                    isIntercepted = true ;
                }
                break;
            case MotionEvent.ACTION_CANCEL :
                sysoXY(" 取消111111 " );
                isIntercepted = false ;
                if (!mScroller.isFinished())mScroller.abortAnimation();
                break;
        }
        return isIntercepted;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isSwipeEnable)return super.onTouchEvent(ev) ;
        boolean onTouch = super.onTouchEvent(ev);
        checkVelocityTracker();
        mVelocityTracker.addMovement(ev);
        int mask = ev.getActionMasked();
        switch (mask){
            case MotionEvent.ACTION_DOWN :
                downX = (int) ev.getX();
                dowmY = (int) ev.getY();
                lastX = downX ;
                lastY = dowmY ;
                sysoXY(" ACTION_DOWN 11111111 ");
                if (!onTouch)onTouch=true;
                break;
            case MotionEvent.ACTION_MOVE :
                sysoXY(" ACTION_MOVE 11111111 ");
                syso(" ev.getX() " + ev.getX());
                syso("lastX" + lastX);
                moveX = (int) (ev.getX() - lastX);
                moveY = (int) (ev.getY() - lastY);
                syso(" mTouchSlp " + mTouchSlp);
                if (!isDragging && Math.abs(moveX) > mTouchSlp && Math.abs(moveX) > Math.abs(moveY)){
                    isDragging = true ; // 正在滑动中
                    sysoXY( " 正在滑动中 " );
                }
                if (isDragging){
                    if (moveX < 0){ // 右边滑动
                        sysoXY( " 右边滑动 " );
                    }else{
                        sysoXY( " 左边滑动 " );
                    }
                    scrollBy(-moveX , 0);
                    lastX = (int) ev.getX();
                    lastY = (int) ev.getY();
                }
                break;
            case MotionEvent.ACTION_UP :
                moveX = (int) (ev.getX() - downX);
                moveY = (int) (ev.getY() - dowmY);
                isDragging = false;
                mVelocityTracker.computeCurrentVelocity(1000 , mMaxScaleFlingVelocity);
                int velocityX = (int) mVelocityTracker.getXVelocity();
                if (Math.abs(velocityX) > mMinScaleFlingVelocity){ // fling
                    int duration = getSwipeDuration(ev , velocityX);
                    syso(" duration " + duration);
                    syso(" moveX " + moveX);
                        syso(" velocityX " + velocityX);
                        if (velocityX < 0) { // 如果是左边 就打开
                            sysoXY( " 速度在左边 " );
                            smoothOpenMenu(duration);
                        } else {
                            sysoXY( " 速度在右边 " );
                            smoothCloseMenu(duration);
                        }
                    ViewCompat.postInvalidateOnAnimation(this);

                }else{ // scroll
                    judgeOpenClose(moveX, moveY);
                }
                mVelocityTracker.clear();
                mVelocityTracker.recycle();
                mVelocityTracker = null;
                break;
            case MotionEvent.ACTION_CANCEL :
                syso(" 取消222222222 ");
                isDragging = false;
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                } else {
                    moveX = (int) (ev.getX() - downX);
                    moveY = (int) (ev.getY() - dowmY);
                    judgeOpenClose(moveX, moveY);
                }
                break;
        }

        return onTouch;
    }

    @Override
    public void scrollTo(int x, int y) {
        if (isSwipeEnable){
            int scrollx = checkX(x);
            if (scrollx != getScaleX()){
                super.scrollTo(scrollx , y);
            }
        }else{
            super.scrollTo(x, y);
        }
    }

    private int checkX(int x) {
        if (x < 0)
            x = 0;
        if (x > menuView.getWidth())
            x = menuView.getWidth();
        return x ;
    }

    @Override
    public void computeScroll() {
        if (!isSwipeEnable)return;
        if (mScroller.computeScrollOffset()){
            scrollTo(Math.abs(mScroller.getCurrX()) , 0);
            postInvalidate();
        }
    }

    private void judgeOpenClose(int lastX, int lastY) {
        if (Math.abs(getScrollX()) > menuView.getWidth() * mOpenPercent){
            syso(" 开启 ");
            if (Math.abs(lastX) > mTouchSlp)
                smoothOpenMenu(mScrollerDuration);
        }else{
            syso(" 关闭 ");
            smoothCloseMenu(mScrollerDuration);
        }
        ViewCompat.postInvalidateOnAnimation(this);
    }

    /**
     * compute finish duration.
     *
     * @param ev       up event.
     * @param velocity velocity x.
     * @return finish duration.
     */
    private int getSwipeDuration(MotionEvent ev, int velocity) {
        int sx = getScrollX();
        int dx = (int) (ev.getX() - sx);
        final int width = menuView.getWidth();
        final int halfWidth = width / 2;
        final float distanceRatio = Math.min(1f, 1.0f * Math.abs(dx) / width);
        final float distance = halfWidth + halfWidth * distanceInfluenceForSnapDuration(distanceRatio);
        int duration;
        if (velocity > 0) {
            duration = 4 * Math.round(1000 * Math.abs(distance / velocity));
        } else {
            final float pageDelta = (float) Math.abs(dx) / width;
            duration = (int) ((pageDelta + 1) * 100);
        }
        duration = Math.min(duration, mScrollerDuration);
        return duration;
    }

    float distanceInfluenceForSnapDuration(float f) {
        f -= 0.5f; // center the values about 0.
        f *= 0.3f * Math.PI / 2.0f;
        return (float) Math.sin(f);
    }

    /**
     * check should reset the Menu
     * */
    private boolean checkResetMenuLayout(int disX){
        return Math.abs(disX) < getWidth() - menuView.getWidth();
    }

    private void checkVelocityTracker(){
        if (mVelocityTracker == null)
            mVelocityTracker = VelocityTracker.obtain();
    }

    public boolean isSwipeEnable(){
        return isSwipeEnable ;
    }

    public boolean isMenuOpen(){
        return isMenuOpen = getScrollX() >= menuView.getWidth() ;
    }


    public void closeMenu(){
        smoothCloseMenu(mScrollerDuration);
    }

    public void smoothOpenMenu(int duration) {
        int scrollX = getScrollX();
        mScroller.startScroll(Math.abs(scrollX), 0, menuView.getWidth() - Math.abs(scrollX), 0, duration);
    }

    private void smoothCloseMenu(int duration) {
        int scrollX = getScrollX();
        mScroller.startScroll(-Math.abs(scrollX), 0, Math.abs(scrollX), 0, duration);
    }

    void syso(String s){
        Utils.syso(TAG +": " + s);
    }

    void sysoXY(String s){
        syso(" ------ "  +  s + "  ----------- ");
        syso(" downX " + downX + " downY " + dowmY);
        syso(" moveX " + moveX + " moveY " + moveY);
        syso(" lastX " + lastX + " lastY " + lastY);
        syso(" -------------------------- ");
    }
}
