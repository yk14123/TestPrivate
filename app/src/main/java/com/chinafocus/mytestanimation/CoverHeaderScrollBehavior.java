package com.chinafocus.mytestanimation;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class CoverHeaderScrollBehavior extends CoordinatorLayout.Behavior<View> {
    public static final String TAG = "CoverHeaderScroll";

    public CoverHeaderScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    //首先是整个布局，Header 固定在顶部，列表在Header 的下方，CoordinatorLayout 是一个FrameLayout,不能提供这样的布局，我们需要重写onLayoutChild 来让列表位于Header下面
    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection) {
        Log.i(TAG, "onLayoutChild.....");
//        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
//        if (params != null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
////            child.layout(0, 0, parent.getWidth(), parent.getHeight());
////            child.setTranslationY(getHeaderHeight());
//            return true;
//        }
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    //表示该父控件是否要接收该滑动事件；
    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
        if (type == 0) {
            if (dy < 0) {
                return;
            }
            consumed[1] = child.getTop();
//            float transY = child.getTranslationY() - dy;
//            Log.i(TAG, "transY:" + transY + "++++child.getTranslationY():" + child.getTranslationY() + "---->dy:" + dy);
//            if (transY > 0) {
//                child.setTranslationY(transY);
//                consumed[1] = dy;
//            }
        }
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull View child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type);
        if (type == 0) {
            //在这个方法里只处理向下滑动
            if (dyUnconsumed > 0) {
                return;
            }
            child.setTranslationY(child.getTop());
//            float transY = child.getTranslationY() - dyUnconsumed;
//            Log.i(TAG, "------>transY:" + transY + "****** child.getTranslationY():" + child.getTranslationY() + "--->dyUnconsumed" + dxUnconsumed);
//            if (transY > 0 && transY < getHeaderHeight()) {
//                child.setTranslationY(transY);
//            }

        }

        Log.i("MyLog", "CoverHeaderScrollBehavior  int dyConsumed -- >" + dyConsumed);
        Log.i("MyLog", "CoverHeaderScrollBehavior  int dyUnconsumed -- >" + dyUnconsumed);
        Log.i("MyLog", "CoverHeaderScrollBehavior  type -- >" + type);
        Log.i("MyLog","===================================");
    }

//我们需要知道Header的高度，将Header的高度写在dimens文件中

    /*** 获取Header 高度 * @return*/
    public int getHeaderHeight() {
        return 900;
    }


}
