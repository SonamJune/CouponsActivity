package com.example.couponactivitygeminitest.model.coupon;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CouponResponse implements Parcelable {
    public static final Creator<CouponResponse> CREATOR = new Creator<CouponResponse>() {
        @Override
        public CouponResponse createFromParcel(Parcel in) {
            return new CouponResponse(in);
        }

        @Override
        public CouponResponse[] newArray(int size) {
            return new CouponResponse[size];
        }
    };
    private String type;
    private String status;
    private String message;
    private String cartId;
    private String cartCount;
    private String discountCode;
    private String total;
    private String subTotal;
    private String shippingCost;
    private String discountTotal;
    private String cartItems;
    private String statusMessage;
    @SerializedName("popup_notification")
    private PopupNotification popupNotification;
    @SerializedName("upsell_sticky_applied_message")
    private String appliedCouponMsg;
    @SerializedName("upsell_stiky_removed_message")
    private String removeCouponMsg;

    protected CouponResponse(Parcel in) {
        this.type = in.readString();
        this.status = in.readString();
        this.message = in.readString();
        this.cartCount = in.readString();
        this.discountCode = in.readString();
        this.total = in.readString();
        this.subTotal = in.readString();
        this.shippingCost = in.readString();
        this.discountTotal = in.readString();
        this.cartItems = in.readString();
        this.statusMessage = in.readString();
        popupNotification = in.readParcelable(PopupNotification.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(type);
        parcel.writeString(status);
        parcel.writeString(message);
        parcel.writeString(cartCount);
        parcel.writeString(discountCode);
        parcel.writeString(total);
        parcel.writeString(subTotal);
        parcel.writeString(shippingCost);
        parcel.writeString(discountTotal);
        parcel.writeString(cartItems);
        parcel.writeString(statusMessage);
        parcel.writeParcelable(popupNotification, flags);
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCartId() {
        return cartId;
    }

    public String getCartCount() {
        return cartCount;
    }

    public String getTotal() {
        return total;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public String getDiscountTotal() {
        return discountTotal;
    }

    public String getCartItems() {
        return cartItems;
    }

    public void setPopupNotification(PopupNotification popupNotification) {
        this.popupNotification = popupNotification;
    }

    public PopupNotification getPopupNotification() {
        return popupNotification;
    }

    public String getAppliedCouponMsg() {
        return appliedCouponMsg;
    }

    public String getRemoveCouponMsg() {
        return removeCouponMsg;
    }
}
