package com.manash.purpllebase.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import com.manash.purpllebase.R;
import com.manash.purpllebase.helper.PurplleTypeFace;

public class PurplleEditText extends AppCompatEditText {

    public PurplleEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode()) {
            return;
        }

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.PurplleTextView);
        String fontName = styledAttrs.getString(R.styleable.PurplleTextView_purplle_typeface);
        styledAttrs.recycle();

        if (fontName != null) {
            switch (fontName) {
                case "regular":
                    setTypeface(PurplleTypeFace.getFontRegular(context));
                    break;
                case "medium":
                    setTypeface(PurplleTypeFace.getFontMedium(context));
                    break;
                case "icon":
                    setTypeface(PurplleTypeFace.getFontIcons(context));
                    break;
                case "airbnb_book":
                    setTypeface(PurplleTypeFace.getAirbnbBook(context));
                    break;
                case "airbnb_light":
                    setTypeface(PurplleTypeFace.getAirbnbLight(context));
                    break;
                case "airbnb_medium":
                    setTypeface(PurplleTypeFace.getAirbnbMedium(context));
                    break;
                case "airbnb_bold":
                    setTypeface(PurplleTypeFace.getAirbnbBold(context));
                    break;
            }
        }
    }

}