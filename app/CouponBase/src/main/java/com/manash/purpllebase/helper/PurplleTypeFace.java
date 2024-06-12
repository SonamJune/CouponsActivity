package com.manash.purpllebase.helper;

import android.content.Context;
import android.graphics.Typeface;

import androidx.annotation.NonNull;

import com.manash.purpllebase.preference.PreferenceUtil;

public class PurplleTypeFace {
    private static Typeface mFontRegular;
    private static Typeface mFontMedium;
    private static Typeface mFontIcons;
    private static Typeface mFontSatisfy;
    private static Typeface mFontPtSerif;
    private static Typeface mCustomFont;
    private static Typeface mFontItalic;
    private static Typeface mFontBambusPro;
    private static Typeface mAqlimaItalic;
    private static Typeface mBodoniFlf;
    private static Typeface mAirBnbBook;
    private static Typeface mAirBnbLight;
    private static Typeface mAirBnbMedium;
    private static Typeface mAirBnbBold;
    private static Typeface mManropeBold;
    private static Typeface mManropeSemiBold;
    private static Typeface mManropeMedium;
    private static Typeface mManropeRegular;
    private static Typeface abrilBoldItalic;
    private static Typeface manropeRegularDefault;

    public static Typeface getFontRegular(Context context) {
        if (mFontRegular == null) {
            mFontRegular = Typeface.createFromAsset(context.getAssets(), "fonts/Aqleema-Regular.otf");
        }
        return mFontRegular;
    }

    public static Typeface getFontMedium(Context context) {
        if (mFontMedium == null) {
            mFontMedium = Typeface.createFromAsset(context.getAssets(), "fonts/Aqleema-Bold.otf");
        }
        return mFontMedium;
    }

    public static Typeface getFontSatisfy(Context context) {
        if (mFontSatisfy == null) {
            try {
                mFontSatisfy = Typeface.createFromFile(PreferenceUtil.getSatisfyFontPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mFontSatisfy;
    }

    public static Typeface getFontPtSerif(Context context) {
        if (mFontPtSerif == null) {
            try {
                mFontPtSerif = Typeface.createFromFile(PreferenceUtil.getPTSerifFontPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mFontPtSerif;
    }

    public static Typeface getFontIcons(Context context) {
        if (mFontIcons == null) {
            mFontIcons = Typeface.createFromAsset(context.getAssets(), "fonts/purplle-icon.ttf");
        }
        return mFontIcons;
    }


    public static Typeface getItalicFont(Context context) {
        if (mFontItalic == null) {
            try {
                mFontItalic = Typeface.createFromFile(PreferenceUtil.getItalicFontPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mFontItalic;
    }

    public static Typeface getAqlimaItalic(Context context) {
        if (mAqlimaItalic == null) {
            try {
                mAqlimaItalic = Typeface.createFromFile(PreferenceUtil.getAqlimaItalicPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mAqlimaItalic;
    }

    public static Typeface getBodoniFLFBoldPath(Context context) {
        if (mBodoniFlf == null) {
            try {
                mBodoniFlf = Typeface.createFromFile(PreferenceUtil.getBodoniFLFBoldPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mBodoniFlf;
    }

    public static Typeface getAirbnbBook(Context context) {
        if (mAirBnbBook == null) {
            try {
                mAirBnbBook = Typeface.createFromFile(PreferenceUtil.getAirBnbBookPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mAirBnbBook;
    }

    public static Typeface getAirbnbLight(Context context) {
        if (mAirBnbLight == null) {
            try {
                mAirBnbLight = Typeface.createFromFile(PreferenceUtil.getAirBnbLightPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mAirBnbLight;
    }

    public static Typeface getAirbnbMedium(Context context) {
        if (mAirBnbMedium == null) {
            try {
                mAirBnbMedium = Typeface.createFromFile(PreferenceUtil.getAirBnbMediumPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mAirBnbMedium;
    }

    public static Typeface getAirbnbBold(Context context) {
        if (mAirBnbBold == null) {
            try {
                mAirBnbBold = Typeface.createFromFile(PreferenceUtil.getAirBnbBoldPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mAirBnbBold;
    }

    public static Typeface getManropeBold(Context context) {
        if (mManropeBold == null) {
            try {
                mManropeBold = Typeface.createFromFile(PreferenceUtil.getManropeBoldPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mManropeBold;
    }

    public static Typeface getManropeSemiBold(Context context) {
        if (mManropeSemiBold == null) {
            try {
                mManropeSemiBold = Typeface.createFromFile(PreferenceUtil.getManropeSemiBoldPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mManropeSemiBold;
    }

    public static Typeface getManropeMedium(Context context) {
        if (mManropeMedium == null) {
            try {
                mManropeMedium = Typeface.createFromFile(PreferenceUtil.getManropeMediumPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mManropeMedium;
    }

    public static Typeface getManropeRegular(@NonNull Context context) {
        if (mManropeRegular == null) {
            try {
                mManropeRegular = Typeface.createFromFile(PreferenceUtil.getManropeRegularPath(context));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mManropeRegular;
    }

    public static Typeface getManropeRegularDefault(@NonNull Context context) {
        if (manropeRegularDefault == null) {
            try {
                manropeRegularDefault = Typeface.createFromAsset(context.getAssets(), "fonts/Manrope-Regular.ttf");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return manropeRegularDefault;
    }

    public static Typeface getAbrilItalicBold(@NonNull Context context) {
        if (abrilBoldItalic == null) {
            try {
                abrilBoldItalic = Typeface.createFromAsset(context.getAssets(), "fonts/abril_bolditalic.otf");
            } catch (Exception e) {
            }
        }
        return abrilBoldItalic;

    }

    public static Typeface getToolbarTypeface(Context context) {
        return getFontRegular(context);
    }

    public static Typeface getTabTypeface(Context context) {
        return getFontMedium(context);
    }
}