package com.manash.purpllebase.preference;

import android.content.Context;
import android.provider.Settings;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.manash.purpllebase.PurplleApplication;
import com.manash.purpllebase.R;
import com.manash.purpllebase.util.BaseConstants;
import com.manash.purpllebase.util.BaseUtil;

public class PreferenceUtil {

    public static String getCartCount(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.CART_COUNT, "0");
    }

    public static String getTooltipMessageObject(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.TOOLTIP_MESSAGE_OBJECT, "");
    }

    public static void saveTooltipMessageObject(Context context, String jsonObject) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.TOOLTIP_MESSAGE_OBJECT, jsonObject);
    }

    public static void setCartCount(Context context, String count) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.CART_COUNT, count);
    }

    public static String getAboutUrl(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.ABOUT_URL, null);
    }

    public static String getTnCUrl(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.TNC_URL, null);
    }

    public static String getSavedPostalCode(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.POSTAL_CODE, null);
    }

    public static String getToken(Context context) {
        String token = PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.TOKEN, "");
        if (token.trim().isEmpty()) {
            token = PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.TOKEN, "");
        }
        return token;
    }

    public static String getJWTToken(Context context) {
        return PurpllePrefManager.getInstance(context).ENCRYPTED.getEncryptedString(PurpllePrefKey.JWTTOKEN, "");

    }

    public static void saveJWToken(Context context, String token) {
        PurpllePrefManager.getInstance(context).ENCRYPTED.saveEncryptedString(PurpllePrefKey.JWTTOKEN, token);

//        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.JWTTOKEN, token);
    }

    public static void saveBeautyProfilePercent(Context context, int percentage) {
        PurpllePrefManager.getInstance(context).GLOBAL.putInt(PurpllePrefKey.BEAUTY_PROFILE_PERCENT, percentage);
    }


    public static int getBeautyProfilePercent(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getInt(PurpllePrefKey.BEAUTY_PROFILE_PERCENT, 0);
    }

    public static void setEnableBeautyQuizOnboarding(int enableBeautyQuiz) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putInt(PurpllePrefKey.ENABLE_BEAUTY_QUIZ_ONBOARDING, enableBeautyQuiz);
    }

    public static int isEnableBeautyQuizOnboarding() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getInt(PurpllePrefKey.ENABLE_BEAUTY_QUIZ_ONBOARDING, 0);
    }

    public static void saveChatAssistantCount(Context context, int count) {
        PurpllePrefManager.getInstance(context).GLOBAL.putInt(PurpllePrefKey.BEAUTY_ASSISTANT_COUNT, count);
    }

    public static int getChatAssistantCount(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getInt(PurpllePrefKey.BEAUTY_ASSISTANT_COUNT, 4);
    }


    public static boolean isShowNotification(Context context) {
        boolean isShowNotification = PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_SHOW_NOTIFICATION, true);
        if (isShowNotification) {
            isShowNotification = PurpllePrefManager.getInstance(context).GLOBAL.getBoolean("is_show_notification", true);
            PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.IS_SHOW_NOTIFICATION, isShowNotification);
            PurpllePrefManager.getInstance(context).GLOBAL.remove("is_show_notification");
        }
        return isShowNotification;
    }

    public static int getSampleOrderQty(Context context) {
        int sampleOrderQty = PurpllePrefManager.getInstance(context).GLOBAL.getInt(PurpllePrefKey.SAMPLE_ORDER_QTY, 0);
        if (sampleOrderQty == 0) {
            sampleOrderQty = PurpllePrefManager.getInstance(context).GLOBAL.getInt("sample_order_qty", 0);
            PurpllePrefManager.getInstance(context).GLOBAL.putInt(PurpllePrefKey.SAMPLE_ORDER_QTY, sampleOrderQty);
            PurpllePrefManager.getInstance(context).GLOBAL.remove("sample_order_qty");
        }
        return sampleOrderQty;
    }

    /*
      static method to get user login type i.e userType it should return
      one of following value : facebook,google,site_user,session
    */
    public static String getLoginType(Context context) {
        if (context == null) {
            return "";
        }
        String loginAs = PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_LOGIN_AS, "");
        String userType = "session";
        if (!loginAs.isEmpty()) {
            if (loginAs.equalsIgnoreCase("facebook")) {
                userType = "facebook";
            } else if (loginAs.equalsIgnoreCase("google-plus")) {
                userType = "google";
            } else {
                userType = "site_user";
            }
        }
        return userType;
    }

    public static String getUserId(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_ID, null);
    }

    public static String getReblazeHash() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.REBLAZE_HASH, "");
    }

    public static void setReblazeHash(String hash) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.REBLAZE_HASH, hash);
    }

    public static boolean isUserLogin(Context context) {
        return getUserId(context) != null;
    }

    public static String getUserName(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_NAME, "");
    }

    public static String getUserFirstName(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_FIRST_NAME, "");
    }

    public static String getUserLastName(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_LAST_NAME, "");
    }

    public static String getWalletBalance(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_WALLET_BALANCE, "");
    }

    public static String getGender(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.GENDER, "female");
    }

    public static String getFollowingCount(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_FOLLOWING_COUNT, "");
    }

    public static String getFollowerCount(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_FOLLOWER_COUNT, "");
    }

    public static String getReviewsCount(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_REVIEW_COUNT, "");
    }

    public static String getStoryCount(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_STORY_COUNT, "");
    }

    public static String getActivitiesCount(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_ACTIVITY_COUNT, "");
    }

    public static String getUserImage(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_IMAGE, "");
    }

    public static String getUserEmail(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_EMAIL, "");
    }

    public static String getUserPhone(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_PHONE, "");
    }

    public static String getUserDob(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_DOB, "");
    }

    public static boolean getEditDobFlag() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).USER.getBoolean(PurpllePrefKey.EDIT_DOB_FLAG, false);
    }

    public static void setUserDob(String dob) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).USER.putString(PurpllePrefKey.USER_DOB, dob);
    }

    public static void setEditDobFlag(boolean dobFlag) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).USER.putBoolean(PurpllePrefKey.EDIT_DOB_FLAG, dobFlag);
    }

    public static boolean isUserVerified(Context context) {
        return context != null && PurpllePrefManager.getInstance(context).USER.getBoolean(PurpllePrefKey.IS_USER_VERIFIED, false);
    }

    public static boolean isDefaultUserImage(Context context) {
        return context != null && PurpllePrefManager.getInstance(context).USER.getInt(PurpllePrefKey.IS_DEFAULT_USER_IMAGE, 0) == 1;
    }

    public static String getUserAddress(Context context) {
        if (context == null) return "";
        return PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.USER_ADDRESS, "");
    }

    public static String getAuthCode(Context context) {
        if (context == null) return "";
        String authCode = PurpllePrefManager.getInstance(context).USER.getString(PurpllePrefKey.AUTH_CODE, "");
        if (authCode.trim().isEmpty()) {
            authCode = PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.AUTH_CODE, "");
            PurpllePrefManager.getInstance(context).USER.putString(PurpllePrefKey.AUTH_CODE, authCode);
            PurpllePrefManager.getInstance(context).GLOBAL.remove(PurpllePrefKey.AUTH_CODE);
        }
        return authCode;
    }

    public static String getLatitude(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.LATITUDE, null);
    }

    public static String getLongitude(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.LONGITUDE, null);
    }

    public static String getCurrentAddress(Context context) {
        if (context == null) return "";
        String address = PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.DEVICE_ADDRESS, "");
        if (address == null) {
            address = "";
        }
        return address;
    }

    public static String getGAId(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.GA_ID, null);
    }

    public static String getBuildVariant(Context context) {
        if (context == null) return null;
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.BUILD_VARIANT, "debug");
    }

    public static void setBuildVariant(Context context, String buildVariant) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.BUILD_VARIANT, buildVariant);
    }

    /**
     * Return one of 0,1 or 2 :
     * 0 - Dev Pointed Build,
     * 1 - Sandbox Pointed Build,
     * 2 - Production Pointed Build
     */

    public static int getBuildSwitchState(Context context) {
        if (context == null) return 2;
        return PurpllePrefManager.getInstance(context).GLOBAL.getInt(PurpllePrefKey.BUILD_SWITCH_STATE, 1);
    }

    public static String getDeviceId(Context context) {
        if (context == null) return "";
        String deviceId = PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.DEVICE_ID, null);
        if (deviceId == null || deviceId.trim().isEmpty()) {
            deviceId = BaseUtil.getDeviceId(context);
            PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.DEVICE_ID, deviceId);
        }
        return deviceId;
    }

    public static String getCustomFontPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_FONT_FILE_PATH, null);
    }

    public static void putCustomFontPath(Context context, String key, String path) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(key, path);
    }

    public static String getItalicFontPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_ITALIC_FONT_PATH, null);
    }


    public static String getSatisfyFontPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_SATISFY_FONT_PATH, null);
    }

    public static String getPTSerifFontPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_PT_SERIF_FONT_PATH, null);
    }

    public static String getBodoniFLFBoldPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_BODONI_FLF_FONT_PATH, null);
    }

    public static String getAqlimaItalicPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_AQLIMA_ITALIC_FONT_PATH, null);
    }

    public static String getAirBnbBookPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_AIRBNB_CEREAL_BOOK_FONT_PATH, null);
    }

    public static String getAirBnbLightPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_AIRBNB_CEREAL_LIGHT_FONT_PATH, null);
    }

    public static String getAirBnbMediumPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_AIRBNB_CEREAL_MEDIUM_FONT_PATH, null);
    }

    public static String getAirBnbBoldPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_AIRBNB_CEREAL_BOLD_FONT_PATH, null);
    }

    public static String getManropeBoldPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_MANROPE_BOLD_FONT_PATH, null);
    }

    public static String getManropeSemiBoldPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_MANROPE_SEMI_BOLD_FONT_PATH, null);
    }

    public static String getManropeMediumPath(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_MANROPE_MEDIUM_FONT_PATH, null);
    }

    @Nullable
    public static String getManropeRegularPath(@NonNull Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(BaseConstants.CUSTOM_MANROPE_REGULAR_FONT_PATH, null);
    }

    public static String getSupportUrl(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.CUSTOMER_SUPPORT_URL, null);
    }

    public static void setIsShowSelfieTutorial(Context context, boolean value) {
        PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.IS_SHOW_SELFIE_TUTORIAL, value);
    }


    public static boolean getIsSelfieTutorial(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_SHOW_SELFIE_TUTORIAL, true);
    }

    public static void setIsShowComplexionTutorial(Context context, boolean value) {
        PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.IS_SHOW_COMPLEXION_TUTORIAL, value);
    }


    public static boolean getIsComplexionTutorial(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_SHOW_COMPLEXION_TUTORIAL, true);
    }

    public static void putFCMToken(Context context, String token) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.FCM_TOKEN, token);
    }

    public static String getFCMToken(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.FCM_TOKEN, null);
    }

    public static void setAppInstanceId(String appInstanceId) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.APP_INSTANCE_ID, appInstanceId);
    }

    public static String getAppInstanceId() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.APP_INSTANCE_ID, "");
    }

    public static Boolean isCT_TokenPushed(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_CT_TOKEN_PUSHED, false);
    }

    public static void setCT_TokenPushed(Context context, boolean value) {
        PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.IS_CT_TOKEN_PUSHED, value);
    }

    public static String getAndroidId(Context context) {
        String androidId = PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.ANDROID_ID, null);
        if (androidId == null) {
            androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.ANDROID_ID, androidId);
        }
        return androidId;
    }

    public static boolean getIsShowComplexionFinderLeftTip(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.LEFT_TIP, true);
    }

    public static boolean getIsShowComplexionFinderRightTip(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.RIGHT_TIP, true);
    }

    public static void setISHowComplextionFinderRightTip(Context context, boolean isShow) {
        PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.RIGHT_TIP, isShow);
    }

    public static void setISHowComplextionFinderLefTip(Context context, boolean isShow) {
        PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.LEFT_TIP, isShow);
    }

    public static boolean getIsNotificationJobIsAlreadySheduled(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_ALREADY_SCHEDULED, false);
    }

    public static void putIsNotificationJobIsAlreadySheduled(Context context) {
        PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.IS_ALREADY_SCHEDULED, true);
    }

    public static void setFirstCall(Context context, boolean isFirstCall) {
        PurpllePrefManager.getInstance(context).GLOBAL.putBoolean(PurpllePrefKey.IS_BOOT_RECEIVE, isFirstCall);
    }

    public static boolean isBootReceive(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_BOOT_RECEIVE, true);
    }

    public static void setIsElite(Context context, int isElite) {
        PurpllePrefManager.getInstance(context).USER.putInt(PurpllePrefKey.IS_ELITE, isElite);
    }

    public static int getIsElite(Context context) {
        return PurpllePrefManager.getInstance(context).USER.getInt(PurpllePrefKey.IS_ELITE, 0);
    }

    public static long getLastSessionTime(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getLong(PurpllePrefKey.SESSION_TIME, 0);
    }

    public static void setLastSessionTime(Context context, long sessionTime) {
        PurpllePrefManager.getInstance(context).GLOBAL.putLong(PurpllePrefKey.SESSION_TIME, sessionTime);
    }

    public static String getSessionId(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.SESSION_ID, null);
    }

    public static void setSessionId(Context context, String sessionId) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.SESSION_ID, sessionId);

    }

    public static boolean isFirstTimeAppLaunch(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_FIRST_ONBOARD, true);
    }

    public static boolean isFirstTimeAppLaunchNew(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_FIRST_ONBOARD_NEW, true);
    }

    public static boolean isShowAlertAppInstallFromPlayStore(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getBoolean(PurpllePrefKey.IS_SHOW_ALERT_APP_INSTALL_FROM_PLAY_STORE, true);
    }

    public static void saveProps(String props, Context context) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.PROPS, props);
    }

    public static String getProps(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.PROPS, null);

    }

    public static void saveUserInterest(String userInterest, Context context) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.USER_BS_INTEREST, userInterest);
    }

    public static String getUserInterest(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.USER_BS_INTEREST, "1");
    }

    public static void setHomePageData(Context context, String pageType, String pageTypeValue) {
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.SHOP_HOME_PAGE_TYPE, pageType);
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.SHOP_HOME_PAGE_TYPE_VALUE, pageTypeValue);
    }

    public static void resetHomePageData(Context context) {
        PurpllePrefManager.getInstance(context).GLOBAL.remove(PurpllePrefKey.SHOP_HOME_PAGE_TYPE);
        PurpllePrefManager.getInstance(context).GLOBAL.remove(PurpllePrefKey.SHOP_HOME_PAGE_TYPE_VALUE);
    }

    public static String getHomePageType(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.SHOP_HOME_PAGE_TYPE, "shop_home");
    }

    public static String getHomePageTypeValue(Context context) {
        return PurpllePrefManager.getInstance(context).GLOBAL.getString(PurpllePrefKey.SHOP_HOME_PAGE_TYPE_VALUE, "default");
    }

    public static void setBottomNavMenu(String navMenu) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.BOTTOM_NAV_MENU, navMenu);
    }

    public static String getBottomNavMenu() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.BOTTOM_NAV_MENU, "");
    }

    public static void setScreensInBottomNav(String deeplinks) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.BOTTOM_NAV_SCREENS, deeplinks);
    }

    public static String getScreensInBottomNav() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.BOTTOM_NAV_SCREENS, "");
    }

    public static String getBottomNavXId() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.BOTTOM_NAV_XID, PurplleApplication.getAppContext().getString(R.string.default_string));
    }

    public static String getForYouImage() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.FOR_YOU_IMAGE, "");
    }

    public static String getGratificationDeeplink() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.GRATIFICATION_DEEPLINK, PurplleApplication.getAppContext().getString(R.string.skin_analyzer_deeplink_default));
    }

    public static String getForYouAPIUrl() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.FOR_YOU_API_URL, PurplleApplication.getAppContext().getString(R.string.for_you_api_url_default));
    }

    public static boolean showVoiceMicTooltip() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getBoolean(PurpllePrefKey.VOICE_MIC_TOOLTIP, false);
    }

    public static boolean showForYouTooltip() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getBoolean(PurpllePrefKey.FOR_YOU_TOOLTIP, false);
    }

    public static String getTooltipBody(Context context) {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.FOR_YOU_TOOLTIP_BODY, context.getString(R.string.tooltip_body_default));
    }

    public static String getTooltipHeader(Context context) {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.FOR_YOU_TOOLTIP_HEADER, context.getString(R.string.tooltip_header_default));
    }

    public static String getToottipDuration() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.FOR_YOU_TOOLTIP_DURATION, "0");
    }

    public static void putJSPath(String key, String path) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(key, path);
    }

    public static String getJSPath(String path) {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(path, "");
    }

    public static void removeJSFile(String path) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.remove(path);
    }

    public static void putWebViewCacheRequired(boolean isRequired) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putBoolean(PurpllePrefKey.WEB_VIEW_CACHE_REQUIRED, isRequired);
    }

    public static boolean getWebViewCacheRequired() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getBoolean(PurpllePrefKey.WEB_VIEW_CACHE_REQUIRED, false);
    }

    public static void putWebCacheVersion(String version) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.WEB_RESOURCE_VERSION, version);
    }

    public static String getWebCacheVersion() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.WEB_RESOURCE_VERSION, "");
    }

    public static Boolean userVernacularEnabled() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getBoolean(PurpllePrefKey.USER_VERNACULAR_ELIGIBLE, false);
    }

    public static void setUserVernacularEnabled(Boolean enabled) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putBoolean(PurpllePrefKey.USER_VERNACULAR_ELIGIBLE, enabled);
    }

    public static Long getLastNotificationPermissionDisplayed() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getLong(PurpllePrefKey.NOTIFICATION_PERMISSION_LAST_DISPLAYED, -1);
    }

    public static void putLastNotificationPermissionDisplayed(long lastDisplayedTime) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putLong(PurpllePrefKey.NOTIFICATION_PERMISSION_LAST_DISPLAYED, lastDisplayedTime);
    }

    public static Boolean getShowVernacularToolTip() {
        //return true to display the vernacular tooltip for first time
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getBoolean(PurpllePrefKey.SHOW_VERNACULAR_TOOLTIP, true);
    }

    public static void setShowVernacularToolTip(Boolean showVernacularToolTip) {
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putBoolean(PurpllePrefKey.SHOW_VERNACULAR_TOOLTIP, showVernacularToolTip);
    }

    public static Boolean vernacularTranslateReviewEnabled() {
        return PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getBoolean(PurpllePrefKey.VERNACULAR_TRANSLATE_REVIEW_FLAG, false);
    }
    public static void setVernacularTranslateReviewEnabled(Boolean flag){
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putBoolean(PurpllePrefKey.VERNACULAR_TRANSLATE_REVIEW_FLAG,flag);
    }
}