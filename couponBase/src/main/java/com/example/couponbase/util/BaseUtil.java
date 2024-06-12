package com.example.couponbase.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;

import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.couponbase.PurplleApplication;
import com.example.couponbase.R;
import com.example.couponbase.helper.MessageType;
import com.example.couponbase.helper.PurplleTrace;
import com.example.couponbase.helper.PurplleTypeFace;
import com.example.couponbase.preference.PreferenceUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.regex.Pattern;

public class BaseUtil {


    /**
     * Hides the soft keyboard
     */
    public static void hideSoftKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /***
     * Show the soft keyboard
     */
    public static void showSoftKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    /***
     * return the height of Actionbar as per current device
     */
    public static int getActionBarHeight(Context context) {
        int height = 0;
        try {
            final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
            height = (int) styledAttributes.getDimension(0, 0);
            styledAttributes.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return height;
    }


    /***
     * get users default email account using google account manager.
     */
    public static String getUserDefaultEmailAccount(Context context) {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(context).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                return account.name;
            }
        }
        return null;
    }

    /**
     * Simple alert dialog in blush.
     */
    public static void showAlertDialogBlush(final Context context, int noOfButtons, String title, String msg, final DialogListener listener) {
        showAlertDialogWithCancelableSetting(context, noOfButtons, title, msg, true, listener);
    }

    public static void showAlertDialogWithCancelableSetting(final Context context, int noOfButtons, String title, String msg, boolean isCancelAble, final DialogListener listener) {
        showAlertDialogBlush(context, noOfButtons, title, msg, isCancelAble, context.getString(R.string.yes), context.getString(R.string.no_translatable), listener);
    }

    public static void showAlertDialogBlush(final Context context, int noOfButtons, String title, String msg, boolean isCancelAble, String positiveButtonText, String negativeButtonText, final DialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null && !title.trim().isEmpty()) {
            builder.setTitle(title);
        }
        if (msg != null && !msg.isEmpty()) {
            builder.setMessage(msg);
        }
        setAlertContent(context, builder, noOfButtons, positiveButtonText, negativeButtonText, listener, isCancelAble);
    }

    private static void setAlertContent(Context context, AlertDialog.Builder builder, int noOfButtons, String positiveButtonText, String negativeButtonText, final DialogListener listener, boolean isCancelAble) {

        if (positiveButtonText == null || positiveButtonText.trim().isEmpty()) {
            positiveButtonText = context.getString(R.string.yes);
        }

        if (negativeButtonText == null || negativeButtonText.trim().isEmpty()) {
            negativeButtonText = context.getString(R.string.no_translatable);
        }

        builder.setCancelable(isCancelAble);
        switch (noOfButtons) {
            case 1:
                builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onAction(DialogListener.YES);
                        }
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (listener != null) {
                            listener.onAction(DialogListener.CANCEL);
                        }
                    }
                });

                break;
            case 2:
                builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onAction(DialogListener.YES);
                        }
                    }
                });
                builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onAction(DialogListener.NO);
                        }
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (listener != null) {
                            listener.onAction(DialogListener.CANCEL);
                        }
                    }
                });
                break;
            case 3:
                break;
        }

        AlertDialog create = builder.create();
        if (context instanceof Activity && !((Activity) context).isFinishing()) {
            create.show();
            setBlushAlertDialogFont(context, create);
        }

    }

    public static void setBlushAlertDialogFont(Context context, AlertDialog create) {
        TextView message = create.findViewById(android.R.id.message);
        if (message != null) {
            message.setTextColor(ContextCompat.getColor(context, R.color.medium_gray_color));
            message.setTypeface(PurplleTypeFace.getManropeRegular(PurplleApplication.getAppContext()));
            message.setLetterSpacing(0.03f);

//            TextView alertTitle = create.findViewById(R.id.alertTitle);
//
//            if (alertTitle != null) {
//                alertTitle.setTextColor(ContextCompat.getColor(context, R.color.dark_gray_color));
//                alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
//                alertTitle.setTypeface(PurplleTypeFace.getManropeMedium(PurplleApplication.getAppContext()));
//            }

            Button btn1 = create.findViewById(android.R.id.button1);
            if (btn1 != null) {
                btn1.setTextColor(ContextCompat.getColor(context, R.color.purplle_base));

                btn1.setTypeface(PurplleTypeFace.getManropeMedium(PurplleApplication.getAppContext()));
            }

            Button btn2 = create.findViewById(android.R.id.button2);
            if (btn2 != null) {
                btn2.setTextColor(ContextCompat.getColor(context, R.color.purplle_base));
                btn2.setTypeface(PurplleTypeFace.getManropeMedium(PurplleApplication.getAppContext()));
            }

            Button btn3 = create.findViewById(android.R.id.button3);
            if (btn3 != null) {
                btn3.setTextColor(ContextCompat.getColor(context, R.color.medium_gray_color));
                btn3.setTypeface(PurplleTypeFace.getManropeMedium(PurplleApplication.getAppContext()));
            }
        }
    }

    /**
     * Simple alert dialog.
     */
    public static void showAlertDialog(final Context context, int noOfButtons, String title, String msg, final DialogListener listener) {
        showAlertDialog(context, noOfButtons, title, msg, true, listener);
    }

    /**
     * Alert dialog with cancelable setting.
     */
    public static void showAlertDialog(final Context context, int noOfButtons, String title, String msg, boolean isCancelAble, final DialogListener listener) {
        showAlertDialog(context, noOfButtons, title, msg, isCancelAble, context.getString(R.string.yes), context.getString(R.string.no_translatable), listener);
    }

    public static void showAlertDialog(final Context context, int noOfButtons, String title, Spanned msg, boolean isCancelAble, String positiveButtonText, String negativeButtonText, final DialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null && !title.trim().isEmpty()) {
            builder.setTitle(title);
        }
        if (msg != null) {
            builder.setMessage(msg);
        }
        setAlert(context, builder, noOfButtons, positiveButtonText, negativeButtonText, listener, isCancelAble);
    }

    /**
     * Alert dialog with cancelable and custom button text.
     */
    public static void showAlertDialog(final Context context, int noOfButtons, String title, String msg, boolean isCancelAble, String positiveButtonText, String negativeButtonText, final DialogListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (title != null && !title.trim().isEmpty()) {
            builder.setTitle(title);
        }
        if (msg != null && !msg.isEmpty()) {
            builder.setMessage(msg);
        }
        setAlert(context, builder, noOfButtons, positiveButtonText, negativeButtonText, listener, isCancelAble);
    }

    private static void setAlert(Context context, AlertDialog.Builder builder, int noOfButtons, String positiveButtonText, String negativeButtonText, final DialogListener listener, boolean isCancelAble) {

        if (positiveButtonText == null || positiveButtonText.trim().isEmpty()) {
            positiveButtonText = context.getString(R.string.yes);
        }

        if (negativeButtonText == null || negativeButtonText.trim().isEmpty()) {
            negativeButtonText = context.getString(R.string.no_translatable);
        }

        builder.setCancelable(isCancelAble);
        switch (noOfButtons) {
            case 1:
                builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onAction(DialogListener.YES);
                        }
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (listener != null) {
                            listener.onAction(DialogListener.CANCEL);
                        }
                    }
                });

                break;
            case 2:
                builder.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onAction(DialogListener.YES);
                        }
                    }
                });
                builder.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (listener != null) {
                            listener.onAction(DialogListener.NO);
                        }
                    }
                });
                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (listener != null) {
                            listener.onAction(DialogListener.CANCEL);
                        }
                    }
                });
                break;
            case 3:
                break;
        }

        AlertDialog create = builder.create();
        if (context instanceof Activity && !((Activity) context).isFinishing()) {
            create.show();
            setAlertDialogFont(context, create);
        }

    }

    public static void setAlertDialogFont(Context context, AlertDialog create) {
        TextView message = create.findViewById(android.R.id.message);
        if (message != null) {
            message.setTextColor(ContextCompat.getColor(context, R.color.black));
            message.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(R.dimen._12ssp));

            message.setTypeface(PurplleTypeFace.getManropeRegular(PurplleApplication.getAppContext()));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                message.setLetterSpacing(0.03f);
            }
        }

        //TextView alertTitle = create.findViewById(R.id.alertTitle);
