package com.example.couponactivitygeminitest.utils;

public class APIEndPoints {

    //API VERSION
    public static final String V2 = "v2/";

    //module
    public static final String COMMON = "common/";
    public static final String TOKEN = "tokens/";
    public static final String USERS = "users/";
    public static final String ACCOUNT = "account/";
    public static final String NEO = "neo/";
    public static final String NEO_CART = "neo/cart/";
    public static final String NEO_RECO = "neo/reco/";
    public static final String AUTHORIZATION = "authorization/";
    public static final String USER = "user/";
    public static final String SHOP = "shop/";
    public static final String BOOKING = "bookings/";
    public static final String SOCIAL = "social/";
    public static final String BOT = "bot/";
    //Sub module
    public static final String FEED = "feed/";
    public static final String VENUE = "venue/";
    public static final String BEAUTY_PROFILE = "beautyprofile/";
    public static final String DISCOVER = "discover/";

    //Generate token End points
    public static final String GENERATE_TOKEN = "/neo/auth/get/v1";
    public static final String DELETE_TOKEN = "/neo/auth/delete/v1";
    public static final String UPDATE_TOKEN = "/neo/auth/update/v1";

    public static final String LOGIN = "login";
    public static final String LOGIN_V2 = "v2/login";
    public static final String USER_REGISTER = "/api/account/authorization/user_registration";
    public static final String USER_REGISTER_V2 = "v2/user_registration";
    public static final String ORDERS = "orders";
    public static final String PROFILE = "public_profile";
    public static final String ORDER_DETAILS = "orderdetail";
    public static final String FOLLOW = "followers";
    public static final String FOLLOWING = "followings";
    public static final String FAVORITES = "favorites";
    public static final String COMBINED = "combined";
    public static final String WISH_LIST = "wishlist";
    public static final String SOCIAL_SHARE = "socialshare";
    public static final String USER_FOLLOW = "userfollow";
    public static final String WALLET_PASSBOOK = "walletpassbook";
    public static final String ABOUT_ME = "aboutme";
    public static final String ITEMS = "neo/merch/listing";
    public static final String ITEM_DETAILS = "itemdetails";
    public static final String GET_CATEGORIES = "categories";
    public static final String CART = "cart";
    public static final String CART_PRICE_DROP = "/neo/cart/price-drop";
    public static final String ADD_TO_CART = "add-to-cart";
    public static final String GO_NARI_TRANSLATE = "/neo/catalog/translate";
    public static final String REMOVE_FROM_CART = "remove-item";
    public static final String BRANDS = "brands";
    public static final String SEARCH = "suggestions";
    public static final String HOME = "home";
    public static final String OFFER = "offer";
    public static final String FORGOT_PASSWORD = "forgot_password";
    public static final String REFER_EARN = "refer";
    public static final String FILTERS_V3 = "filtersv3";
    public static final String FILTERS_V2 = "filters/v2";

