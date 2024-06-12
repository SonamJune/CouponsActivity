package com.example.couponbase.util;

import android.content.Context;

import com.example.couponbase.preference.PreferenceUtil;

public class BaseConstants {

    public static final String GOOGLE_DEVELOPER_KEY = "AIzaSyD1h6YY52hheDXBC7vEPjyh86JSXDbUwtE";
    public static final String GOOGLE_API_KEY = "AIzaSyDgBk3lX5wqqIU8p5yFE_JGkh-iufOUQqc";

    public static final String CUSTOM_FONT_FILE_PATH = "https://media5.ppl-media.com/mediafiles/ecomm/misc/1501224718_phosphateinline.ttf";
    public static final String CUSTOM_ITALIC_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/mobile/1503136158_caveat-regular.ttf";
    public static final String CUSTOM_SATISFY_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/misc/1536316950_satisfy-regular.ttf";
    public static final String CUSTOM_PT_SERIF_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1563792758_ptserif-bolditalic.ttf";
    public static final String CUSTOM_BODONI_FLF_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1573796555_bodoniflf-bold.ttf";
    public static final String CUSTOM_AQLIMA_ITALIC_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1573796522_aqleema-italic.otf";
    public static final String CUSTOM_AIRBNB_CEREAL_BOOK_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1591769929_airbnbcerealbook.ttf";
    public static final String CUSTOM_AIRBNB_CEREAL_LIGHT_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1591769931_airbnbcereallight.ttf";
    public static final String CUSTOM_AIRBNB_CEREAL_MEDIUM_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1591769932_airbnbcerealmedium.ttf";
    public static final String CUSTOM_AIRBNB_CEREAL_BOLD_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1591682557_airbnbcerealbold.ttf";
    public static final String CUSTOM_MANROPE_SEMI_BOLD_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1619618152_manrope-semibold.ttf";
    public static final String CUSTOM_MANROPE_MEDIUM_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1619618134_manrope-medium.ttf";
    public static final String CUSTOM_MANROPE_BOLD_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1619618123_manrope-bold.ttf";
    public static final String CUSTOM_MANROPE_EXTRA_BOLD_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1619783193_manrope-extrabold.ttf";
    public static final String CUSTOM_MANROPE_REGULAR_FONT_PATH = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1619618145_manrope-regular.ttf";

    public static final String APP_UPDATE_ANIMATION = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1697449599_app-update-ppl.json";

    private static final String BASE_URL_DEV = "https://test.purplle.com";
    private static final String BASE_URL_SANDBOX = "https://www.purplle.com";
    private static final String BASE_URL_PROD = "https://www.purplle.com";
    public static final String API = "api/";
    public static final String MERCHANT_ID_PROD = "purplle.com";
    public static final String MERCHANT_ID_SANDBOX = "sandbox_purplle_com";
    public static final String PAYMENT_SERVICE = "in.juspay.hyperapi";
    public static final String PRIVACY_POLICY = "https://www.purplle.com/pr/privacy-policy";
    public static final String TERMS_CONDITION = "https://www.purplle.com/pr/terms-and-condition";


    public static String getBaseUrl(Context context) {
        if (PreferenceUtil.getBuildVariant(context) != null && PreferenceUtil.getBuildVariant(context).equalsIgnoreCase("release")) {
            return BASE_URL_PROD;
        } else {
            int buildType = PreferenceUtil.getBuildSwitchState(context);
            if (buildType == 0) {
                return BASE_URL_DEV;
            } else if (buildType == 1) {
                return BASE_URL_SANDBOX;
            } else {
                return BASE_URL_PROD;
            }
        }
    }
}