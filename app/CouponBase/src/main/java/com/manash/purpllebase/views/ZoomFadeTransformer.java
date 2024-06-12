package com.manash.purpllebase.views;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class ZoomFadeTransformer implements ViewPager.PageTransformer {

    private float offset = -1;
    private float paddingPx = 100;
    private final float MIN_SCALE = 0.8f;
    private final float MAX_SCALE = 1f;

    @Override
    public void transformPage(View page, float position) {
        float pagerWidthPx = ((ViewPager) page.getParent()).getWidth();
        float pageWidthPx = pagerWidthPx - 2 * paddingPx;

        float maxVisiblePages = pagerWidthPx / pageWidthPx;
        float center = maxVisiblePages / 2f;

        float scale;
        if (position + 0.5f < center - 0.5f || position > center) {
            scale = MIN_SCALE;
        } else {
            float coef;
            if (position + 0.5f < center) {
                coef = (position + 1 - center) / 0.5f;
            } else {
                coef = (center - position) / 0.5f;
            }
            scale = coef * (MAX_SCALE - MIN_SCALE) + MIN_SCALE;
        }
        page.setScaleX(scale);
        page.setScaleY(scale);
    }


}