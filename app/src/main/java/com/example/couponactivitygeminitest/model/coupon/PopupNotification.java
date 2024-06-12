package com.example.couponactivitygeminitest.model.coupon;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.example.couponactivitygeminitest.model.Images;

import java.util.List;

public class PopupNotification implements Parcelable {
    @SerializedName("primary_text")
    private String primaryText;
    private String coupon;
    private String amount;
    @SerializedName("tertiary_text")
    private String message;
    @SerializedName("secondary_text")
    private String secondaryText;
    @SerializedName("images")
    private Images images;
    @SerializedName("coupon_icon")
    private String couponIcon;
    @SerializedName("coupon_applied_message")
    private String couponAppliedMessage;
    @SerializedName("coupon_saved_message")
    private String couponSavedMessage;

    @SerializedName("coupon_close_message")
    private String couponCloseMessage;

    @SerializedName("images_array")
    private List<Images> imageArray;

    @SerializedName("coupon_applied_description")
    private String couponAppliedDescription;
    @SerializedName("type")
    private String type;



    public String getCouponIcon() {
        return couponIcon;
    }

    public String getCouponAppliedMessage() {
        return couponAppliedMessage;
    }

    public String getCouponSavedMessage() {
        return couponSavedMessage;
    }

    public String getCouponCloseMessage() {
        return couponCloseMessage;
    }

    public List<Images> getImageArray() {
        return imageArray;
    }

    public PopupNotification(Parcel in) {
        primaryText = in.readString();
        coupon = in.readString();
        amount = in.readString();
        message = in.readString();
        secondaryText = in.readString();
    }

    public static final Creator<PopupNotification> CREATOR = new Creator<PopupNotification>() {
        @Override
        public PopupNotification createFromParcel(Parcel in) {
            return new PopupNotification(in);
        }

        @Override
        public PopupNotification[] newArray(int size) {
            return new PopupNotification[size];
        }
    };

    public String getPrimaryText() {
        return primaryText;
    }

    public String getCoupon() {
        return coupon;
    }

    public String getAmount() {
        return amount;
    }

    public String getMessage() {
        return message;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public Images getImages() {
        return images;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(primaryText);
        dest.writeString(coupon);
        dest.writeString(amount);
        dest.writeString(message);
        dest.writeString(secondaryText);
    }

    public String getCouponAppliedDescription() {
        return couponAppliedDescription;
    }
    public String getType(){
        return type;
    }
}
