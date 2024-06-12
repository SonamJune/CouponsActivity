package com.example.couponactivitygeminitest.model.cartCoupon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rahul D on 1/4/18.
 */

public class Coupons {
    private List<CouponItem> valid;

    @SerializedName("not_valid")
    private List<CouponItem> notValid;

    public List<CouponItem> getValid() {
        return valid;
    }

    public List<CouponItem> getNotValid() {
        return notValid;
    }
}
