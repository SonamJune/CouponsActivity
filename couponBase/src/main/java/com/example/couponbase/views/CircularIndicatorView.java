package com.example.couponbase.views;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couponbase.R;
import com.example.couponbase.util.BaseUtil;

/**
 * This custom view shows animating indicators against a supplied recycler view.
 * Recycler view unlike view pagers can show a lot of cards with more than one cards showing at a time.
 * This view handles all those cases along with showing an indefinite scrolling animation when
 * scrolling through the large list of items.
 */
public class CircularIndicatorView extends View {

    private static final int INDICATOR_TRANSLATION_DURATION = 150;

    @Nullable private Paint paint;

    /**
     * Height of the space the indicator takes up at the bottom of the view.
     */
    @Px private int indicatorHeight;
    /**
     * Each indicator width.
     */
    @Px private float indicatorLength;
    /**
     * Padding between each indicators.
     */
    @Px private float indicatorPadding;

    private int itemCount;
    private int currentLastVisibleItem;
    private int previousLastVisibleItem;
    private int previousLastVisibleItemForScrolling;
    private float translationX;
    private boolean isFirstItemFullyVisible;
    private boolean isLastItemFullyVisible;
    private boolean shouldShowSecondIndicatorState;
    private boolean shouldShowSecondLastIndicatorState;
    private boolean isRightScroll;
    private boolean shouldAllowAnimation;
    private boolean isAnimationPart1InProgress;
    private boolean isAnimationPart2InProgress;

    /**
     * This indicates each animating state of the indicators. Each state has different circle states.
     * The more the circleState number, the more is the perceived opacity for that indicator circle.
     * circleState = 4 symbolises the highlighted indicator circle.
     */
    private enum IndicatorState {
        // Center Highlighted. Applicable only for 5 items
        CENTER_HIGHLIGHTED(1, 2, 4, 2, 1),
        // First Highlighted
        FIRST_HIGHLIGHTED_FIVE_ITEMS(4, 2, 2, 1, 1),
        FIRST_HIGHLIGHTED_FOUR_ITEMS(4, 2, 2, 1, -1),
        FIRST_HIGHLIGHTED_THREE_ITEMS(4, 2, 2, -1, -1),
        FIRST_HIGHLIGHTED_UP_TO_TWO_ITEMS(4, -1, -1, -1, -1),
        // Second highlighted
        SECOND_HIGHLIGHTED_FIVE_ITEMS(2, 4, 2, 1, 1),
        SECOND_HIGHLIGHTED_FOUR_ITEMS(2, 4, 2, 1, -1),
        SECOND_HIGHLIGHTED_THREE_ITEMS(2, 4, 2, -1, -1),
        // Second last highlighted
        SECOND_LAST_HIGHLIGHTED_FIVE_ITEMS(1, 1, 2, 4, 2),
        SECOND_LAST_HIGHLIGHTED_FOUR_ITEMS(1, 2, 4, 2, -1),
        SECOND_LAST_HIGHLIGHTED_THREE_ITEMS(2, 4, 2, -1, -1),
        // Last highlighted
        LAST_HIGHLIGHTED_FIVE_ITEMS(1, 1, 2, 2, 4),
        LAST_HIGHLIGHTED_FOUR_ITEMS(1, 2, 2, 4, -1),
        LAST_HIGHLIGHTED_THREE_ITEMS(2, 2, 4, -1, -1),
        // Transient states. Applicable only for 5 items
        LEFT_HIGHLIGHTED_TRANSIENT(2, 4, 2, 1, 3),
        RIGHT_HIGHLIGHTED_TRANSIENT(3, 1, 2, 4, 2);

        public final int[] values;

        IndicatorState(int circle1State, int circle2State, int circle3State, int circle4State, int circle5State) {
            values = new int[]{circle1State, circle2State, circle3State, circle4State, circle5State};
        }
    }

    public CircularIndicatorView(@NonNull Context context) {
        super(context);
    }