//        if (alertTitle != null) {
//            alertTitle.setTextColor(ContextCompat.getColor(context, R.color.black));
//            alertTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,context.getResources().getDimension(R.dimen._12ssp));
//            alertTitle.setTypeface(PurplleTypeFace.getManropeBold(PurplleApplication.getAppContext()));
//        }

        Button btn1 = create.findViewById(android.R.id.button1);
        if (btn1 != null) {
            btn1.setTextColor(ContextCompat.getColor(context, R.color.purplle_base));
            btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX,PurplleApplication.getAppContext().getResources().getDimension(R.dimen._12ssp));
            btn1.setTypeface(PurplleTypeFace.getManropeBold(PurplleApplication.getAppContext()));
        }

        Button btn2 = create.findViewById(android.R.id.button2);
        if (btn2 != null) {
            btn2.setTextColor(ContextCompat.getColor(context, R.color.purplle_base));
            btn2.setTextSize(TypedValue.COMPLEX_UNIT_PX,PurplleApplication.getAppContext().getResources().getDimension(R.dimen._12ssp));
            btn2.setTypeface(PurplleTypeFace.getManropeBold(PurplleApplication.getAppContext()));
        }

        Button btn3 = create.findViewById(android.R.id.button3);
        if (btn3 != null) {
            btn3.setTextColor(ContextCompat.getColor(context, R.color.medium_gray_color));
            btn3.setTypeface(PurplleTypeFace.getManropeRegular(PurplleApplication.getAppContext()));
        }
    }

    public static String getDeviceId(Context context) {
        String deviceId = PreferenceUtil.getAndroidId(context);
        return deviceId;
    }

    public static int convertDpToPx(Context context, int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int convertPxToDp(Context context, int PX) {
        return (int) (PX / Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean hasPermission(Context context, String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED);
    }

    public static void setStatusBarColor(Activity activity) {
        setStatusBarColor(activity, activity.getResources().getColor(R.color.status_bar_color));
    }

    public static void setStatusBarColor(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
    }

    public static String getImageUrl(Context context, String url) {
        if (url != null && !url.contains(context.getString(R.string.http_text))) {
            url = context.getString(R.string.http) + url;
        }
        return url;
    }

    public static void setupActionBar(AppCompatActivity appCompatActivity) {
        //Toolbar toolbar = appCompatActivity.findViewById(R.id.tool_bar_generic);
        //appCompatActivity.setSupportActionBar(toolbar);
    }

    public static MessageType getIconAndBackgroundColor(String type, Context context) {
        String icon = null;
        int backgroundColor = Color.YELLOW, color = Color.WHITE;
        Drawable drawable = null;
        switch (type) {
            case "info":
                backgroundColor = ContextCompat.getColor(context, R.color.info_bg_color);
                icon = context.getString(R.string.info_icon_id);
                break;
            case "error":
                backgroundColor = ContextCompat.getColor(context, R.color.negative_bg_color);
                icon = context.getString(R.string.circular_cross_icon);
                break;
            case "success":
                backgroundColor = ContextCompat.getColor(context, R.color.positive_bg_color);
                icon = context.getString(R.string.circular_tick_icon);
                break;
            case "warning":
                backgroundColor = ContextCompat.getColor(context, R.color.alert_bg_color);
                icon = context.getString(R.string.exclamation_icon_id);
                color = ContextCompat.getColor(context, R.color.dark_gray_color);
                break;
            case "offer":
                backgroundColor = ContextCompat.getColor(context, R.color.pink);
                icon = context.getString(R.string.new_offers_icon);
                break;
            case "v2":
                //drawable = ContextCompat.getDrawable(context, R.drawable.circular_textview_bg);
                backgroundColor = ContextCompat.getColor(context, R.color.white);
                icon = context.getString(R.string.info_icon_id);
                break;

        }
        MessageType messageType = new MessageType();
        messageType.setBackgroundColor(backgroundColor);
        messageType.setDrawable(drawable);
        messageType.setColor(color);
        messageType.setIcon(icon);
        return messageType;
    }


    public static View getRootView(Activity activity) {
        return ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    }

    public static int getDeviceWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;

    }

    public static long getTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }

    public static void allowPdToOpen() {
        final Runtime runtime = Runtime.getRuntime();
        final long usedMemInMB = (runtime.totalMemory() - runtime.freeMemory()) / 1048576L;
        PurplleTrace.i("App_Memory", "usedMemInMB " + usedMemInMB);
        final long maxHeapSizeInMB = runtime.maxMemory() / 1048576L;
        PurplleTrace.i("App_Memory", "maxHeapSizeInMB " + maxHeapSizeInMB);
        final long availHeapSizeInMB = maxHeapSizeInMB - usedMemInMB;
        PurplleTrace.i("App_Memory", "availHeapSizeInMB " + availHeapSizeInMB);
        //return usedMemInMB < maxHeapSizeInMB * 0.7;
    }

    public static void saveProp(HashMap<String, Object> params, Context context) {
        Gson gson = new Gson();
        String hashMapString = gson.toJson(params);
        PurplleTrace.i("Flutter", "save props " + hashMapString);
        if(params.get("bs_is_answered") != null && !params.get("bs_is_answered").toString().trim().isEmpty()) {
            PreferenceUtil.saveUserInterest(params.get("bs_is_answered").toString(),context);
        } else {
            PreferenceUtil.saveUserInterest("1",context);
        }
        PreferenceUtil.saveProps(hashMapString, context);
    }

    public static HashMap<String, Object> getProps(Context context) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        return gson.fromJson(PreferenceUtil.getProps(context), type);

    }

    public static boolean isBuildTypeRelease(Context context) {
        return PreferenceUtil.getBuildVariant(context) != null
                && PreferenceUtil.getBuildVariant(context).equalsIgnoreCase("release");
    }


    public static GradientDrawable getCornerDrawable(int solid, float[] cornerRadii) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadii(cornerRadii);
        shape.setColor(solid);
        // shape.setStroke(cornerWidth, cornerColor);
        return shape;
    }

    public static GradientDrawable getCornerDrawable(float cornerRadius, int cornerColor, float cornerWidth) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(cornerRadius);
