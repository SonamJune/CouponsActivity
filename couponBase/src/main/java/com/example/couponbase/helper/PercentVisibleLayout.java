package com.example.couponbase.helper;


import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class PercentVisibleLayout extends RelativeLayout {
    public static final int TOP = 1;
    public static final int BOTTOM = 2;
    public static final int RIGHT = 3;
    public static final int LEFT = 4;
    public static final int LEFT_AND_RIGHT = 5;
    public static final int TOP_AND_BOTTOM = 6;
    public static final int NOWHERE = 7;


    public PercentVisibleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentVisibleLayout(Context context) {
        super(context);
    }

    public int calculateVisibility() {
        Rect rectf = new Rect();
        this.getLocalVisibleRect(rectf);

        int fromWhereHeight;
        int fromWhereWidth;
        int top = rectf.top;
        int bottom = rectf.bottom;
        int right = rectf.right;
        int left = rectf.left;
        int width = this.getWidth();
        int height = this.getHeight();
        int heightPercentage;
        int heightPixels;
        int widthPercentage;
        int widthPixels;


        if (top != 0 && bottom != height) {
            fromWhereHeight = TOP_AND_BOTTOM;

        } else if (top != 0) {
            fromWhereHeight = TOP;
        } else if (bottom != height) {
            fromWhereHeight = BOTTOM;

        } else {
            fromWhereHeight = NOWHERE;
        }


        if (left != 0 && right != width) {
            fromWhereWidth = LEFT_AND_RIGHT;

        } else if (left != 0) {
            fromWhereWidth = LEFT;
        } else if (right != width) {
            fromWhereWidth = RIGHT;

        } else {
            fromWhereWidth = NOWHERE;
        }


        heightPixels = height + top - bottom;
        widthPixels = width + left - right;

        heightPercentage = (int) (100 - ((double) (heightPixels) / height) * 100);
        widthPercentage = (int) (100 - ((double) (widthPixels) / width) * 100);

        if (fromWhereHeight == 6 || fromWhereWidth == 5) {
            return 0;
        } else if (fromWhereHeight == NOWHERE && fromWhereWidth == NOWHERE) {
            return 100;
        } else if (fromWhereWidth > fromWhereHeight) {
            return heightPercentage;
        } else {
            return widthPercentage;
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
    }
}