    public CircularIndicatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularIndicatorView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setup(@NonNull RecyclerView recyclerView, @NonNull final LinearLayoutManager layoutManager) {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
        this.paint.setStrokeWidth(BaseUtil.convertDpToPx(getContext(), 1));
        this.indicatorHeight = BaseUtil.convertDpToPx(getContext(), 16);
        this.indicatorLength = BaseUtil.convertDpToPx(getContext(), 8);
        this.indicatorPadding = BaseUtil.convertDpToPx(getContext(), 4);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getAdapter() == null) {
                    return;
                }
                // Here we initialize variables and decide if view should be invalidated.
                itemCount = recyclerView.getAdapter().getItemCount();
                currentLastVisibleItem = layoutManager.findLastVisibleItemPosition();
                float currentLastVisiblePercentArea = getCurrentLastItemVisiblePercentArea(layoutManager);
                isFirstItemFullyVisible = layoutManager.findFirstCompletelyVisibleItemPosition() == 0;
                isLastItemFullyVisible = layoutManager.findLastCompletelyVisibleItemPosition() == itemCount -1;
                shouldShowSecondIndicatorState = currentLastVisibleItem == 2 && currentLastVisiblePercentArea >= 40;
                // Custom logic whether to show second indicator state for when item count is one of 3, 4 or 5.
                if (itemCount == 3 || itemCount == 4 || itemCount == 5) {
                    shouldShowSecondIndicatorState = currentLastVisibleItem == 2
                                                      && currentLastVisiblePercentArea >= 40
                                                      && currentLastVisiblePercentArea <=97;
                }

                int secondLastPosition = itemCount - 2;
                shouldShowSecondLastIndicatorState = (currentLastVisibleItem == secondLastPosition && currentLastVisiblePercentArea >= 40)
                                                     || (currentLastVisibleItem == secondLastPosition +1);
                // Custom logic whether to allow change of indicator state for when item count is one of 3, 4 or 5.
                boolean shouldShowSecondOrSecondLastIndicator = shouldShowSecondIndicatorState || shouldShowSecondLastIndicatorState;
                boolean shouldNotAllowIndicatorStateChange =
                        !((itemCount == 3 && currentLastVisibleItem == itemCount - 1 && shouldShowSecondOrSecondLastIndicator)
                        || ((itemCount == 4 || itemCount == 5) && currentLastVisibleItem != itemCount - 1 && shouldShowSecondOrSecondLastIndicator));

