package com.example.couponbase.views;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;

import com.example.couponbase.R;


public class MaterialProgressBar extends androidx.appcompat.widget.AppCompatImageView {

    private static final int KEY_SHADOW_COLOR = 0x1E000000;
    private static final int FILL_SHADOW_COLOR = 0x3D000000;
    // PX
    private static final float X_OFFSET = 0f;
    private static final float Y_OFFSET = 1.75f;
    private static final float SHADOW_RADIUS = 3.5f;
    private static final int SHADOW_ELEVATION = 4;


    private static final int DEFAULT_CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    private static final int DEFAULT_CIRCLE_DIAMETER = 40;
    private static final int STROKE_WIDTH_LARGE = 2;
    private static final int DEFAULT_TEXT_SIZE = 9;

    // private Animation.AnimationListener mListener;
    private int mShadowRadius;
    private int mBackGroundColor;
    private int mProgressStokeWidth;
    private int mArrowWidth;
    private int mArrowHeight;
    private int mProgress;
    private int mMax;
    private int mInnerRadius;
    private Paint mTextPaint;
    private int mTextSize;
    private boolean mIfDrawText;
    private boolean mShowArrow;
    private MaterialProgressDrawable mProgressDrawable;
    private boolean mCircleBackgroundEnabled;
    private int[] mColors = new int[]{Color.BLACK};
    private int mDiameter;
    private float density;
    private ShapeDrawable mBgCircle;

    public MaterialProgressBar(Context context) {
        super(context);
        init(context, null, 0);

    }

