package com.example.couponactivitygeminitest.model.error;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    private String status;
    private String message;
    private int statusCode;
    private String action;
    private String moduleType;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getAction() {
        return action;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }
}
