package com.manash.purpllebase.views;

import android.view.ViewGroup;

/**
 * Interface for providing common API for observable and scrollable widgets.
 */
public interface Scrollable {
    @Deprecated
    void setScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void addScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void removeScrollViewCallbacks(ObservableScrollViewCallbacks listener);

    void clearScrollViewCallbacks();

    void scrollVerticallyTo(int y);

    int getCurrentScrollY();

    void setTouchInterceptionViewGroup(ViewGroup viewGroup);
}