//        shape.setColor(solid);
        shape.setStroke((int) cornerWidth, cornerColor);
        return shape;
    }

    public static GradientDrawable getCornerDrawable(float cornerRadius, int cornerColor, int solidColor, float cornerWidth) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(cornerRadius);
        shape.setColor(solidColor);
        shape.setStroke((int) cornerWidth, cornerColor);
        return shape;
    }


    public static GradientDrawable getSolidCornerDrawable(float cornerRadius, int solidColor) {
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(cornerRadius);
        shape.setColor(solidColor);
        return shape;
    }

    public static GradientDrawable getGradientDrawable(int leftColor, int rightColor, GradientDrawable.Orientation orientation, float cornerRadius) {
        int[] colors = {leftColor, rightColor};
        GradientDrawable gd = new GradientDrawable(orientation, colors);
        gd.setCornerRadius(cornerRadius);
        return gd;
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }

    public static String getStyledFont(String html) {
        boolean addBodyStart = !html.toLowerCase().contains("<body>");
        boolean addBodyEnd = !html.toLowerCase().contains("</body");
        return "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "   <style type=\"text/css\">\n" +
                "    @font-face {font-family: Manrope-Regular;src: url(\"fonts/Manrope-Regular.ttf\")}\n" +
                "    body{font-family:'Manrope-Regular';color:#6d7c83;text-align:left;margin:0;font-size:14px;}\n" +
                "   .description p, .description, .description ul, .description ol{margin-top:0;margin-bottom:10px;color:#6d7c83;font-size:14px;}\n" +
                "   .description ul{padding-left:18px;line-height:18px;}\n" +
                "   .description ul li{margin:0;margin-bottom:5px;}\n" +
                "    .description img {max-width:100% !important;display:inline-block;margin:0 auto;width:auto !important;height:auto!important;}    \n" +
                "</style>" +
                (addBodyStart ? "<body><div class=\"description\">" : "") + html + (addBodyEnd ? "</div></body>" : "");
    }
}
