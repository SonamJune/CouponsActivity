package com.example.couponactivitygeminitest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.couponactivitygeminitest.model.ItemTimer;
import com.google.gson.annotations.SerializedName;

public class Coupon implements Parcelable {
    private String code;
    private String id;
    private String description;
    private String key;
    private String tnc;
    @SerializedName("is_cashback")
    private String isCashBack;
    @SerializedName("show_timer_widget")
    private String isTimerVisible;
    @SerializedName("item_timer_widget")
    private ItemTimer itemTimer;

    protected Coupon(Parcel in) {
        code = in.readString();
        key = in.readString();
        isCashBack = in.readString();
    }

    public static final Creator<Coupon> CREATOR = new Creator<Coupon>() {
        @Override
        public Coupon createFromParcel(Parcel in) {
            return new Coupon(in);
        }

        @Override
        public Coupon[] newArray(int size) {
            return new Coupon[size];
        }
    };




    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTnc() {
        return tnc;
    }

    public String getIsCashBack() {
        return isCashBack;
    }

    public String getIsVisibility() { return isTimerVisible; }

    public ItemTimer getTimerData(){
        return itemTimer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(key);
        dest.writeString(isCashBack);
    }
}