                if (isAnimationPart1InProgress
                    || isAnimationPart2InProgress
                    || (currentLastVisibleItem == previousLastVisibleItem
                        && !isFirstItemFullyVisible
                        && shouldNotAllowIndicatorStateChange)) {
                    return;
                }
                if (itemCount == 5 && currentLastVisibleItem == itemCount -2 && currentLastVisiblePercentArea < 97) {
                    return;
                }
                if (currentLastVisibleItem < previousLastVisibleItem
                    || currentLastVisiblePercentArea >= 40) {
                    // Calculate scroll direction.
                    // As the already provided dx value is too sensitive, we calculate scroll
                    // direction on our own using last known item positions.
                    isRightScroll = currentLastVisibleItem - previousLastVisibleItemForScrolling < 0;
                    previousLastVisibleItemForScrolling = currentLastVisibleItem;
                    // Here we update current and previous positions and also decide if animation should be allowed.
                    if (isFirstItemFullyVisible) {
                        currentLastVisibleItem = previousLastVisibleItem = 0;
                    } else if (isLastItemFullyVisible) {
                        currentLastVisibleItem = previousLastVisibleItem = itemCount -1;
                    } else if (shouldShowSecondIndicatorState) {
                        currentLastVisibleItem = previousLastVisibleItem = 1;
                    } else if (shouldShowSecondLastIndicatorState) {
                        currentLastVisibleItem = previousLastVisibleItem = secondLastPosition;
                    } else {
                        previousLastVisibleItem = currentLastVisibleItem;
                    }
                    isAnimationPart1InProgress = !isFirstItemFullyVisible
                                                  && !isLastItemFullyVisible
                                                  && !shouldShowSecondIndicatorState
                                                  && !shouldShowSecondLastIndicatorState;
                    invalidate();
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint == null) {
            return;
        }
        if (shouldAllowAnimation && isAnimationPart1InProgress) {
            // Draw 6 dots and highlight one ahead OR one behind the center.
            createIndicator(canvas, isRightScroll
                                    ? IndicatorState.LEFT_HIGHLIGHTED_TRANSIENT
                                    : IndicatorState.RIGHT_HIGHLIGHTED_TRANSIENT);
            post(new Runnable() {
                @Override
                public void run() {
                    CircularIndicatorView.this.animateHorizontalTranslation();
                }
            });
            return;
        } else if (isFirstItemFullyVisible) {
            shouldAllowAnimation = false;
            // Draw dots with first dot highlighted
            IndicatorState state;
            if (itemCount >= 5) {
                state = IndicatorState.FIRST_HIGHLIGHTED_FIVE_ITEMS;
            } else if (itemCount == 4) {
                state = IndicatorState.FIRST_HIGHLIGHTED_FOUR_ITEMS;
            } else if (itemCount == 3) {
                state = IndicatorState.FIRST_HIGHLIGHTED_THREE_ITEMS;
            } else {
                state = IndicatorState.FIRST_HIGHLIGHTED_UP_TO_TWO_ITEMS;
            }
            createIndicator(canvas, state);
        }  else if (isLastItemFullyVisible) {
            shouldAllowAnimation = false;
            // Draw dots with last dot highlighted
            IndicatorState state;
            if (itemCount >= 5) {
                state = IndicatorState.LAST_HIGHLIGHTED_FIVE_ITEMS;
            } else if (itemCount == 4) {
                state = IndicatorState.LAST_HIGHLIGHTED_FOUR_ITEMS;
            } else if (itemCount == 3) {
                state = IndicatorState.LAST_HIGHLIGHTED_THREE_ITEMS;
            } else {
                state = IndicatorState.FIRST_HIGHLIGHTED_UP_TO_TWO_ITEMS;
            }
            createIndicator(canvas, state);
        } else if (shouldShowSecondIndicatorState || shouldShowSecondLastIndicatorState) {
            shouldAllowAnimation = false;
            // Draw dots with second or second last dot highlighted
            IndicatorState state;
            if (itemCount >= 5) {
                state = shouldShowSecondIndicatorState
                        ? IndicatorState.SECOND_HIGHLIGHTED_FIVE_ITEMS
                        : IndicatorState.SECOND_LAST_HIGHLIGHTED_FIVE_ITEMS;
            } else if (itemCount == 4) {
                state = shouldShowSecondIndicatorState
                        ? IndicatorState.SECOND_HIGHLIGHTED_FOUR_ITEMS
                        : IndicatorState.SECOND_LAST_HIGHLIGHTED_FOUR_ITEMS;
            } else if (itemCount == 3) {
                state = shouldShowSecondIndicatorState
                        ? IndicatorState.SECOND_HIGHLIGHTED_THREE_ITEMS
                        : IndicatorState.SECOND_LAST_HIGHLIGHTED_THREE_ITEMS;
            } else {
                state = IndicatorState.FIRST_HIGHLIGHTED_UP_TO_TWO_ITEMS;
            }
            createIndicator(canvas, state);
        } else {
            shouldAllowAnimation = true;
            // Draw 5 dots with center dot highlighted
            createIndicator(canvas, IndicatorState.CENTER_HIGHLIGHTED);
        }
        isAnimationPart1InProgress = false;
        isAnimationPart2InProgress = false;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        // Set view size to long enough such that there are no issues when horizontally translating the view.
        setLeft(-100 * (int) getResources().getDimension(R.dimen._500dp));
        setRight(getMeasuredWidth() + (100 * (int) getResources().getDimension(R.dimen._500dp)));
    }

    private float getCurrentLastItemVisiblePercentArea(@NonNull LinearLayoutManager layoutManager) {
        View lastVisibleView = layoutManager.findViewByPosition(currentLastVisibleItem);
        if (lastVisibleView == null) {
            return 0;
        }
        int actualViewArea = lastVisibleView.getHeight() * lastVisibleView.getWidth();
        int visibleWidth = BaseUtil.getDeviceWidth() - lastVisibleView.getLeft();
        int visibleArea = visibleWidth > 0 ? (visibleWidth) * lastVisibleView.getHeight() : 0;
        return (float) visibleArea / actualViewArea * 100;
    }

    private void animateHorizontalTranslation() {
        float itemWidth = indicatorPadding + indicatorLength;
        final float translationX = this.translationX + (isRightScroll ? itemWidth : -itemWidth);
        animate().translationX(translationX)
                .setDuration(INDICATOR_TRANSLATION_DURATION)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation, boolean isReverse) {}

                    @Override
                    public void onAnimationStart(Animator animation) {}

                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        isAnimationPart1InProgress = false;
                        isAnimationPart2InProgress = true;
                        CircularIndicatorView.this.translationX = translationX;
                        invalidate();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        isAnimationPart1InProgress = false;
                        isAnimationPart2InProgress = true;
                        CircularIndicatorView.this.translationX = translationX;
                        invalidate();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {}

                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                }).start();
    }

    private void createIndicator(@NonNull Canvas canvas,
                                 @NonNull IndicatorState state) {
        // Horizontally center the indicator strip
        float stripWidthWithoutPadding = indicatorLength * Math.min(itemCount, 5);
        float totalPaddingBetweenIndicators = Math.max(0, Math.min(itemCount, 5) - 1) * indicatorPadding;
        float indicatorStripWidth = stripWidthWithoutPadding + totalPaddingBetweenIndicators;
        float viewWidth = getMeasuredWidth() + ((200) * (int) (getResources().getDimension(R.dimen._500dp)));
        float indicatorStripStartX = ((viewWidth - indicatorStripWidth) / 2F) - translationX;

        // Vertically center the indicator strip in the allotted space
        float indicatorStripVerticalCenterY = getMeasuredHeight() - indicatorHeight / 2F;
        // Calculate width of each indicator including padding
        float itemWidth = indicatorLength + indicatorPadding;

        // Draw the circle for every indicator
        float indicatorCenterX = indicatorStripStartX + (indicatorLength / 2);
        if (state == IndicatorState.LEFT_HIGHLIGHTED_TRANSIENT) {
            drawExtraTransientIndicator(canvas, indicatorCenterX - itemWidth, indicatorStripVerticalCenterY);
        }
        for (int value : state.values) {
            if (value == -1) {
                continue;
            }
            setPaint(value);
            canvas.drawCircle(indicatorCenterX,
                              indicatorStripVerticalCenterY,
                              indicatorLength / 2,
                              paint);
            indicatorCenterX += itemWidth;
        }
        if (state == IndicatorState.RIGHT_HIGHLIGHTED_TRANSIENT) {
            drawExtraTransientIndicator(canvas, indicatorCenterX, indicatorStripVerticalCenterY);
        }
    }

    private void drawExtraTransientIndicator(@NonNull Canvas canvas,
                                             float indicatorCenterX,
                                             float indicatorStripVerticalCenterY) {
        if (paint ==null) {
            return;
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(ContextCompat.getColor(getContext(), R.color.pager_indicator_stroke));
        paint.setAlpha((int) (0.08 * 255));
        canvas.drawCircle(indicatorCenterX,
                indicatorStripVerticalCenterY,
                indicatorLength / 2,
                paint);
    }

    private void setPaint(int value) {
        if (paint == null) {
            return;
        }
        switch (value) {
            case 1:
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(ContextCompat.getColor(getContext(), R.color.pager_indicator_stroke));
                paint.setAlpha(255);
                break;
            case 2:
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(ContextCompat.getColor(getContext(), R.color.pager_indicator_stroke));
                paint.setAlpha(255);
                break;
            case 3:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(ContextCompat.getColor(getContext(), R.color.pager_indicator_fill));
                paint.setAlpha((int) (0.08 * 255));
                break;
            case 4:
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(ContextCompat.getColor(getContext(), R.color.pager_indicator_fill));
                paint.setAlpha(255);
        }
    }
}