    public static final String PRODUCT_REVIEW = "newreviews";
    public static final String GET_PAYMENT_OPTIONS = "paymentoptions";
    public static final String GET_PAYMENT_OPTIONS_V2 = "payment_options_v2";
    public static final String CARD_VALIDATIONS = "card_validations";
    public static final String CREATE_ORDER = "createorder";
    public static final String COUPON = "coupon/apply-coupon";
    public static final String ADD_REVIEW = "submitreview";
    public static final String DELETE_CARD = "deletecard";
    public static final String DELETE_UPI = "remove_upi";
    public static final String CANCEL_ORDER = "cancelorder";
    public static final String SOCIAL_LOGIN = "social_login";
    public static final String GENERATE_OTP = "generateotp";
    public static final String VERIFY_OTP = "verify_otp";
    public static final String VERIFY_OTP_V2 = "v2/verify_otp";
    public static final String ONBOARDING_SCREEN_DATA = "onboarding_screen_data";
    public static final String UPDATE_PROFILE = "updateprofile";
    public static final String STORIES = "stories";
    public static final String STORY_DETAIL = "storydetail";
    public static final String COLOR_RECOMMEND = "color_recommend_story";
    public static final String HASH_TAGS = "hashtags";
    public static final String CREATE_STORY = "createstory";
    public static final String ACTIVITY_STORIES = "all";
    public static final String USER_STORIES = "userstories";
    public static final String PRODUCTS = "products";
    public static final String UPLOAD_IMAGE = "api/common/users/uploadimage";
    public static final String SKIN_ANALYSER = "beauty-profile/revieve/analyzeImage";
    public static final String WEB_VIEW = "webView";
    public static final String MY_REVIEWS = "userreviews";
    public static final String CHANGE_PASSWORD = "changepassword";
    public static final String NOTIFY_OUT_OF_STOCK = "notifyme";
    public static final String WRITE_REVIEW = "writeareview";
    public static final String QUIZ_QUESTION = "questions";
    public static final String QUIZ_ANSWER = "answer";
    public static final String PRODUCT_SELLER = "product_seller";
    public static final String ORDER_RETURN = "orderreturnitems";
    public static final String ORDER_RETURN_CONFIRM = "orderreturnconfirm";
    public static final String THREAD_CATEGORIES = "threadcategories";
    public static final String THREADS = "threads";
    public static final String THREAD_DETAIL = "thread_detail";
    public static final String CONFIG = "config";
    public static final String MEGA_MENU = "megamenu";
    public static final String RECO_BY_TYPE = "reco_by_type";
    public static final String PRODUCT_Q_A = "neo/catalog/qna";                     //          "productqna";
    public static final String ADD_QUESTION = "addquestion";
    public static final String LISTING = "listing";
    public static final String NOTIFICATION = "notifications";
    public static final String READ_NOTIFICATION = "read_notification";
    public static final String ORDER_FEEDBACK = "feedback";
    public static final String FIND_MY_FIT = "findmyfit";
    public static final String ORDER_TACK = "ordertrack";
    public static final String PRODUCT_STORIES = "module_stories";
    public static final String PRODUCT_FEEDBACK = "submit_info_feedback";
    public static final String PERSONA = "persona";
    public static final String NEW_USER_REVIEW = "newuserreview";
    public static final String DELETE_REVIEW = "deletereview";
    public static final String DEEP_LINK_BY_TEXT = "deeplinkbytxt";
    public static final String HEADER_MENU = "header_menu";
    public static final String PRELIMINARY_STATE = "preliminarystate";
    public static final String COUPONS = "coupon/listing-coupon";
    public static final String USER_PERSONALIZED_PRODUCT = "user_personalized_products";
    public static final String VARIANT_ITEMS = "variants_items";
    public static final String VARIANT = "/neo/catalog/variants/";
    public static final String PRODUCT_INFO = "/neo/catalog/productInfo/";
    public static final String SAVED_PAYMENT_DETAILS = "saved_paymentdetails";
    public static final String DE_LINK_WALLET = "delink_wallet";
    public static final String PRODUCT_GALLERY = "product/gallery";
    public static final String RECO_PRODUCT = "neo/reco/product";
    public static final String PRODUCT_DESCRIPTION = "neo/catalog/description";     //          "product/description";
    public static final String BASIC_INFO = "neo/catalog/basicinfo";                //          "product/basicinfo";
    public static final String ELITE_MSG = "neo/catalog/pdpstrip";                //          "product/basicinfo";
    public static final String PRODUCT_PRICE = "neo/catalog/retrieveprice";         //          "product/price";
    public static final String PRODUCT_REVIEW_QNA = "neo/catalog/reviews";          //          "product/reviews";
    public static final String PRODUCT_CUSTOMER_REVIEW_IMAGES = "/neo/catalog/reviewimages";
    public static final String NEO_MERCH = "neo/merch/";
    public static final String SORT_OPTIONS = "filters/sortOptions";
    public static final String SKIN_SHADES = "bot/skin_shade";

    public static final String API_PENDING_NOTIFICATION_POLLING = "api_pending_notification_polling";

