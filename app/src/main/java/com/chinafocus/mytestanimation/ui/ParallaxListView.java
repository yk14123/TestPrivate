package com.chinafocus.mytestanimation.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.OvershootInterpolator;
import android.widget.ListView;

public class ParallaxListView extends ListView {

    private int mParallaxHeight;
    private int mInitRawHeight;
    private View mView;
    private CardView mCardView;

    public ParallaxListView(Context context) {
        super(context);
    }

    public ParallaxListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setHeaderView(View v) {
        mView = v;
        mView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mInitRawHeight = mView.getHeight();
                mParallaxHeight = (int) (mInitRawHeight * 1.2);
            }
        });
    }

    /**
     * 在listview滑动到头的时候执行，可以获取到继续滑动的距离和方向
     * deltaX：继续滑动x方向的距离
     * deltaY：继续滑动y方向的距离     负：表示顶部到头   正：表示底部到头  按住不放一直慢慢移动，值一直是很小的，说明是一个累加的值
     * maxOverScrollX:x方向最大可以滚动的距离
     * maxOverScrollY：y方向最大可以滚动的距离
     * isTouchEvent: true: 是手指拖动滑动     false:表示fling靠惯性滑动;
     */
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {

        if (deltaY < 0 && isTouchEvent) {
            int newHeight = mView.getHeight() - deltaY / 3;
            if (newHeight >= mParallaxHeight) newHeight = mParallaxHeight;
            if (mView != null) {
                mView.getLayoutParams().height = newHeight;
//                mView.setPadding(0,newHeight,0,0);
                mView.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            //需要将ImageView的高度缓慢恢复到最初高度
            ValueAnimator animator = ValueAnimator.ofInt(mView.getHeight(), mInitRawHeight);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animator) {
                    //获取动画的值，设置给imageview
                    int animatedValue = (Integer) animator.getAnimatedValue();
                    mView.getLayoutParams().height = animatedValue;
                    mView.requestLayout();//使ImageView的布局参数生效
                }
            });
            animator.setInterpolator(new OvershootInterpolator(5));//弹性的插值器
            animator.setDuration(350);
            animator.start();

        }
        return super.onTouchEvent(ev);
    }
}