    public MaterialProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);

    }

    public MaterialProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.MaterialProgressBar, defStyleAttr, 0);


        density = getContext().getResources().getDisplayMetrics().density;

        mBackGroundColor = a.getColor(
                R.styleable.MaterialProgressBar_background_color, DEFAULT_CIRCLE_BG_LIGHT);
        mInnerRadius = a.getDimensionPixelOffset(
                R.styleable.MaterialProgressBar_inner_radius, -1);

        mProgressStokeWidth = a.getDimensionPixelOffset(
                R.styleable.MaterialProgressBar_progress_stoke_width, (int) (STROKE_WIDTH_LARGE * density));
        mArrowWidth = a.getDimensionPixelOffset(
                R.styleable.MaterialProgressBar_arrow_width, -1);
        mArrowHeight = a.getDimensionPixelOffset(
                R.styleable.MaterialProgressBar_arrow_height, -1);
        mTextSize = a.getDimensionPixelOffset(
                R.styleable.MaterialProgressBar_progress_text_size, (int) (DEFAULT_TEXT_SIZE * density));
        int mTextColor = a.getColor(
                R.styleable.MaterialProgressBar_progress_text_color, Color.BLACK);

        mShowArrow = a.getBoolean(R.styleable.MaterialProgressBar_show_arrow, false);
        mCircleBackgroundEnabled = a.getBoolean(R.styleable.MaterialProgressBar_enable_circle_background, true);
        mProgress = a.getInt(R.styleable.MaterialProgressBar_progress, 0);
        mMax = a.getInt(R.styleable.MaterialProgressBar_max, 100);
        int textVisible = a.getInt(R.styleable.MaterialProgressBar_progress_text_visibility, 1);
        if (textVisible != 1) {
            mIfDrawText = true;
        }
        mShadowRadius = (int) (density * SHADOW_RADIUS);

        mTextPaint = new Paint();
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setAntiAlias(true);
        a.recycle();
        mProgressDrawable = new MaterialProgressDrawable(getContext(), this);
        this.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mDiameter = (int) density * DEFAULT_CIRCLE_DIAMETER;
        mBgCircle = new ShapeDrawable(new OvalShape());
        if (elevationSupported()) {
            mBgCircle = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, SHADOW_ELEVATION * density);
        } else {
            OvalShape oval = new OvalShadow(mShadowRadius, mDiameter);
            mBgCircle = new ShapeDrawable(oval);
        }
        super.setImageDrawable(mProgressDrawable);
    }


    private boolean elevationSupported() {
        return android.os.Build.VERSION.SDK_INT >= 21;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!elevationSupported()) {
            setMeasuredDimension(getMeasuredWidth() + mShadowRadius * 2, getMeasuredHeight()
                    + mShadowRadius * 2);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // }*/
        if (getBackground() == null && mCircleBackgroundEnabled) {
            final int shadowYOffset = (int) (density * Y_OFFSET);
            final int shadowXOffset = (int) (density * X_OFFSET);

            if (elevationSupported()) {
                ViewCompat.setElevation(this, SHADOW_ELEVATION * density);
            } else {
                ViewCompat.setLayerType(this, ViewCompat.LAYER_TYPE_SOFTWARE, mBgCircle.getPaint());
                mBgCircle.getPaint().setShadowLayer(mShadowRadius, shadowXOffset, shadowYOffset,
                        KEY_SHADOW_COLOR);
                setPadding(mShadowRadius, mShadowRadius, mShadowRadius, mShadowRadius);
            }
            mBgCircle.getPaint().setColor(mBackGroundColor);
            setBackground(mBgCircle);
        }
        mProgressDrawable.setBackgroundColor(mBackGroundColor);
        mProgressDrawable.setColorSchemeColors(mColors);
        mProgressDrawable.setSizeParameters(mDiameter, mDiameter,
                mInnerRadius <= 0 ? (mDiameter - mProgressStokeWidth * 2) / 4 : mInnerRadius,
                mProgressStokeWidth,
                mArrowWidth < 0 ? mProgressStokeWidth * 4 : mArrowWidth,
                mArrowHeight < 0 ? mProgressStokeWidth * 2 : mArrowHeight);
        if (isShowArrow()) {
            mProgressDrawable.setArrowScale(1f);
            mProgressDrawable.showArrow(true);
        }
        super.setImageDrawable(null);
        super.setImageDrawable(mProgressDrawable);
        mProgressDrawable.setAlpha(255);
        mProgressDrawable.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIfDrawText) {
            String text = String.format("%s%%", mProgress);
            int x = getWidth() / 2 - text.length() * mTextSize / 4;
            int y = getHeight() / 2 + mTextSize / 4;
            canvas.drawText(text, x, y, mTextPaint);
        }
    }

    @Override
    final public void setImageResource(int resId) {

    }


    private boolean isShowArrow() {
        return mShowArrow;
    }


    @Override
    final public void setImageURI(Uri uri) {
        super.setImageURI(uri);
    }

    @Override
    final public void setImageDrawable(Drawable drawable) {
    }

    @Override
    public void onAnimationStart() {
        super.onAnimationStart();
      /*  if (mListener != null) {
            mListener.onAnimationStart(getAnimation());
        }
   */
    }

    @Override
    public void onAnimationEnd() {
        super.onAnimationEnd();
     /*   if (mListener != null) {
            mListener.onAnimationEnd(getAnimation());
        }
  */
    }


    private void setColorSchemeResources(int... colorResIds) {
        final Resources res = getResources();
        int[] colorRes = new int[colorResIds.length];
        for (int i = 0; i < colorResIds.length; i++) {
            colorRes[i] = res.getColor(colorResIds[i]);
        }
        setColorSchemeColors(colorRes);
    }


    private void setColorSchemeColors(int... colors) {
        mColors = colors;
        if (mProgressDrawable != null) {
            mProgressDrawable.setColorSchemeColors(colors);
        }
    }

    /* public void setBackgroundColor(int colorRes) {
         if (getBackground() instanceof ShapeDrawable) {
           //  final Resources res = getResources();
             // ((ShapeDrawable) getBackground()).getPaint().setColor(res.getColor(colorRes));
         }
     }
 */
    private int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int progress) {
        if (getMax() > 0) {
            mProgress = progress;
        }
    }


    @Override
    public int getVisibility() {
        return super.getVisibility();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (mProgressDrawable != null) {
            if (visibility != VISIBLE) {
                mProgressDrawable.stop();
            }
            mProgressDrawable.setVisible(visibility == VISIBLE, false);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mProgressDrawable != null) {
            if (getVisibility() == VISIBLE)
                mProgressDrawable.start();
            else
                mProgressDrawable.stop();
            mProgressDrawable.setVisible(getVisibility() == VISIBLE, false);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mProgressDrawable != null) {
            mProgressDrawable.stop();
            mProgressDrawable.setVisible(false, false);
        }
    }


    private class OvalShadow extends OvalShape {
        private RadialGradient mRadialGradient;
        private int mShadowRadius;
        private Paint mShadowPaint;
        private int mCircleDiameter;

        public OvalShadow(int shadowRadius, int circleDiameter) {
            super();
            mShadowPaint = new Paint();
            mShadowRadius = shadowRadius;
            mCircleDiameter = circleDiameter;
            mRadialGradient = new RadialGradient(mCircleDiameter / 2, mCircleDiameter / 2,
                    mShadowRadius, new int[]{
                    FILL_SHADOW_COLOR, Color.TRANSPARENT
            }, null, Shader.TileMode.CLAMP);
            mShadowPaint.setShader(mRadialGradient);
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
            final int viewWidth = MaterialProgressBar.this.getWidth();
            final int viewHeight = MaterialProgressBar.this.getHeight();
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, (mCircleDiameter / 2 + mShadowRadius),
                    mShadowPaint);
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, (mCircleDiameter / 2), paint);
        }
    }
}
