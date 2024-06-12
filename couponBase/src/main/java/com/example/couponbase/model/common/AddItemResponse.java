package com.example.couponbase.model.common;

import com.google.gson.annotations.SerializedName;
import com.example.couponbase.model.common.PopupNotification;

public class AddItemResponse {

    private String type;
    private String status;
    private String message;
    private int count;
    @SerializedName("is_location_error")
    private boolean isLocationError;
    @SerializedName("product_widget")
    private DataPricing dataPricing;
    @SerializedName("cart_id")
    private String cartId;
    @SerializedName("membership_message")
    private String membershipMessage;
    @SerializedName("popup_notification")
    private PopupNotification popupNotification;

    @SerializedName("elite_pro")
    private ElitePro elitePro;

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getCount() {
        return count;
    }

    public String getCartId() {
        return cartId;
    }

    public String getMembershipMessage() {
        return membershipMessage;
    }

    public DataPricing getDataPricing() {
        return dataPricing;
    }

    public PopupNotification getPopupNotification() {
        return popupNotification;
    }

    public void setPopupNotification(PopupNotification popupNotification) {
        this.popupNotification = popupNotification;
    }

    public boolean isLocationError() {
        return isLocationError;
    }

    public void setLocationError(boolean locationError) {
        isLocationError = locationError;
    }

    public ElitePro getElitePro() {
        return elitePro;
    }
}
