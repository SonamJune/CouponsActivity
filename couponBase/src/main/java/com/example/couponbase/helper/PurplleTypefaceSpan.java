package com.example.couponbase.helper;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class PurplleTypefaceSpan extends TypefaceSpan {
    private Typeface mTypeface;

    public PurplleTypefaceSpan(Typeface type) {
        super("");
        mTypeface = type;
    }

    @Override
    public void updateMeasureState(TextPaint p) {
        p.setTypeface(mTypeface);
    }

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setTypeface(mTypeface);
    }
}
