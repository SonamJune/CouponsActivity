package com.manash.purpllebase.model.common.user;

import com.google.gson.annotations.SerializedName;

public class PostalCodeResponse {
    private String Type;
    private int status;
    private String message;
    private Area area;
    @SerializedName("delivery_by")
    private String deliveryBy;
    @SerializedName("x_id")
    private String xId;
    @SerializedName("min_date_event")
    private int minDateEvent;
    @SerializedName("max_date_event")
    private int maxDateEvent;
    @SerializedName("pincode")
    private int pincode;
    @SerializedName("city")
    private String city;

    @SerializedName("isShowMessage")
    private boolean isShowMessage;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public boolean getIsShowMessage() {
        return isShowMessage;
    }

    public void setShowMessage(boolean showMessage) {
        isShowMessage = showMessage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getDeliveryBy() {
        return deliveryBy;
    }

    public void setDeliveryBy(String deliveryBy) {
        this.deliveryBy = deliveryBy;
    }

    public int getMinDateEvent() {
        return minDateEvent;
    }

    public void setMinDateEvent(int minDateEvent) {
        this.minDateEvent = minDateEvent;
    }

    public int getMaxDateEvent() {
        return maxDateEvent;
    }

    public void setMaxDateEvent(int maxDateEvent) {
        this.maxDateEvent = maxDateEvent;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getXId() {
        return xId;
    }

    public void setXId(String xId) {
        this.xId = xId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
