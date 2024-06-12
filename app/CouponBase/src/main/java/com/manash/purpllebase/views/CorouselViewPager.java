package com.manash.purpllebase.views;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Custom {@link ViewPager} implementation to resolve scroll gesture directions more accurate than a regular
 * {@link ViewPager} component. This will make it perfectly usable into a scroll container such as {@link ScrollView},
 * {@link ListView}, etc.
 * <p/>
 * Default ViewPager becomes hardly usable when it's nested into a scroll container. Such container will intercept any
 * touch event with minimal vertical shift from the child ViewPager. So switch the page by scroll gesture with a regular
 * {@link ViewPager} nested into a scroll container, user will need to move his finger horizontally without vertical
 * shift. Which is obviously quite irritating. {@link CorouselViewPager} has a much much better behavior at resolving
 * scrolling directions.
 */
public class CorouselViewPager extends ViewPager implements ViewPager.PageTransformer {

    private final int mPageMarginLeft;
    // -----------------------------------------------------------------------
    //
    // Fields
    //
    // -----------------------------------------------------------------------
    private GestureDetector mGestureDetector;
    private boolean mIsLockOnHorizontalAxis = false;

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private boolean mIsScrolling = false;
    private boolean mIsAutoScroll = false;
    private Timer mTimer = null;
    private AutoScrollTask mAutoScrollTask = null;
    private int mCurrentPage = 0;
    private boolean isPagingEnabled = true;

    public static final String TAG = "KKViewPager";
    private float MAX_SCALE = 0.0f;
    private int mPageMargin;
    private boolean animationEnabled = true;
    private boolean fadeEnabled = false;
    private float fadeFactor = 0.5f;

    // -----------------------------------------------------------------------
    //
    // Constructor
    //
    // -----------------------------------------------------------------------
    public CorouselViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mGestureDetector = new GestureDetector(context, new XScrollDetector());
        setClipChildren(false);
        setClipToPadding(false);
        // to avoid fade effect at the end of the page
        setOverScrollMode(2);
        setPageTransformer(false, this);
        setOffscreenPageLimit(3);
        mPageMargin = dp2px(context.getResources(), 40);
        mPageMarginLeft = dp2px(context.getResources(), 50);
        setPadding(mPageMarginLeft, mPageMargin, mPageMarginLeft, mPageMargin);
    }

    // -----------------------------------------------------------------------
    //
    // Methods
    //
    // -----------------------------------------------------------------------
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // decide if horizontal axis is locked already or we need to check the scrolling direction
        if (!mIsLockOnHorizontalAxis)
            mIsLockOnHorizontalAxis = mGestureDetector.onTouchEvent(event);

        // release the lock when finger is up
        if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
            mIsLockOnHorizontalAxis = false;
            if (mIsAutoScroll) startPagerAutoScroll();
        } else {
            if (mIsAutoScroll) stopPagerAutoScroll();
        }

        getParent().requestDisallowInterceptTouchEvent(mIsLockOnHorizontalAxis);
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    // -----------------------------------------------------------------------
    //
    // Inner Classes
    //
    // -----------------------------------------------------------------------
    private class XScrollDetector extends SimpleOnGestureListener {

        // -----------------------------------------------------------------------
        //
        // Methods
        //
        // -----------------------------------------------------------------------

        /**
         * @return true - if we're scrolling in X direction, false - in Y direction.
         */
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX) > Math.abs(distanceY);
        }
    }

    public void setAutoScroll(boolean isAutoScroll) {
        mIsAutoScroll = isAutoScroll;
        if (isAutoScroll) {
            addOnPageChangeListener(new OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mCurrentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
            startPagerAutoScroll();
        }
    }

    public void startPagerAutoScroll() {
        if (!mIsScrolling) {
            mTimer = new Timer();
            mAutoScrollTask = new AutoScrollTask();
            mTimer.scheduleAtFixedRate(mAutoScrollTask, 2 * 1000, 2 * 1000);
            mIsScrolling = true;
        }
    }

    public void stopPagerAutoScroll() {
        if (mIsScrolling && mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            mAutoScrollTask = null;
            mIsScrolling = false;
        }
    }

    private class AutoScrollTask extends TimerTask {
        @Override
        public void run() {
            mHandler.post(new Runnable() {
                public void run() {
                    setCurrentItem(mCurrentPage++, true);
                    if (mCurrentPage == getAdapter().getCount()) {
                        mCurrentPage = 0;
                    }
                }
            });
        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        if (mIsAutoScroll) {
            if (visibility == VISIBLE) {
                setCurrentItem(mCurrentPage);
                startPagerAutoScroll();
            } else {
                stopPagerAutoScroll();
            }
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (mIsAutoScroll) {
            if (hasWindowFocus) {
                setCurrentItem(mCurrentPage);
                startPagerAutoScroll();
            } else {
                stopPagerAutoScroll();
            }
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }


    public int dp2px(Resources resource, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resource.getDisplayMetrics());
    }

    public void setAnimationEnabled(boolean enable) {
        this.animationEnabled = enable;
    }

    public void setFadeEnabled(boolean fadeEnabled) {
        this.fadeEnabled = fadeEnabled;
    }

    public void setFadeFactor(float fadeFactor) {
        this.fadeFactor = fadeFactor;
    }

    @Override
    public void setPageMargin(int marginPixels) {
        mPageMargin = marginPixels;
        setPadding(mPageMargin, mPageMargin, mPageMargin, mPageMargin);
    }

    @Override
    public void transformPage(View page, float position) {
        if (mPageMargin <= 0 || !animationEnabled)
            return;
        page.setPadding(mPageMargin / 3, mPageMargin / 3, mPageMargin / 3, mPageMargin / 3);

        if (MAX_SCALE == 0.0f && position > 0.0f && position < 1.0f) {
            MAX_SCALE = position;
        }
        position = position - MAX_SCALE;
        float absolutePosition = Math.abs(position);
        if (position <= -1.0f || position >= 1.0f) {
            if (fadeEnabled)
                page.setAlpha(fadeFactor);
            // Page is not visible -- stop any running animations

        } else if (position == 0.0f) {

            // Page is selected -- reset any views if necessary
            page.setScaleX((1 + MAX_SCALE));
            page.setScaleY((1 + MAX_SCALE));
            page.setAlpha(1);
        } else {
            page.setScaleX(1 + MAX_SCALE * (1 - absolutePosition));
            page.setScaleY(1 + MAX_SCALE * (1 - absolutePosition));
            if (fadeEnabled)
                page.setAlpha(Math.max(fadeFactor, 1 - absolutePosition));
        }
    }
}