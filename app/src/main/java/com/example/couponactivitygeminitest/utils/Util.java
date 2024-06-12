package com.example.couponactivitygeminitest.utils;

//import static com.example.couponbase.PurplleApplication.mLastSessionTime;

import static com.example.couponbase.PurplleApplication.mLastSessionTime;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Vibrator;
import android.text.InputFilter;
import android.text.format.DateUtils;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.couponactivitygeminitest.R;
import com.example.couponbase.PurplleApplication;
import com.example.couponbase.helper.PurplleTrace;
import com.example.couponbase.helper.UserDataSingleton;
import com.example.couponbase.preference.PreferenceUtil;
import com.example.couponbase.preference.PurpllePrefKey;
import com.example.couponbase.preference.PurpllePrefManager;
import com.example.couponbase.util.BaseUtil;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

//import io.branch.referral.Branch;

public class Util {

    private static final String CART = "cart";
    private static String abExperiment;
    private static final String PDP_OLD_VERSION = "a";
    private static final int VIBRATION_DURATION = 320;
    private static final int SESSION_TIME_OUT = 30;
    //private static ArrayList<Integer> mSkeltonBackgroundColor = getSkeltonBackgroundColor();

    public static void setupActionBar(AppCompatActivity appCompatActivity) {
        Toolbar toolbar = appCompatActivity.findViewById(R.id.tool_bar_generic);
        appCompatActivity.setSupportActionBar(toolbar);
    }

