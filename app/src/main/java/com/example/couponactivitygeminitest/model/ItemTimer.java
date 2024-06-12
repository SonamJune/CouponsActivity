package com.example.couponactivitygeminitest.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ItemTimer implements Parcelable {
    private String message;
    @SerializedName("enddate")
    private String endTime;
    @SerializedName("timeroffset")
    private String timerOffSet;

    protected ItemTimer(Parcel in) {
    }

    public static final Creator<ItemTimer> CREATOR = new Creator<ItemTimer>() {
        @Override
        public ItemTimer createFromParcel(Parcel in) {
            return new ItemTimer(in);
        }

        @Override
        public ItemTimer[] newArray(int size) {
            return new ItemTimer[size];
        }
    };

    public String getMessage(){
        return message;
    }

    public String getEndTime(){
        return endTime;
    }

    public String getTimerOffSet(){
        return timerOffSet;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(message);
        dest.writeString(endTime);
        dest.writeString(timerOffSet);
    }
}
