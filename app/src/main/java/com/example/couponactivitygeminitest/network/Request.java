package com.example.couponactivitygeminitest.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.couponbase.R;
import com.example.couponactivitygeminitest.callback.CommonVolleyCallback;
import com.example.couponactivitygeminitest.model.error.ErrorResponse;
import com.example.couponactivitygeminitest.model.token.TokenResponse;
import com.example.couponactivitygeminitest.network.volley.CustomRequest;
import com.example.couponactivitygeminitest.network.volley.MySingleton;
import com.example.couponactivitygeminitest.utils.APIEndPoints;
import com.example.couponactivitygeminitest.utils.Network;
import com.example.couponactivitygeminitest.utils.Util;
import com.example.couponactivitygeminitest.utils.VolleyRequestType;
import com.example.couponbase.util.localization.LocaleManager;
import com.google.gson.Gson;
//import com.manash.analytics.AnalyticsHelper;
//import com.manash.analytics.aws.AwsKinesisHelper;
//import com.manash.analytics.google_analytics.GoogleAnalyticsHelper;
import com.example.couponbase.PurplleApplication;
import com.example.couponbase.helper.PurplleTrace;
import com.example.couponbase.model.EventBusMessage;
import com.example.couponbase.preference.PreferenceUtil;
import com.example.couponbase.preference.PurpllePrefKey;
import com.example.couponbase.preference.PurpllePrefManager;
import com.example.couponbase.util.BaseConstants;
import com.example.couponbase.util.BaseUtil;
import com.example.couponbase.util.DialogListener;
//import com.example.couponbase.util.localization.LocaleManager;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class Request {
    private static final String TAG = "Request";
    private static boolean isDialogShown;

    public static <T> void postRequest(final Context context, HashMap<String, String> hashMap, final T requestType, final CommonVolleyCallback commonVolleyCallback, boolean appendBaseUrl) {
        String type;
        if (requestType instanceof String) {
            type = (String) requestType;
        } else {
            type = ((VolleyRequestType) requestType).getType();
        }

        final String url;
        if (appendBaseUrl) {
            url = getUrlByType(context, type) + type;
        } else {
            url = type;
        }

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                parseError(context.getApplicationContext(), volleyError, commonVolleyCallback, requestType, url, null);
            }
        };

        Response.Listener sucessListener = new Response.Listener() {
            @Override
            public void onResponse(Object response) {

            }
        };

        PurplleTrace.i(TAG, "postRequest: " + url);

        CustomRequest postRequest = new CustomRequest(context.getApplicationContext(), com.android.volley.Request.Method.POST, url, hashMap, commonVolleyCallback, errorListener, requestType, null);
        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(postRequest);
    }

    public static <T> void postRequest(final Context context, HashMap<String, String> hashMap, final T requestType, final CommonVolleyCallback commonVolleyCallback) {
        postRequest(context, hashMap, requestType, commonVolleyCallback, true);
    }

    public static <T> void getRequest(final Context context, HashMap<String, String> hashMap, final T type, final CommonVolleyCallback commonVolleyCallback) {
        getRequest(context, hashMap, type, null, commonVolleyCallback);
    }

    //Todo
    public static <T> void getRequestWithTag(final Context context, HashMap<String, String> hashMap, final T type, final CommonVolleyCallback commonVolleyCallback, final Object tag) {
        getRequest(context, hashMap, type, tag, commonVolleyCallback);
    }

    public static <T> void getRequest(final Context context, HashMap<String, String> hashMap, final T request, final Object requestTag, final CommonVolleyCallback commonVolleyCallback) {
        String url;
        final String type;
        if (request instanceof String) {
            type = (String) request;
        } else {
            type = ((VolleyRequestType) request).getType();
        }

        if (type.equalsIgnoreCase(APIEndPoints.GET_BOOKING_CONFIRMATION)) {
            url = BaseConstants.getBaseUrl(context) + "/" + BaseConstants.API + type;
        } else {
            url = getUrlByType(context, type) + type;
        }

        String vernacularAbFlag = PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.VERNACULAR_AB_TESTING, null);

        if (hashMap != null) {
            hashMap.put(context.getString(R.string.lang_code), LocaleManager.INSTANCE.getLanguagePref(context));
            if (vernacularAbFlag != null && !vernacularAbFlag.isEmpty()) {
                hashMap.put(context.getString(R.string.vernacular_ab_experiment), vernacularAbFlag);
            }
        }


        if (hashMap != null && !type.equalsIgnoreCase(APIEndPoints.PINCODE_CHECK_APP)) {

            StringBuilder builder = new StringBuilder();
            for (String key : hashMap.keySet()) {
                Object value = hashMap.get(key);
                if (value != null) {
                    try {
                        value = URLEncoder.encode(String.valueOf(value), "UTF-8");
                        if (builder.length() > 0) {
                            builder.append("&");
                        }
                        builder.append(key).append("=").append(value);
                    } catch (UnsupportedEncodingException e) {
                        //GoogleAnalyticsHelper.pushCatchException(e, context);
                    }
                }
            }
            url += "?" + builder.toString();
        } else if (hashMap != null) {
            if (hashMap.get(context.getString(R.string.postal_code_key)) != null) {
                url += "/" + hashMap.get(context.getString(R.string.postal_code_key));
            }
            if (hashMap.get(context.getString(R.string.product_id)) != null) {
                url += "/" + hashMap.get(context.getString(R.string.product_id));
            }
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            if (type.contains("?")) {
                stringBuilder.append("&").append(context.getString(R.string.lang_code) + "=").append(LocaleManager.INSTANCE.getLanguagePref(context));
            } else {
                stringBuilder.append("?").append(context.getString(R.string.lang_code) + "=").append(LocaleManager.INSTANCE.getLanguagePref(context));
            }

            if (vernacularAbFlag != null && !vernacularAbFlag.isEmpty()) {
                /*check if the url contains the vernacular_ab_experiment flag,
                as this is already present in listing aync urls
                * */
                if (!url.contains(context.getString(R.string.vernacular_ab_experiment))) {
                    stringBuilder.append("&").append(context.getString(R.string.vernacular_ab_experiment) + "=").append(vernacularAbFlag);
                }
            }


            url += stringBuilder.toString();

        }

        PurplleTrace.i(TAG, "getRequest: " + url);

        final String requestUrl = url;

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                PurplleTrace.e(TAG, "getRequest onVolleyError type----" + type + " --->" + volleyError.getMessage());
                parseError(context.getApplicationContext(), volleyError, commonVolleyCallback, request, requestUrl, requestTag);
            }
        };

        CustomRequest<T> getRequest = new CustomRequest<T>(context, com.android.volley.Request.Method.GET, url, hashMap, commonVolleyCallback, errorListener, request, requestTag);

        if (type.equalsIgnoreCase(APIEndPoints.CART)) {
            getRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 0, 1f));
        }

        if (requestTag != null && !(type.equalsIgnoreCase(APIEndPoints.SEARCH))) {
            getRequest.setTag(requestTag);
        }
        MySingleton.getInstance(context.getApplicationContext()).addToRequestQueue(getRequest);

    }

    private static <T> void parseError(final Context context, VolleyError volleyError, CommonVolleyCallback commonVolleyCallback, T type, String requestUrl, Object requestTag) {
        if (commonVolleyCallback == null) return;
        String jsonString = null;
        if (volleyError != null && volleyError.networkResponse != null) {
            PurplleTrace.e(TAG, "onVolleyError " + volleyError.toString());
            NetworkResponse networkResponse = volleyError.networkResponse;
            try {
                jsonString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
                ErrorResponse errorResponse = new Gson().fromJson(jsonString, ErrorResponse.class);
                checkForInvalidToken(context, networkResponse.statusCode, errorResponse);

                String errorMessage;
                if (errorResponse != null && errorResponse.getMessage() != null && !errorResponse.getMessage().trim().isEmpty()) {
                    errorMessage = errorResponse.getMessage();
                } else {
                    errorMessage = Network.getStatusCodeMessage(networkResponse.statusCode, context);
                }

                commonVolleyCallback.onVolleyError(jsonString, errorMessage, networkResponse.statusCode, type, errorResponse.getModuleType(), requestTag);

                //GoogleAnalyticsHelper.pushCatchException(requestUrl + ":" + networkResponse.statusCode + ":" + errorMessage, context);


            } catch (Exception e) {
                commonVolleyCallback.onVolleyError(jsonString, Network.getStatusCodeMessage(networkResponse.statusCode, context), networkResponse.statusCode, type, null, requestTag);
                //GoogleAnalyticsHelper.pushCatchException(e, context);
            }
        } else {
            String moduleType = null;
            String response = null;
            String message;
            try {
                if(volleyError != null) {
                    message = volleyError.getMessage();
                    response = volleyError.toString();

                    if (volleyError.toString().equals(PurplleApplication.getAppContext().getString(R.string.volley_timeouterror_format))) {
                        moduleType = PurplleApplication.getAppContext().getString(R.string.timeout_error_str);
                    }
                } else {
                    message = PurplleApplication.getAppContext().getString(R.string.volley_error_null);
                }
            } catch (Exception e) {
                message = PurplleApplication.getAppContext().getString(R.string.volley_excep_not_handled) + ": " +e.getMessage();
            }
            commonVolleyCallback.onVolleyError(response, message, 0, type, moduleType, requestTag);
        }
    }

    private static void checkForInvalidToken(final Context context, int statusCode, ErrorResponse errorResponse) {
        if (statusCode == Network.INVALID_TOKEN && errorResponse != null && errorResponse.getAction() != null && errorResponse.getAction().equalsIgnoreCase("update")) {
            tokenUpdate(context);

        } else if (statusCode == Network.INVALID_TOKEN) {
            isDialogShown = true;
            tokenExpiredLogOutAndExit(context);
        }
    }

    public static void tokenUpdate(Context context) {
        Request.postRequest(context, null, APIEndPoints.UPDATE_TOKEN, new CommonVolleyCallback() {
            @Override
            public void onVolleySuccess(Object obj, Object type) {
                if (obj != null) {
                    TokenResponse tokenResponse = new Gson().fromJson(obj.toString(), TokenResponse.class);
                    PurplleTrace.i("Token", "token repsone " + tokenResponse.getToken());
                    if (tokenResponse != null) {
                        PreferenceUtil.saveJWToken(context, tokenResponse.getToken());
//                        EventBusMessage messageType = new EventBusMessage(EventBusMessage.MessageType.TOKEN_UPDATE);
//                        EventBus.getDefault().post(messageType);
                    }
                }
            }

            @Override
            public void onVolleyError(String response, String msg, int statusCode, Object type, String moduleType, Object tag) {
                PurplleTrace.i("Token", "token error");
                sendActivityResponseEvent(moduleType, msg, response, statusCode, APIEndPoints.UPDATE_TOKEN);
            }
        });
    }


    public static void tokenExpiredLogOutAndExit(Context context) {
        if (context instanceof Activity) {
            BaseUtil.showAlertDialog(context, 1, context.getString(R.string.you_will_be_logged_out), context.getString(R.string.you_are_not_authorized), new DialogListener() {
                @Override
                public void onAction(int action) {
                    logOutAndExit(context);
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.you_will_be_logged_out) + " ..." + context.getString(R.string.you_are_not_authorized), Toast.LENGTH_LONG).show();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    logOutAndExit(context);
                }
            }, 1500);

        }
    }

    private static void logOutAndExit(Context context) {
        isDialogShown = false;
        Util.logoutUser(context, "forced_logout");
//        Util.fblogout();
        PreferenceUtil.saveJWToken(context, "");
//        Intent intent = new Intent(context.getApplicationContext(), SplashActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//        context.getApplicationContext().startActivity(intent);
//        System.exit(0);
    }

    private static void sendActivityResponseEvent(String moduleType, String msg, String response, int statusCode, String request) {
        String pageType = PurplleApplication.getInstance().getmCurrentPage() != null? PurplleApplication.getInstance().getmCurrentPage() : PurplleApplication.getAppContext().getString(R.string.default_str);
        String pageTypeValue = PurplleApplication.getInstance().getmCurrentPageTypeValue() != null? PurplleApplication.getInstance().getmCurrentPageTypeValue() : PurplleApplication.getAppContext().getString(R.string.default_str);

//        AwsKinesisHelper.saveEventRecord(PurplleApplication.getAppContext(), AwsKinesisHelper.ACTIVITY_RESPONSE,
//                AnalyticsHelper.getApplicationErrorData(pageType,
//                        pageTypeValue, "", 0,
//                        PurplleApplication.getAppContext().getString(R.string.page), request, moduleType, msg, new HashMap<>(), response, statusCode));
    }

    private static String getUrlByType(Context context, String requestType) {
        String baseUrl = BaseConstants.getBaseUrl(context) + "/" + BaseConstants.API;
        switch (requestType) {
            case APIEndPoints.SEARCH:
            case APIEndPoints.FILTERS_V2:
            case APIEndPoints.SORT_OPTIONS:
                baseUrl = BaseConstants.getBaseUrl(context) + "/" + APIEndPoints.NEO_MERCH;
                break;
            case APIEndPoints.ITEMS:
                baseUrl = BaseConstants.getBaseUrl(context) + "/";
                break;
            case APIEndPoints.CHECK_USER:
            case APIEndPoints.SEND_OTP:
            case APIEndPoints.SOCIAL_REGISTER:
            case APIEndPoints.SOCIAL_LOGIN:
            case APIEndPoints.LOGIN:
            case APIEndPoints.FORGOT_PASSWORD:
            case APIEndPoints.RESET_PASSWORD:
            case APIEndPoints.VERIFY_OTP:
                baseUrl += APIEndPoints.ACCOUNT + APIEndPoints.AUTHORIZATION;
                break;

            case APIEndPoints.CHECK_USER_V2:
            case APIEndPoints.SENT_OTP_V2:
            case APIEndPoints.LOGIN_V2:
            case APIEndPoints.VERIFY_OTP_V2:
            case APIEndPoints.USER_REGISTER_V2:
            case APIEndPoints.ONBOARDING_SCREEN_DATA:
                baseUrl = BaseConstants.getBaseUrl(context) + "/" + APIEndPoints.NEO + APIEndPoints.USER + APIEndPoints.AUTHORIZATION;
                break;
            case APIEndPoints.COUPONS:
            case APIEndPoints.COUPON:
                baseUrl = BaseConstants.getBaseUrl(context) + "/" + APIEndPoints.NEO;
                break;
            case APIEndPoints.PRODUCT_GALLERY:
                break;
            case APIEndPoints.PRODUCT_DESCRIPTION:
            case APIEndPoints.BASIC_INFO:
            case APIEndPoints.ELITE_MSG:
            case APIEndPoints.PRODUCT_PRICE:
            case APIEndPoints.PRODUCT_REVIEW_QNA:
            case APIEndPoints.PRODUCT_Q_A:
            case APIEndPoints.RECO_PRODUCT:
                baseUrl = BaseConstants.getBaseUrl(context) + "/";
                break;
            case APIEndPoints.CONFIG:
            case APIEndPoints.ORDER_FEEDBACK:
            case APIEndPoints.PRELIMINARY_STATE:
                baseUrl += APIEndPoints.COMMON;
                break;
            case APIEndPoints.PROFILE:
            case APIEndPoints.ORDERS:
            case APIEndPoints.ORDER_DETAILS:
            case APIEndPoints.WISH_LIST:
            case APIEndPoints.SOCIAL_SHARE:
            case APIEndPoints.USER_FOLLOW:
            case APIEndPoints.ABOUT_ME:
            case APIEndPoints.REFER_EARN:
            case APIEndPoints.CANCEL_ORDER:
            case APIEndPoints.DELETE_CARD:
            case APIEndPoints.GENERATE_OTP:
            case APIEndPoints.UPDATE_PROFILE:
            case APIEndPoints.MY_REVIEWS:
            case APIEndPoints.CHANGE_PASSWORD:
            case APIEndPoints.ORDER_RETURN:
            case APIEndPoints.ORDER_RETURN_CONFIRM:
            case APIEndPoints.WALLET_PASSBOOK:
            case APIEndPoints.ORDER_TACK:
            case APIEndPoints.NEW_USER_REVIEW:
            case APIEndPoints.DELETE_REVIEW:
            case APIEndPoints.DELETE_UPI:
                baseUrl += APIEndPoints.ACCOUNT + APIEndPoints.USER;
                break;
            case APIEndPoints.FOLLOW:
            case APIEndPoints.FAVORITES:
            case APIEndPoints.FOLLOWING:
            case APIEndPoints.ACTIVITY_STORIES:
            case APIEndPoints.USER_STORIES:
                baseUrl += APIEndPoints.COMMON + APIEndPoints.USERS + APIEndPoints.FEED;
                break;
            case APIEndPoints.ITEM_DETAILS:
            case APIEndPoints.GET_CATEGORIES:
            case APIEndPoints.CART:
            case APIEndPoints.GET_PAYMENT_OPTIONS:
            case APIEndPoints.CREATE_ORDER:
            case APIEndPoints.PRODUCT_REVIEW:
            case APIEndPoints.ADD_REVIEW:
            case APIEndPoints.ADD_QUESTION:
            case APIEndPoints.NOTIFY_OUT_OF_STOCK:
            case APIEndPoints.NOTIFICATION:
            case APIEndPoints.WRITE_REVIEW:
            case APIEndPoints.READ_NOTIFICATION:
            case APIEndPoints.PRODUCT_SELLER:
            case APIEndPoints.PRODUCT_FEEDBACK:
            case APIEndPoints.USER_PERSONALIZED_PRODUCT:
            case APIEndPoints.CREATE_WALLET:
            case APIEndPoints.CREATE_WALLET_V2:
            case APIEndPoints.UPDATE_PHONE_NUMBER:
            case APIEndPoints.LINK_WALLET:
            case APIEndPoints.LINK_WALLET_V2:
            case APIEndPoints.SAVED_PAYMENT_DETAILS:
            case APIEndPoints.DE_LINK_WALLET:
            case APIEndPoints.GET_PAYMENT_OPTIONS_V2:
            case APIEndPoints.CARD_VALIDATIONS:
                baseUrl += APIEndPoints.SHOP;
                break;
            case APIEndPoints.ESTIMATED_DELIVERY:
            case APIEndPoints.PINCODE_CHECK_APP:
            case APIEndPoints.GET_SERVICE_V2:
            case APIEndPoints.REMOVE_FROM_CART:
            case APIEndPoints.ADD_TO_CART:
            case APIEndPoints.GET_POSTAL_CODE:
                baseUrl = BaseConstants.getBaseUrl(context) + "/" + APIEndPoints.NEO_CART;
                break;
            case APIEndPoints.COMBINED:
            case APIEndPoints.LISTING:
                baseUrl = BaseConstants.getBaseUrl(context) + "/" + APIEndPoints.NEO_RECO;
                break;
            case APIEndPoints.STORIES:
            case APIEndPoints.HASH_TAGS:
            case APIEndPoints.CREATE_STORY:
            case APIEndPoints.PRODUCTS:
            case APIEndPoints.GET_SAVED_ADDRESSES:
            case APIEndPoints.DELETE_ADDRESS:
            case APIEndPoints.SAVE_ADDRESS:
                baseUrl += APIEndPoints.COMMON + APIEndPoints.USERS;
                break;
            case APIEndPoints.BOOKING_GET_PAYMENT:
            case APIEndPoints.BOOKING_CRETE_ORDER:
            case APIEndPoints.BOOKING_CART_DETAIL:
            case APIEndPoints.GET_PERSON_LIST:
            case APIEndPoints.ADD_PERSON:
            case APIEndPoints.DELETE_PERSON:
                baseUrl += APIEndPoints.BOOKING;
                break;
            case APIEndPoints.BOOKING_FAVORITE_SALONS:
                baseUrl += APIEndPoints.BOOKING;
                break;
            case APIEndPoints.BOOKING_VENUE_SHARE:
            case APIEndPoints.BOOKING_ADD_TO_FAV:
                baseUrl += APIEndPoints.BOOKING + APIEndPoints.VENUE;
                break;
            case APIEndPoints.QUIZ_QUESTION:
            case APIEndPoints.QUIZ_ANSWER:
                baseUrl += APIEndPoints.SOCIAL + APIEndPoints.BEAUTY_PROFILE;
                break;
            case APIEndPoints.THREAD_CATEGORIES:
            case APIEndPoints.THREADS:
            case APIEndPoints.FIND_MY_FIT:
            case APIEndPoints.PRODUCT_STORIES:
            case APIEndPoints.PERSONA:
                baseUrl += APIEndPoints.SOCIAL + APIEndPoints.DISCOVER;
                break;
            case APIEndPoints.THREAD_DETAIL:
                baseUrl += APIEndPoints.V2 + APIEndPoints.SOCIAL + APIEndPoints.DISCOVER;
                break;
            case APIEndPoints.BRANDS:
            case APIEndPoints.HOME:
            case APIEndPoints.OFFER:
            case APIEndPoints.HEADER_MENU:
            case APIEndPoints.MEGA_MENU:
            case APIEndPoints.STORY_DETAIL:
            case APIEndPoints.COLOR_RECOMMEND:
            case APIEndPoints.RECO_BY_TYPE:
            case APIEndPoints.VARIANT_ITEMS:
            case APIEndPoints.CAMPAIGN_TRACK:
                baseUrl += APIEndPoints.V2 + APIEndPoints.SHOP;
                break;
            case APIEndPoints.DEEP_LINK_BY_TEXT:
                baseUrl += APIEndPoints.BOT;
                break;
            case APIEndPoints.API_PENDING_NOTIFICATION_POLLING:

                baseUrl = PreferenceUtil.getBuildVariant(context).equalsIgnoreCase("release") ? "https://us-central1-datapipelineproduction.cloudfunctions.net/" : "https://us-central1-datapipelinedev.cloudfunctions.net/";
                break;
            case APIEndPoints.SKIN_ANALYZER_DEEPLINK:
                baseUrl = BaseConstants.getBaseUrl(context) + APIEndPoints.NEO_UTIL;
                break;
            default:
                baseUrl = BaseConstants.getBaseUrl(context);
                break;
        }
        return baseUrl;
    }


}
