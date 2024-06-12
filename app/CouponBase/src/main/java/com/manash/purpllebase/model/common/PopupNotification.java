package com.manash.purpllebase.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PopupNotification implements Parcelable {
    @SerializedName("primary_text")
    private String primaryText;
    private String coupon;
    private String amount;
    @SerializedName("tertiary_text")
    private String message;
    @SerializedName("secondary_text")
    private String secondaryText;

    protected PopupNotification(Parcel in) {
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
}
