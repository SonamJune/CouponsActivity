package com.manash.purpllebase.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;

import com.manash.purpllebase.R;
import com.manash.purpllebase.helper.PurplleTypeFace;
import com.manash.purpllebase.util.BaseUtil;

/**
 * Custom view to show the present time in a timer like UI. To make the timer tick, use the
 * method #setTime() and pass in the new time in 1 second intervals.
 */
public class TimerView extends PurplleTextView {

    @NonNull private final Paint paint;
    @NonNull private final RectF rect;
    @NonNull private final Rect textBounds;
    @NonNull private final Typeface squareTextTypeface;

    @Nullable private String[] time;

    /**
     * Width of each ticker square.
     */
    @Px private final int squareWidth;

    /**
     * Height of each ticker square.
     */
    @Px private final int squareHeight;

    /**
     * length between 2 squares.
     */
    @Px private final int squareGap;

    /**
     * length between square and colon.
     */
    @Px private final int squareColonGap;

    /**
     * Diameter of each colon circles
     */
    @Px private final int colonDiameter;

    /**
     * Width of each square border
     */
    @Px private final float squareBorderWidth;

    /**
     * Radius of corners of each square
     */
    @Px private final int squareCornerRadius;

    public TimerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.squareWidth = BaseUtil.convertDpToPx(getContext(), 14);
        this.squareHeight = BaseUtil.convertDpToPx(getContext(), 16);
        this.squareGap = BaseUtil.convertDpToPx(getContext(), 2);
        this.squareColonGap = BaseUtil.convertDpToPx(getContext(), 3);
        this.colonDiameter = BaseUtil.convertDpToPx(getContext(), 2);
        this.squareBorderWidth = 0.4f * Resources.getSystem().getDisplayMetrics().density;
        this.squareCornerRadius = BaseUtil.convertDpToPx(getContext(), 2);
        int timeTextSize = BaseUtil.convertDpToPx(getContext(), 13);

        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeCap(Paint.Cap.SQUARE);
        this.paint.setTextSize(timeTextSize);
        this.squareTextTypeface = Typeface.create(PurplleTypeFace.getFontMedium(context), Typeface.BOLD);
        this.rect = new RectF();
        this.textBounds = new Rect();
    }

    /**
     * Sets the present time. For making the timer tick, pass in the new time in 1 second intervals.
     * The time in hh:mm:ss format is used where hh, mm, ss are separately passed.
     *
     * @param hour The present hour in hh (2 digit) format
     * @param min  The present minutes in mm (2 digit) format
     * @param sec  The present seconds in ss (2 digit) format
     */
    public void setTime(@NonNull String hour, @NonNull String min, @NonNull String sec) {
        time = new String[]{
                ((Character) (hour.charAt(0))).toString(),
                ((Character) (hour.charAt(1))).toString(),
                ((Character) (min.charAt(0))).toString(),
                ((Character) (min.charAt(1))).toString(),
                ((Character) (sec.charAt(0))).toString(),
                ((Character) (sec.charAt(1))).toString()};
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (time == null) {
            return;
        }
        float startX = 0;
        // We will create the squares and colons backwards i.e make the last (6th) square first and move backwards.
        int squareCount = 6;
        while (squareCount > 0) {
            switch (squareCount) {
                case 6:
                    startX = getMeasuredWidth() - squareWidth - squareBorderWidth;
                    drawSquare(canvas, startX);
                    drawSquareText(canvas, squareCount);
                    break;
                case 5:
                case 3:
                    startX = startX - (squareWidth + squareGap);
                    drawSquare(canvas, startX);
                    drawSquareText(canvas, squareCount);
                    startX = startX - (squareColonGap + (colonDiameter / 2.0f));
                    drawColon(canvas, startX);
                    break;
                case 4:
                case 2:
                    startX = startX - (squareColonGap + (colonDiameter / 2.0f) + squareWidth);
                    drawSquare(canvas, startX);
                    drawSquareText(canvas, squareCount);
                    break;
                case 1:
                    startX = startX - (squareWidth + squareGap);
                    drawSquare(canvas, startX);
                    drawSquareText(canvas, squareCount);
                    break;
            }
            squareCount--;
        }
    }

    private void drawSquare(@NonNull Canvas canvas, @FloatRange(from = 0) float startX) {
        // Draw square's filled background
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.time_ticker_background));
        int rectBottomY = (getMeasuredHeight() / 2) + (squareHeight / 2);
        rect.set(startX, rectBottomY - squareHeight, startX + squareWidth, rectBottomY);
        canvas.drawRoundRect(rect, squareCornerRadius, squareCornerRadius, paint);
        // Draw square border
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(squareBorderWidth);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.time_ticker_border));
        canvas.drawRoundRect(rect, squareCornerRadius, squareCornerRadius, paint);
    }

    private void drawSquareText(@NonNull Canvas canvas, @IntRange(from = 1, to = 6) int count) {
        if (time == null || count >= 7) {
            return;
        }
        // Setup paint
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.time_ticker_text_color));
        paint.setTypeface(squareTextTypeface);
        paint.getTextBounds(time[count - 1], 0, time[count - 1].length(), textBounds);

        int textWidth = textBounds.width();
        int textHeight = textBounds.height();
        float textOriginX = rect.centerX() - (textWidth / 2.0f) - (squareBorderWidth / 2.0f);
        textOriginX -= time[count - 1].equals("0") ? (squareBorderWidth / 2.0f) : 0f;
        float textOriginY = rect.centerY() + (textHeight / 2.0f);
        canvas.drawText(time[count - 1], textOriginX, textOriginY, paint);
    }

    private void drawColon(@NonNull Canvas canvas, @FloatRange(from = 0) float startX) {
        // Setup paint
        paint.setStyle(Paint.Style.FILL);
        paint.setTypeface(Typeface.DEFAULT);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.time_ticker_border));

        float colonCenterY = (getMeasuredHeight() / 2.0f) - (rect.height() / 8.0f);
        canvas.drawCircle(startX, colonCenterY, colonDiameter / 2.0f, paint);
        colonCenterY = (getMeasuredHeight() / 2.0f) + (rect.height() / 8.0f);
        canvas.drawCircle(startX, colonCenterY, colonDiameter / 2.0f, paint);
    }
}
