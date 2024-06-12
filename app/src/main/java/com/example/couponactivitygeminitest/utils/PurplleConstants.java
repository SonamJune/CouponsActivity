package com.example.couponactivitygeminitest.utils;

public class PurplleConstants {

    // Try On
    public static final String TRY_ON_VIEW_ONE = "v1";
    public static final String TRY_ON_VIEW_TWO = "v2";
    public static final String TRY_ON_VIEW_THREE = "v3";
    public static final String TRY_ON_VIEW_FOUR = "v4";
    public static final String TRY_ON_VIEW_FIVE = "v5";
    public static final String PD_PAGE_TYPE = "pd";

    //card regex string
    public static final String VISA = "^4[0-9]{12}(?:[0-9]{3})?$";
    public static final String MASTER = "^5[1-5][0-9]{14}$";
    public static final String AMERICAN = "^3[47][0-9]{13}$";
    public static final String RUPAY = "^6[0-9]{15}$";

    public static final int ELITE = 1;
    public static final int NON_ELITE = 0;

    public static final String SIMILAR_PRODUCTS_TOOLTIP_PDP = "similar_products_pdp";
    public static final String SIMILAR_PROD_TOOLTIP = "similar_prod";

    public static boolean darkThemeEnabled = false;
    public static boolean calledViaDeeplink = false;
    public static boolean sessionIdChange = false;

    public static String BS_GUIDELINE_TAP_HOLD_GIF_URL = "https://media6.ppl-media.com/mediafiles/ecomm/promo/1594915531_tap-and-hold-2.gif";

    //Flutter Method Names
    public  static final String SHOW_LOCATION_TOOLTIP = "showLocationTooltip";
    public static final String LOCATION_LISTING_API_CALL = "listing";
    public static final String HOME = "home";
    //Simpl Payment
    public static final String PAYMENT_METHOD_SIMPL = "SIMPL";
    public static final String PAYMENT_METHOD_LAZYPAY = "LAZYPAY";

    //Brands
    public static String brandsReferrerPageType = "shop_home";
    public static String brandsReffererPageTypeValue = "default";
    public static boolean setReferrerValuesForBrandsPg = false;

    //Notification
    public static final String PP_TITLE = "pp_nt";
    public static final String PP_DEEPLINK = "pp_wzrk_dl";
    public static final String PP_MSG = "pp_nm";
    public static final String PP_BIG_IMG = "pp_wzrk_bp";
    public static final String PP_SUBTITLE = "pp_subtitle";
    public static final String PP_CHANNEL = "pp_channel";
    public static final String PP_TYPE  = "pp_type";
    public static final String PP_SOURCE = "pp_source";
    public static final String PP_CAMPAIGN = "pp_campaign";
    public static final String PP_MEDIUM = "pp_medium";
    public static final String PP_PRIORITY = "pp_priority";
    public static final String PP_SAVE = "pp_save";
    public static final String TRACKING_ID = "pp_wzrk_id";
    public static final String NAME = "name";
    public static final String IMPORTANCE = "importance";
    public static final String KEY = "key";
    public static final String DESCRIPTION = "description";
    public static final String PP_WZRK_ICON =  "pp_wzrk_icon";
    public static final String PP_ACTION_NOTIFICATION ="pp_action_notification";

    //Rich notification-Timer constants
    public static final String PT_ID = "pt_id";
    public static final String PT_TITLE_ALT = "pt_title_alt";
    public static final String PT_MSG_ALT = "pt_msg_alt";
    public static final String PT_BIG_IMG_ALT = "pt_big_img_alt";
    public static final String PT_BG = "pt_bg";
    public static final String PT_TIMER_THRESHOLD = "pt_timer_threshold";
    public static final String PT_TIMER_END = "pt_timer_end";
    public static final String PT_TITLE_COLOR = "pt_title_clr";
    public static final String PT_MSG_COLOR = "pt_msg_clr";
    public static final int ONE_SECOND = 1000;
    public static final String APP_UPDATE_TIME_FORMAT = "MM dd yyyy hh:mm:ss aaa";

    //Rich notification-carousel constants
    public static final String PT_IMAGE_LIST = "pt_carousel_images";
    public static final String PT_DEEPLINK_LIST =  "pt_carousel_deeplink";
    public static final String PT_MANUAL_CAROUSEL_TYPE = "pt_manual_carousel_type";
    public static final String PT_MANUAL_CAROUSEL_FILMSTRIP = "filmstrip";
    public static final String PT_MANUAL_CAROUSEL_CURRENT = "pt_manual_carousel_current";
    public static final String PT_RIGHT_SWIPE = "right_swipe";
    public static final String PT_MANUAL_CAROUSEL_FROM = "manual_carousel_from";
    public static final String PT_NOTIF_ID = "notificationId";
    public static final String PT_DISMISS_INTENT = "pt_dismiss_intent";
    public static final String PT_LAUNCH_INTENT = "pt_launch_intent";
    public static final String PT_IMG1 = "pt_img1";
    public static final String PT_IMG2 = "pt_img2";
    public static final String PT_IMG3 = "pt_img3";
    public static final String PT_IMG4 = "pt_img4";
    public static final String PT_IMG5 = "pt_img5";
    public static final String PT_DL1 =  "pt_dl1";
    public static final String PT_DL2 =  "pt_dl2";
    public static final String PT_DL3 =  "pt_dl3";
    public static final String PT_DL4 =  "pt_dl4";
    public static final String PT_DL5 =  "pt_dl5";

    //Google Deferred link
    public static final String GOOGLE_DEFERRED_LINK_PREFERENCE="google.analytics.deferred.deeplink.prefs";
    public static final String GOOGLE_DEFERRED_LINK="deeplink";
    public static final String DEEPLINK_SKIN_ANALYZER = "purplle.com://skinAnalyzer?gender=male";
    public static final String DEEPLINK_MY_ORDERS = "purplle.com://order";
    public static final String DEEPLINK_MY_WISHLIST = "purplle.com://myfavourites";
    public static final String DEEPLINK_MY_REVIEWS = "purplle.com://myreviews";
    public static final String DEEPLINK_PURPLLE_CREDIT = "purplle.com://promotion?url=https://www.purplle.com/profile/purpllecredit&&purplle_credit=1";
    public static final String DEEPLINK_DELETE_ACCOUNT = "purplle.com://delete_account";
    public static final String DEEPLINK_UPDATE = "purplle.com://update";
    public static final String DEEPLINK_PRIVACY_POLICY = "purplle.com://promotion?url=https://www.purplle.com/mb/pages/privacy-policy";
    public static final String DEEPLINK_BEAUTY_PROFILE_SANDBOX = "purplle.com://promotion?url=https://sandbox.purplle.com/wv/beautyquiz/63078f7593e68d78714cd9ee";
    public static final String DEEPLINK_BEAUTY_PROFILE_PRODUCTION = "purplle.com://promotion?url=https://www.purplle.com/wv/beautyquiz/62145676519057c4eb61cd51";
    public static final String DEEPLINK_BEAUTY_PROFILE_TEST = "purplle.com://promotion?url=https://test.purplle.com/wv/beautyquiz/62145676519057c4eb61cd51";
    public static final String DEEPLINK_NEW_ELITE_MEM_PAGE="purplle.com://promotion?url=https://www.purplle.com/mb/elitepage&is_elite_mem_page=1";

    public static final String WEBVIEW_RESOURCE="/wv-resources";

    public enum EntryType {
        HOME("Home"),CART("Cart"), PRODUCT("Product");
        public String value;

        EntryType(String value) {
            this.value = value;
        }
    }
}