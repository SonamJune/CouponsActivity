package com.example.couponbase.model.common.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.couponbase.model.common.AlertMessage;

import java.util.List;

public class AddressResponse {
    private String status;
    private String message;
    private List<Address> addresses;
    @SerializedName("str")
    @Expose
    private String serviceMsg;
    @SerializedName("alert_message")
    private AlertMessage alertMessage;

    private String moduleType;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getServiceMsg() {
        return serviceMsg;
    }

    public AlertMessage getAlertMessage() {
        return alertMessage;
    }

    public String getModuleType() {
        return moduleType;
    }
}
