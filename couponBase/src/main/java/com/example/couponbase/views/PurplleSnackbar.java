package com.example.couponbase.views;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.example.couponbase.PurplleApplication;
import com.example.couponbase.R;


public final class PurplleSnackbar extends BaseTransientBottomBar<PurplleSnackbar> {

    public static int TIME_SHORT = BaseTransientBottomBar.LENGTH_SHORT;
    public static int TIME_LONG = BaseTransientBottomBar.LENGTH_LONG;


    /**
     * Constructor for the transient bottom bar.
     *
     * @param parent              The parent for this transient bottom bar.
     * @param content             The content view for this transient bottom bar.
     * @param contentViewCallback The content view callback for this transient bottom bar.
     */
    protected PurplleSnackbar(@NonNull ViewGroup parent, @NonNull View content, @NonNull ContentViewCallback contentViewCallback) {
        super(parent, content, contentViewCallback);
    }

    private static class ContentViewCallback implements
            BaseTransientBottomBar.ContentViewCallback {

        // view inflated from custom layout
        private View content;

        public ContentViewCallback(View content) {
            this.content = content;
        }

        @Override
        public void animateContentIn(int delay, int duration) {
            // add custom *in animations for your views
            // e.g. original snackbar uses alpha animation, from 0 to 1
            //  ViewCompat.setScaleY(content, 0f);
            ViewCompat.animate(content)
                    .scaleY(1f).setDuration(duration)
                    .setStartDelay(delay);
        }

        @Override
        public void animateContentOut(int delay, int duration) {
            // add custom *out animations for your views
            // e.g. original snackbar uses alpha animation, from 1 to 0
            ViewCompat.setScaleY(content, 1f);
            ViewCompat.animate(content)
                    .scaleY(0f)
                    .setDuration(duration)
                    .setStartDelay(delay);
        }
    }

    public static PurplleSnackbar make(View view, @Duration int duration) {
        // inflate custom layout
        final ViewGroup parent = findSuitableParent(view);
        if (parent == null) {
            throw new IllegalArgumentException("No suitable parent found from the given view. "
                    + "Please provide a valid view.");
        }

        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View content = inflater.inflate(
                R.layout.snackbar_view, parent, false);
        final PurplleSnackbar snackbar = new PurplleSnackbar(parent, content, new ContentViewCallback(content));
        snackbar.setDuration(duration);
        return snackbar;
    }

    private static ViewGroup findSuitableParent(View view) {
        ViewGroup fallback = null;
        do {
            if (view instanceof CoordinatorLayout) {
                // We've found a CoordinatorLayout, use it
                return (ViewGroup) view;
            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    // If we've hit the decor content view, then we didn't find a CoL in the
                    // hierarchy, so use it.
                    return (ViewGroup) view;
                } else {
                    // It's not the content view but we'll use it as our fallback
                    fallback = (ViewGroup) view;
                }
            }

            if (view != null) {
                // Else, we will loop and crawl up the view hierarchy and try to find a parent
                final ViewParent parent = view.getParent();
                view = parent instanceof View ? (View) parent : null;
            }
        } while (view != null);

        // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
        return fallback;
    }


    // set text in custom layout
    public PurplleSnackbar setText(CharSequence text) {
        TextView textView = getView().findViewById(R.id.snackbar_text);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,PurplleApplication.getAppContext().getResources().getDimension(R.dimen._12ssp));
        return this;
    }

    // set action in custom layout
    public PurplleSnackbar setAction(CharSequence text, final View.OnClickListener listener) {
        TextView actionView = getView().findViewById(R.id.snackbar_action);
        actionView.setText(text);
        actionView.setTextSize(TypedValue.COMPLEX_UNIT_PX,PurplleApplication.getAppContext().getResources().getDimension(R.dimen._12ssp));
        actionView.setVisibility(View.VISIBLE);
        actionView.setOnClickListener(view -> {
            listener.onClick(view);
            // Now dismiss the Snackbar
            dismiss();
        });
        return this;
    }
}