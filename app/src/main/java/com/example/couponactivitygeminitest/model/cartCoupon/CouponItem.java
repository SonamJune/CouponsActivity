package com.example.couponactivitygeminitest.model.cartCoupon;

import com.google.gson.annotations.SerializedName;

public class CouponItem {
    private int viewType;
    private String title;
    @SerializedName("coupon_message")
    private String couponMessage;

    @SerializedName("is_selected")
    private String isSelected;

    @SerializedName("coupon_text")
    private String couponText;

    @SerializedName("coupon_message_detail")
    private String couponMessageDetail;

    @SerializedName("coupon_id")
    private String couponId;

    @SerializedName("coupon_code")
    private String couponCode;

    @SerializedName("coupon_detail_link")
    private String couponDetailLink;

    @SerializedName("coupon_detail_deeplink")
    private String couponDetailDeeplink;

    @SerializedName("coupon_expiry")
    private String couponExpiry;

    private boolean isValid;


    public CouponItem() {
    }

    public String getCouponMessage() {
        return couponMessage;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public String getCouponText() {
        return couponText;
    }

    public String getCouponMessageDetail() {
        return couponMessageDetail;
    }

    public String getCouponId() {
        return couponId;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public String getCouponDetailLink() {
        return couponDetailLink;
    }

    public String getCouponDetailDeeplink() {
        return couponDetailDeeplink;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public int getViewType() {
        return viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public String getCouponExpiry() {
        return couponExpiry;
    }
}