    public static void setActionBarTitle(AppCompatActivity appCompatActivity, String toolbarTitle) {
        if (appCompatActivity.getSupportActionBar() != null) {
            appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        TextView title = appCompatActivity.findViewById(com.example.couponbase.R.id.toolbar_title);
        title.setText(toolbarTitle);
        title.setHorizontallyScrolling(true);
        title.setSelected(true);
    }

//    public static void setStatusBarColor(Activity activity) {
//        setStatusBarColor(activity, activity.getResources().getColor(R.color.status_bar_color));
//    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

//    public static void showNetworkErrorScreen(Context context, LinearLayout networkErrorContainer, String message, final String type, final OnRetryClick click) {
//        if (context == null) {
//            return;
//        }
//        View errorLayout;
//        if (PurplleConstants.darkThemeEnabled && (context instanceof VideoRecommendationsActivity || context instanceof BSVideoListActivity)) {
//            errorLayout = LayoutInflater.from(context).inflate(R.layout.network_error_dark, networkErrorContainer, false);
//        } else {
//            errorLayout = LayoutInflater.from(context).inflate(R.layout.network_error, networkErrorContainer, false);
//        }
//        if (type.equalsIgnoreCase(CART)) {
//            errorLayout = LayoutInflater.from(context).inflate(R.layout.network_error_blush, networkErrorContainer, false);
//        }
//
//        TextView messageView = errorLayout.findViewById(R.id.no_network_message);
//        ImageView imageView = errorLayout.findViewById(R.id.image);
//        TextView button = errorLayout.findViewById(R.id.retry_button);
//        button.setTypeface(PurplleTypeFace.getFontMedium(context));
////        if (message != null) {
////            if (message.equalsIgnoreCase(context.getString(R.string.network_failure_msg))) {
////                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.common_error_icon));
////                Network.registerNetworkReceiver(button, context);
////            } else if (message.equalsIgnoreCase(context.getString(R.string.no_videos_error_msg))) {
////                if (PurplleConstants.darkThemeEnabled && (context instanceof VideoRecommendationsActivity || context instanceof BSVideoListActivity)) {
////                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.empty_result_img_dark));
////                } else {
////                    imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.empty_result_icon));
////                }
////                button.setVisibility(View.GONE);
////            } else if (message.equalsIgnoreCase(context.getString(R.string.no_items_msg)) || message.equalsIgnoreCase(context.getString(R.string.no_products_error_msg)) || message.equalsIgnoreCase(context.getString(R.string.no_salon_error_msg)) || message.equalsIgnoreCase(context.getString(R.string.no_stories_error_msg)) || message.equalsIgnoreCase(context.getString(R.string.no_followers_error_msg)) || message.equalsIgnoreCase(context.getString(R.string.no_address_error_msg)) || message.equalsIgnoreCase(context.getString(R.string.no_person_error_msg))) {
////                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.empty_result_icon));
////                button.setVisibility(View.GONE);
////            } else if (type.equalsIgnoreCase(APIEndPoints.ORDERS)) {
////                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.empty_result_icon));
////                button.setText(context.getString(R.string.buy_now));
////                button.setVisibility(View.GONE);
////            } else if (message.equalsIgnoreCase(context.getString(R.string.no_orders_msg))) {
////                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.empty_result_icon));
////                button.setText(context.getString(R.string.buy_now));
////            } else if (message.equalsIgnoreCase(context.getString(R.string.empty_result_message))) {
////                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.empty_result_icon));
////                button.setText(context.getString(R.string.go_back));
////            } else if (!Network.isConnected(context)) {
////                Network.registerNetworkReceiver(button, context);
////            } else if (type.equalsIgnoreCase(APIEndPoints.PRODUCT_SELLER)) {
////                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.empty_result_icon));
////                button.setText(context.getString(R.string.go_back));
////            } else if (type.equalsIgnoreCase(context.getString(R.string.no_reviews))) {
////                imageView.setImageResource(R.drawable.no_reviews);
////                button.setVisibility(View.GONE);
////            } else if (type.equalsIgnoreCase(AnalyticsHelper.PAGE_SPLASH_SCREEN)) {
////                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.common_error_icon));
////            }
////            messageView.setText(message);
////        }
//        button.setOnClickListener(view -> click.setOnRetry(type));
//        if (networkErrorContainer != null && networkErrorContainer.getChildCount() > 0) {
//            networkErrorContainer.removeAllViews();
//        }
//        networkErrorContainer.addView(errorLayout);
//        networkErrorContainer.setVisibility(View.VISIBLE);
//    }

    public static String getImageUrl(Context context, String url) {
        if (url != null && !url.contains(context.getString(R.string.http_text))) {
            url = context.getString(R.string.http) + url;
        }
        return url;
    }


    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

//    public static GradientDrawable getRatingBG(Context context, float rating) {
//        GradientDrawable shape = new GradientDrawable();
//        shape.setCornerRadius(8);
//        if (rating <= 1.0) {
//            shape.setColor(ContextCompat.getColor(context, R.color.ferrari_red));
//        } else if (rating > 1.0 && rating <= 2.0) {
//            shape.setColor(ContextCompat.getColor(context, R.color.safety_orange));
//        } else if (rating > 2.0 && rating <= 3.0) {
//            shape.setColor(ContextCompat.getColor(context, R.color.fluorescent_orange));
//        } else if (rating > 3.0 && rating <= 4.0) {
//            shape.setColor(ContextCompat.getColor(context, R.color.lime));
//        } else if (rating > 4.0) {
//            shape.setColor(ContextCompat.getColor(context, R.color.forest_green));
//        } else {
//            shape.setColor(ContextCompat.getColor(context, R.color.light_green));
//        }
//        return shape;
//    }


//    public static ItemDetails getItemDetailModel(Items productItem) {
//        ItemDetails item = new ItemDetails();
//        item.setItemId(productItem.getId());
//        item.setItemType(productItem.getItemType());
//        item.setName(productItem.getName());
//        Images[] images = new Images[1];
//        Images image = new Images();
//        image.setPrimaryImage(productItem.getDataImage().get(0).getPrimaryImageUrl());
//        images[0] = image;
//        ArrayList<ProductImages> imagesArrayList = new ArrayList<>();
//        ProductImages productImages = new ProductImages();
//        productImages.setPrimaryImage(productItem.getDataImage().get(0).getPrimaryImageUrl());
//        imagesArrayList.add(productImages);
//        item.setImages(imagesArrayList);
//        Availability availability = new Availability();
//        availability.setMrp(productItem.getDataPricing().getPrice());
//        availability.setOfferPrice(productItem.getDataPricing().getOfferPrice());
//        availability.setDiscount(productItem.getDataPricing().getDiscount());
//        availability.setStockStatus(productItem.getStockStatus());
//        //item.setAvailability(availability);
//        SocialActions socialActions = new SocialActions();
//        Rating rating = new Rating();
//        rating.setAverageRating(productItem.getDataSocialaction().getRatingAvg());
//        rating.setCount(Integer.parseInt(productItem.getDataSocialaction().getRatingCount()));
//        socialActions.setRatings(rating);
//        socialActions.setIsLiked(-1);
//        return item;
//    }

    public static boolean checkConditionForTooltipVisibility(Context context, String key) {
        return PurpllePrefManager.getInstance(context.getApplicationContext()).GLOBAL.getBoolean(key, false);
    }


//    public static Tooltip.TooltipView MakeToolTipWithDelay(final Context context, View anchorView, Tooltip.Gravity gravity, String text, int width, final String variable, Tooltip.ClosePolicy closePolicy, Tooltip.Callback callback, int toolTipLayoutStyle, final OnItemClickListener onItemClickListener, int delay, int duration, boolean SecondaryTooltipTextVisibility, boolean conditionForPreference) {
//
//
//        SpannableStringBuilder builder = new SpannableStringBuilder();
//        if (!SecondaryTooltipTextVisibility) {
//            SpannableString descriptionString = new SpannableString(text);
//            builder.append(descriptionString);
//            if (conditionForPreference)
//                PurpllePrefManager.getInstance(context.getApplicationContext()).GLOBAL.putBoolean(variable, true);
//
//        } else {
//            SpannableString descriptionString = new SpannableString(text);
//            float proportion = .9f;
//            if (variable.equalsIgnoreCase(PurplleConstants.TRY_ON_VIEW_FIVE)) proportion = 1f;
//
//            descriptionString.setSpan(new RelativeSizeSpan(proportion), 0, descriptionString.length(), 0);
//            if (!variable.equalsIgnoreCase(PurplleConstants.TRY_ON_VIEW_FIVE)) {
//                descriptionString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context.getApplicationContext(), R.color.black)), 0, descriptionString.length(), 0);
//                descriptionString.setSpan(new PurplleTypefaceSpan(PurplleTypeFace.getManropeRegular(PurplleApplication.getAppContext())), 0, descriptionString.length(), 0);
//                descriptionString.setSpan(new AlignmentSpan() {
//                    @Override
//                    public Layout.Alignment getAlignment() {
//                        if (variable.equals(PurplleConstants.SIMILAR_PRODUCTS_TOOLTIP_PDP))
//                            return Layout.Alignment.ALIGN_NORMAL;
//                        return Layout.Alignment.ALIGN_OPPOSITE;
//                    }
//                }, 0, descriptionString.length(), 0);
//            }
//
//            if (variable.equalsIgnoreCase(PurplleConstants.TRY_ON_VIEW_FIVE)) {
//                builder.append(descriptionString).append("\n\n");
//            } else {
//                builder.append(descriptionString);
//                SpannableString nextLine = new SpannableString("\n\n");
//                nextLine.setSpan(new RelativeSizeSpan(0.5f), 1, nextLine.length(), 0);
//                builder.append(nextLine);
//            }
//
//            ClickableSpan tncClickSpan = new ClickableSpan() {
//                @Override
//                public void onClick(View textView) {
//                    if (onItemClickListener != null) {
//                        PurpllePrefManager.getInstance(context.getApplicationContext()).GLOBAL.putBoolean(variable, true);
//                        onItemClickListener.onItemClick(textView, 0, null);
//                    }
//                }
//
//                @Override
//                public void updateDrawState(TextPaint ds) {
//                }
//            };
//            SpannableString tnc = new SpannableString("OK, GOT IT");
//            tnc.setSpan(new AlignmentSpan() {
//                @Override
//                public Layout.Alignment getAlignment() {
//                    if (variable.equals(PurplleConstants.SIMILAR_PRODUCTS_TOOLTIP_PDP))
//                        return Layout.Alignment.ALIGN_NORMAL;
//                    return Layout.Alignment.ALIGN_OPPOSITE;
//                }
//            }, 0, tnc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            tnc.setSpan(new RelativeSizeSpan(.9f), 0, tnc.length(), 0);
//            tnc.setSpan(new StyleSpan(Typeface.BOLD), 0, tnc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            if (!variable.equalsIgnoreCase(PurplleConstants.TRY_ON_VIEW_FIVE)) {
//                tnc.setSpan(new PurplleTypefaceSpan(PurplleTypeFace.getManropeBold(PurplleApplication.getAppContext())), 0, tnc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//                tnc.setSpan(new UnderlineSpan(), 0, tnc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//            }
//            tnc.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context.getApplicationContext(), variable.equalsIgnoreCase(PurplleConstants.TRY_ON_VIEW_FIVE) ? R.color.white : R.color.product_name_lite_black_color)), 0, tnc.length(), 0);
//            tnc.setSpan(tncClickSpan, 0, tnc.length(), 0);
//            builder.append(tnc);
//        }
//
//        Tooltip.TooltipView mCurrentTooltip = Tooltip.make(context, new Tooltip.Builder(101).anchor(anchorView, gravity).closePolicy(closePolicy, duration).text(builder, TextView.BufferType.SPANNABLE).withCallback(callback).fitToScreen(true).maxWidth(width).fadeDuration(400).typeface(PurplleTypeFace.getFontRegular(PurplleApplication.getAppContext())).withStyleId(toolTipLayoutStyle).floatingAnimation(Tooltip.AnimationBuilder.DEFAULT).withArrow(true).withOverlay(!SecondaryTooltipTextVisibility).showDelay(delay)
//                //.activateDelay(SecondaryTooltipTextVisibility ? 1000 : 0)
//                .build());
//        mCurrentTooltip.show();
//
//        return mCurrentTooltip;
//    }
//

//    public static Tooltip.TooltipView showArrowButtonToolTip(final Context context, View anchorView, Tooltip.Gravity gravity, String text, int width, Tooltip.ClosePolicy closePolicy, int toolTipLayoutDefaultStyle, int delay, int duration) {
//        SpannableString descriptionString = new SpannableString(text);
//        descriptionString.setSpan(new RelativeSizeSpan(1f), 0, descriptionString.length(), 0);
//
//
//        Tooltip.TooltipView mCurrentTooltip = Tooltip.make(context, new Tooltip.Builder(101).anchor(anchorView, gravity).closePolicy(closePolicy, duration).text(descriptionString, TextView.BufferType.SPANNABLE).fitToScreen(true).maxWidth(width).fadeDuration(400).typeface(PurplleTypeFace.getFontRegular(PurplleApplication.getAppContext())).withStyleId(toolTipLayoutDefaultStyle).floatingAnimation(Tooltip.AnimationBuilder.DEFAULT).withArrow(true).showDelay(delay).build());
//        mCurrentTooltip.show();
//
//        return mCurrentTooltip;
//    }


//    public static TooltipNew.TooltipView showTryOnToolTip(final Context context, View anchorView, TooltipNew.Gravity gravity, String text, int width, TooltipNew.ClosePolicy closePolicy, int toolTipLayoutDefaultStyle, int delay, int duration) {
//        SpannableString descriptionString = new SpannableString(text);
//        descriptionString.setSpan(new RelativeSizeSpan(1f), 0, descriptionString.length(), 0);
//
//
//        TooltipNew.TooltipView mCurrentTooltip = TooltipNew.make(context, new TooltipNew.Builder(101).anchor(anchorView, gravity).closePolicy(closePolicy, duration).text(descriptionString, TextView.BufferType.SPANNABLE).fitToScreen(true).maxWidth(width).fadeDuration(400).typeface(PurplleTypeFace.getFontRegular(PurplleApplication.getAppContext())).withStyleId(toolTipLayoutDefaultStyle).floatingAnimation(TooltipNew.AnimationBuilder.DEFAULT).withArrow(true).showDelay(delay).build());
//
//        return mCurrentTooltip;
//    }


//    public static Tooltip.TooltipView MakeLocationToolTip(final Context context, View anchorView, Tooltip.Gravity gravity, String text, int width, Tooltip.ClosePolicy closePolicy, int toolTipLayoutStyle, int delay, int duration) {
//        SpannableStringBuilder builder = new SpannableStringBuilder();
//        SpannableString tnc = new SpannableString(text);
//        tnc.setSpan(new AlignmentSpan.Standard(Layout.Alignment.ALIGN_NORMAL), 0, tnc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tnc.setSpan(new RelativeSizeSpan(.9f), 0, tnc.length(), 0);
//        tnc.setSpan(new StyleSpan(Typeface.BOLD), 0, tnc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//
//        tnc.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context.getApplicationContext(), R.color.white)), 0, tnc.length(), 0);
//
//        builder.append(tnc);
//        Tooltip.TooltipView mCurrentTooltip = Tooltip.make(context, new Tooltip.Builder(101).anchor(anchorView, gravity).closePolicy(closePolicy, duration).text(builder, TextView.BufferType.SPANNABLE).fitToScreen(true).maxWidth(width).fadeDuration(400).typeface(PurplleTypeFace.getFontRegular(PurplleApplication.getAppContext())).withStyleId(toolTipLayoutStyle).floatingAnimation(Tooltip.AnimationBuilder.DEFAULT).withArrow(true).showDelay(delay).build());
//        mCurrentTooltip.show();
//
//        return mCurrentTooltip;
//    }


//    public static int setplaceholder(int pos) {
//
//        if (pos < mSkeltonBackgroundColor.size()) {
//            return mSkeltonBackgroundColor.get(pos);
//        } else {
//            Random rand = new Random();
//            int value = rand.nextInt(12);
//            PurplleTrace.i("random", String.valueOf(value));
//            return mSkeltonBackgroundColor.get(value);
//        }
//    }


//    public static ArrayList<Integer> getSkeltonBackgroundColor() {
//        ArrayList<Integer> mSkeltonBackgroundColor = new ArrayList<>();
//        mSkeltonBackgroundColor.add(R.color.placeholder_one);
//        mSkeltonBackgroundColor.add(R.color.placeholder_two);
//        mSkeltonBackgroundColor.add(R.color.placeholder_three);
//        mSkeltonBackgroundColor.add(R.color.placeholder_four);
//        mSkeltonBackgroundColor.add(R.color.placeholder_five);
//        mSkeltonBackgroundColor.add(R.color.placeholder_six);
//        mSkeltonBackgroundColor.add(R.color.placeholder_seven);
//        mSkeltonBackgroundColor.add(R.color.placeholder_eight);
//        mSkeltonBackgroundColor.add(R.color.placeholder_nine);
//        mSkeltonBackgroundColor.add(R.color.placeholder_ten);
//        mSkeltonBackgroundColor.add(R.color.placeholder_eleven);
//        mSkeltonBackgroundColor.add(R.color.placeholder_twelve);
//        return mSkeltonBackgroundColor;
//    }

    @Nullable
    public static Activity getActivity(@Nullable Context cont) {
        if (cont == null) {
            return null;
        } else if (cont instanceof Activity) {
            return (Activity) cont;
        } else if (cont instanceof ContextWrapper) {
            return getActivity(((ContextWrapper) cont).getBaseContext());
        }
        return null;
    }

    public static boolean equals(@Nullable Object a, @Nullable Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static boolean rectContainsRectWithTolerance(@NonNull final Rect parentRect, @NonNull final Rect childRect, final int t) {
        return parentRect.contains(childRect.left + t, childRect.top + t, childRect.right - t, childRect.bottom - t);
    }

//    public static void fblogout() {
//        if (FirebaseAuth.getInstance() != null) {
//            FirebaseAuth.getInstance().signOut();
//        }
//    }

    public static float getDeviceDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

//    public static boolean isValidEmail(Context context, String email) {
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(context, context.getString(R.string.email_field_error_msg), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Toast.makeText(context, context.getString(R.string.invalid_email_msg), Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        return true;
//    }

    public static Integer tryIntegerParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /**
     * Check for valid email id
     *
     * @param context context
     * @param email   email address string
     * @return boolean is email id valid
     */
    public static boolean isValidEmailCheckWithRegExpn(Context context, String email) {
        String regExpn = "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@" + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|" + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        if (!pattern.matcher(email).matches()) {
            return false;
        }
        return true;
    }

    /**
     * Check for valid mobile number
     *
     * @param phone mobile number
     * @return boolean is mobile number valid
     */
    public static boolean checkPhoneNumber(Context context, String phone) {
        if (phone.isEmpty()) return true;
        if (phone.length() < 10) {
            return false;
        }
        return true;
    }

    /**
     * function to get application name
     *
     * @param context context
     * @return application name
     */
    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    /**
     * function to get current time stamp
     *
     * @param context Context
     * @return current timeStamp
     */
    public static String getTimeStamp(Context context) {
        return DateUtils.formatDateTime(context, System.currentTimeMillis(), DateUtils.FORMAT_SHOW_TIME);
    }

    /**
     * get colour
     *
     * @param clr         colour
     * @param default_clr deflaut colour
     * @return color
     */
    public static int getColour(String clr, String default_clr) {
        try {
            return Color.parseColor(clr);
        } catch (Exception e) {
            return Color.parseColor(default_clr);
        }
    }

    public static Float tryFloatParse(String text) {
        try {
            return Float.parseFloat(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static int TimeDifferenceBetweenDates(long time, long savedDate) {
        long diff = time - savedDate;
        return (int) (diff / (1000 * 60 * 60));
    }

    public static String getTodayFormateDateForAppUpdate() {
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd yyyy hh:mm:ss aaa");
        return simpleDateFormat.format(c);
    }

//    public static void updateABTestingData(@NonNull Gson gson, @Nullable AboutMe aboutMe) {
//        if (aboutMe == null) {
//            return;
//        }
//        if (aboutMe.getEnableBeautyQuizOnBoarding() != null && !aboutMe.getEnableBeautyQuizOnBoarding().trim().isEmpty()) {
//            if (aboutMe.getEnableBeautyQuizOnBoarding().equalsIgnoreCase("1")) {
//                PreferenceUtil.setEnableBeautyQuizOnboarding(1);
//            }
//        }
//        if (aboutMe.getAbTesting() != null) {
//            //Storing  AB Experiment value for PDP screen
//            if (aboutMe.getAbTesting().getAbExperiment() != null) {
//                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.AB_EXPERIMENT_PDP, aboutMe.getAbTesting().getAbExperiment());
//            }
//
//            //Storing  AB Experiment value for Listing screen for Variant PopUp
//            if (aboutMe.getAbTesting().getPdpAbListingPage() != null) {
//                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.LISTING_VARIANT_AB_TESTING, aboutMe.getAbTesting().getPdpAbListingPage());
//            }
//            if (aboutMe.getAbTesting().getPricePerUnitAbTesting() != null) {
//                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.PRICE_PER_UNIT_AB, aboutMe.getAbTesting().getPricePerUnitAbTesting());
//            }
//
//            //AB flag for For you section Bottom Nav
//            if (aboutMe.getAbTesting().getForYouAbExperiment() != null && !aboutMe.getAbTesting().getForYouAbExperiment().trim().isEmpty()) {
//                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.FORYOU_AB_TESTING, aboutMe.getAbTesting().getForYouAbExperiment());
//            }
//
//            //Storing  AB Experiment value for Choose Freebie on Cart Page
//            if (aboutMe.getAbTesting().getChooseFreebieAbTesting() != null) {
//                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.AB_CHOOSE_FREEBIE, aboutMe.getAbTesting().getChooseFreebieAbTesting());
//            }
//            //Storing  AB Experiment value for show Bogo on Cart Page
//            if (aboutMe.getAbTesting().getBogoAbTesting() != null) {
//                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.BOGO_OFFERS_AB, aboutMe.getAbTesting().getBogoAbTesting());
//            }
//
//            if (aboutMe.getAbTesting().getAtcPopupRecosExpFlag() != null) {
//                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.ATC_POPUP_RECOS_AB_EXPERIMENT, aboutMe.getAbTesting().getAtcPopupRecosExpFlag());
//            }
//        }
//        setABTestingScreenPreference(gson, aboutMe.getScreenABTesting());
//        setABTestingListingVariantPreference(aboutMe.getAbTesting());
//    }

//    public static void setABTestingScreenPreference(@NonNull Gson gson, @Nullable ScreenABTesting screenABTesting) {
//        if (screenABTesting == null) {
//            return;
//        }
//        PurpllePreference purpllePreference = PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL;
//        // Create if-else ladder for every screen below.
//        // Listing screen
//        if (screenABTesting.getListingABTestingList() != null && !screenABTesting.getListingABTestingList().isEmpty()) {
//            String listingJson = gson.toJson(screenABTesting.getListingABTestingList());
//            purpllePreference.putString(PurpllePrefKey.LISTING_AB_TESTING, listingJson);
//        }
//    }


//    public static void setABTestingListingVariantPreference(@Nullable ABTesting abTesting) {
//        if (abTesting == null) {
//            return;
//        }
//        PurpllePreference purpllePreference = PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL;
//        //for variant popup AB Testing on Listing page
//        if (Util.objectValidator(abTesting.getPdpAbListingPage())) {
//            purpllePreference.putString(PurpllePrefKey.LISTING_VARIANT_AB_TESTING, abTesting.getPdpAbListingPage().trim());
//        }
//    }

//    static public void loginCheck(Context context, String pageName) {
//        if (PreferenceUtil.isUserLogin(context)) {
//            PurplleTrace.d("Login In Check", "already Logged");
//        } else {
//            Intent intent = new Intent(context, AuthenticationActivity.class);
//            intent.putExtra(PurplleApplication.getAppContext().getString(R.string.page_type), pageName);
//            intent.putExtra(PurplleApplication.getAppContext().getString(R.string.screen_type), AuthenticationActivity.BOTTOM_SHEET_LOGIN_SCREEN);
//            context.startActivity(intent);
//            getActivity(context).overridePendingTransition(R.anim.slide_up, R.anim.no_change);
//        }
//    }

    public static boolean objectValidator(Object obj) {
        if (obj == null) return false;
        if (obj instanceof String) {
            return obj.toString().trim().length() > 0 && !obj.toString().trim().equalsIgnoreCase("null");
        } else if (obj instanceof List<?>) {
            return ((List<?>) obj).size() > 0;
        }
        return true;
    }

    public static String appendAdditionalParams(String additionalParams, String url) {
        if (additionalParams != null && !additionalParams.isEmpty()) {
            List<String> paramList = Arrays.asList(additionalParams.split(","));
            StringBuilder builder = new StringBuilder();
            if (BaseUtil.getProps(PurplleApplication.getAppContext()) != null && !BaseUtil.getProps(PurplleApplication.getAppContext()).isEmpty()) {
                for (String param : paramList) {
                    if (BaseUtil.getProps(PurplleApplication.getAppContext()).containsKey(param.trim())) {
                        if (builder.length() > 0) {
                            builder.append("&");
                        }
                        builder.append(param.trim()).append("=").append(BaseUtil.getProps(PurplleApplication.getAppContext()).get(param.trim()).toString());
                    }
                }
                url += "&" + builder.toString();
            }
        }
        if (!url.startsWith("/")) url = "/" + url;
        return url;
    }

    public static List<Address> getGeoCoderAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses = null;
        geocoder = new Geocoder(PurplleApplication.getAppContext(), Locale.ENGLISH);

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return addresses;

    }

    public static List<com.example.couponbase.model.common.user.Address> repeatationCheckOnPostalCode(List<com.example.couponbase.model.common.user.Address> mAddressList) {
        HashMap<String, com.example.couponbase.model.common.user.Address> uniqueAddress = new HashMap<>();
        for (com.example.couponbase.model.common.user.Address mAdd : mAddressList) {
            uniqueAddress.put(mAdd.getPostalCode(), mAdd);
        }
        return new ArrayList<com.example.couponbase.model.common.user.Address>(uniqueAddress.values());
    }

    public static boolean abExperimentCheck() {
        // true - blush , false - old
        try {
            abExperiment = PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getString(PurpllePrefKey.AB_EXPERIMENT_PDP, PurplleApplication.getAppContext().getString(R.string.default_str));
            return abExperiment != null && !abExperiment.equalsIgnoreCase(PDP_OLD_VERSION);
        } catch (Exception e) {
            //blush is default in case of any crash
            return true;
        }
    }

    public static void callVibration() {
        Vibrator vb = (Vibrator) PurplleApplication.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
        if (vb != null) {
            vb.vibrate(VIBRATION_DURATION);
        }
    }

    public static InputFilter getAlphaNumericInputFilter() {
        return (source, start, end, dest, dstart, dend) -> {

            if (source.equals("")) { // for backspace
                return source;
            }
            if (source.toString().matches("[a-zA-Z0-9 ]+")) {
                return source;
            }
            return "";
        };
    }

    public static String getJsonFromAssets(String fileName) {
        String jsonString;
        try {
            InputStream is = PurplleApplication.getAppContext().getResources().openRawResource(PurplleApplication.getAppContext().getResources().getIdentifier(fileName, "raw", PurplleApplication.getAppContext().getPackageName()));

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

    public static boolean checkBrandNameMatchesProductName(String productName, String itembrand) {
        if (productName.length() > itembrand.length()) {
            String productBrandName = productName.substring(0, itembrand.length());
            return productBrandName.equalsIgnoreCase(itembrand);
        }
        return false;
    }

    public static InputStream getBitmapInputStream(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 80, byteArrayOutputStream);
        byte[] bitmapData = byteArrayOutputStream.toByteArray();
        return new ByteArrayInputStream(bitmapData);
    }

    public static void logoutUser(Context context, String reference) {
        //send auth event
//        if (PreferenceUtil.isUserLogin(context)) {
//            EventData eventData = AnalyticsHelper.getAuthActionData("logout", reference, reference);
//            AnalyticsHelper.trackCleverTapAnalytics(context, AnalyticsHelper.AUTH_ACTION, eventData);
//        }

//        FirebaseAuth.getInstance().signOut();
//        //call branch logout
//        if (Branch.getInstance() != null) {
//            Branch.getInstance().logout();
//        }
        //clear user preference
        PurpllePrefManager.getInstance(context).USER.clear();

        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.FOR_YOU_IMAGE, "");
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.GRATIFICATION_DEEPLINK, "");

        //reset cart count to 0
        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.CART_COUNT, "0");
        UserDataSingleton.getInstance().clearCartIds();
        UserDataSingleton.getInstance().clearWishListIds();

        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putStringSet(PurpllePrefKey.UPSELL_COUPONS, new HashSet<>());
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putBoolean(PurpllePrefKey.DISMISS_UPSELL_WIDGET, false);
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putBoolean(PurpllePrefKey.DISMISS_SHIPPING_WIDGET, false);
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.UPSELL_GIFT_UNLOCKED_OFFER_ID, null);
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.NEW_UPSELL_COUPON, null);
        PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putStringSet(PurpllePrefKey.UPSELL_OFFER_IDS, new HashSet<>());
    }

    public static boolean displayNotificationPermission(long timeLastDisplayed) {
        PurplleTrace.d("NotificationDisplay", "displayNotificationPermission: " + timeLastDisplayed);
        if (timeLastDisplayed == -1) {
            return true;
        } else {
            if ((System.currentTimeMillis()) >= (timeLastDisplayed + (24 * 60 * 60 * 1000))) {
                return true;
            } else {
                return false;
            }

        }
    }

    public static void sendSessionDetailsEvent(boolean isSplash, Intent intent) {
        mLastSessionTime = PreferenceUtil.getLastSessionTime(PurplleApplication.getAppContext());
        long timeStamp = BaseUtil.getTimeStamp();
        long diff = Math.abs((mLastSessionTime - timeStamp) / 60);
        if (mLastSessionTime == 0 || diff > SESSION_TIME_OUT) {
            PurplleConstants.sessionIdChange = true;
            String sessionId = PreferenceUtil.getDeviceId(PurplleApplication.getAppContext()) + "_" + timeStamp;
            PreferenceUtil.setLastSessionTime(PurplleApplication.getAppContext(), timeStamp);
            PreferenceUtil.setSessionId(PurplleApplication.getAppContext(), sessionId);
            //EventData eventData;
            if (mLastSessionTime == 0) {
                //eventData = AnalyticsHelper.getSessionDetailsEventData(AnalyticsHelper.PAGE_SPLASH_SCREEN, "default", "", true);
            } else {
                String pageType = PurplleApplication.getInstance().getmCurrentPage() == null || PurplleApplication.getInstance().getmCurrentPage().trim().isEmpty() ? "default" : PurplleApplication.getInstance().getmCurrentPage();
                String pageTypeValue = PurplleApplication.getInstance().getmCurrentPageTypeValue() == null || PurplleApplication.getInstance().getmCurrentPageTypeValue().trim().isEmpty() ? "default" : PurplleApplication.getInstance().getPreviousPageTypeValue();
                String pageTitle = PurplleApplication.getInstance().getmPageTitle();

                //eventData = AnalyticsHelper.getSessionDetailsEventData(pageType, pageTypeValue, pageTitle, false);
                PurplleTrace.i("session_id", "change " + sessionId);
                if (!PurplleConstants.calledViaDeeplink && !(isSplash) &&
                        intent.getStringExtra(PurplleApplication.getAppContext().getString(R.string.notification_source)) == null) {
                    JSONObject tempReferringParams = new JSONObject();
                    //AnalyticsHelper.sendAppOpenEvent(PurplleApplication.getAppContext(), tempReferringParams, null);
                }
            }
            //AwsKinesisHelper.saveEventRecord(PurplleApplication.getAppContext(), AwsKinesisHelper.SESSION_DETAILS, eventData);
        } else {
            PurplleConstants.sessionIdChange = false;
        }
        PurplleTrace.i("session_id", "last time " + mLastSessionTime);
        PurplleTrace.i("session_id", "time stamp " + timeStamp);
        mLastSessionTime = timeStamp;
        PreferenceUtil.setLastSessionTime(PurplleApplication.getAppContext(), mLastSessionTime);
    }

    public static String getUntranslatedString(String primaryString, String eventString) {
        if (eventString == null) {
            return primaryString;
        }
        return eventString;
    }

    public static int getWindowHeight(Activity activity) {
        // Calculate the height of the screen
        Resources resources = activity.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }
}