    public static final String CHECK_USER_V2 = "v2/check_user";
    public static final String CHECK_USER = "check_user";
    public static final String SENT_OTP_V2 = "v2/send_otp";
    public static final String SEND_OTP = "send_otp";
    public static final String SOCIAL_REGISTER = "social_register";
    public static final String RESET_PASSWORD = "reset_password";
    public static final String GET_SERVICE_V2 = "v2/pincode-check";

    //booking
    public static final String BOOKING_GET_PAYMENT = "payment_options";
    public static final String BOOKING_CRETE_ORDER = "saveApp";
    public static final String BOOKING_CART_DETAIL = "getCartDetails";
    public static final String BOOKING_FAVORITE_SALONS = "my_favourite";
    public static final String BOOKING_ADD_TO_FAV = "add_to_fav";
    public static final String BOOKING_VENUE_SHARE = "venue_share";
    public static final String PINCODE_CHECK_APP = "pincode-check-app";
    public static final String GET_BOOKING_CONFIRMATION = "bookings/bookingConfirmation";

    public static final String GET_SAVED_ADDRESSES = "userAddresses";
    public static final String GET_VIDEO_DETAILS_REALTIME_DATA = "/api/social/discover/videodetail_data";
    public static final String GET_VIDEO_DETAILS = "/api/social/discover/videodetail";
    public static final String GET_VIDEO_RECOMMENDATION = "/api/pages/widget";
    public static final String GET_VIDEO_LISTING = "/api/pages/details";
    public static final String GET_BS_INTEREST = "/api/account/user/bs_interest";
    public static final String SAVE_USER_INTEREST = "/api/account/user/save_user_interest";
    public static final String DELETE_ADDRESS = "deleteAddress";
    public static final String GET_POSTAL_CODE = "v2/pincode-info";
    public static final String SAVE_ADDRESS = "saveAddress";
    public static final String GET_PERSON_LIST = "person";
    public static final String ADD_PERSON = "addPerson";
    public static final String DELETE_PERSON = "deletePerson";
    public static final String UPDATE_PHONE_NUMBER = "update_phone_number";
    public static final String CREATE_WALLET = "create_wallet";
    public static final String CREATE_WALLET_V2 = "create_wallet_v2";
    public static final String LINK_WALLET = "link_wallet";
    public static final String LINK_WALLET_V2 = "link_wallet_v2";
    public static final String CAMPAIGN_TRACK = "campaign_track_source";

    public static final String ESTIMATED_DELIVERY = "get-edd-of-cart";
    public static final String SKIN_ANALYZER_DEEPLINK = "skinAnalyzerDeeplinks";
    public static final String NEO_UTIL = "/neo/utils/";

    public static final String VIDEO_COM_FOLLOW = "/api/common/users/follow";
    public static final String VIDEO_COM_UNFOLLOW = "/api/common/users/unfollow";
    public static final String VIDEO_COM_LIKE = "/api/common/users/like";
    public static final String VIDEO_COM_UNLIKE = "/api/common/users/unlike";
    public static final String RECO_GET = "/reco/get";

    public static final String UPI_VALIDATION = "/api/shop/upi_validation";
    public static final String ADD_FREEBIE_TO_CART_V2 = "/api/v2/shop/addfreebietocart";
    public static final String ADD_FREEBIE_TO_CART = "/api/shop/addfreebietocart";
    public static final String ADD_REMOVE_COUPON = "/neo/coupon/apply-coupon";

    public static final String BOTTOM_NAV_MENU = "/api/v2/shop/bottom_navigation_v2";

    public static final String MULTI_ADD_TO_CART = "/neo/cart/multi-add-to-cart";
    public static final String UPSELL_FREEBIE = "api/v2/shop/getoffers_Onid?upsell=true";

    public static final String ALL_LANGUAGES = "/neo/translate/language/all-languages";
    public static final String ADD_USER_PREFERRED_LANGUAGE = "/neo/translate/language/add-user-preference";
    public static final String GET_USER_PREFERRED_LANGUAGE = "/neo/translate/language/user-preference";
}