package com.example.couponactivitygeminitest.model;

import com.google.gson.annotations.SerializedName;

public class ButtonDetails {
    private String link;

    private String text;

    private String action;

    private String coupon;

    @SerializedName("free_gift_button_text")
    private String chooseGiftButtonText;

    @SerializedName("free_gift_button_unlock")
    private int chooseGiftUnlock;

    @SerializedName("gift_added_button_text")
    private String giftAddedButtonText;

    public String getLink() {
        return link;
    }

    public String getText() {
        return text;
    }

    public String getAction() {
        return action;
    }

    public String getChooseGiftButtonText() {
        return chooseGiftButtonText;
    }

    public int isChooseGiftUnlocked() {
        return chooseGiftUnlock;
    }

    public void setChooseGiftUnlock(int chooseGiftUnlock) {
        this.chooseGiftUnlock = chooseGiftUnlock;
    }

    public void setGiftAddedButtonText(String giftAddedButtonText) {
        this.giftAddedButtonText = giftAddedButtonText;
    }

    public String getGiftAddedButtonText() {
        return giftAddedButtonText;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getCoupon() {
        return coupon;
    }
}
