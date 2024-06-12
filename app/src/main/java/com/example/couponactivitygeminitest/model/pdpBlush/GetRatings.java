package com.example.couponactivitygeminitest.model.pdpBlush;

import com.google.gson.annotations.SerializedName;

public class GetRatings {

    @SerializedName("apiUrl")
    private String apiUrl;

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
