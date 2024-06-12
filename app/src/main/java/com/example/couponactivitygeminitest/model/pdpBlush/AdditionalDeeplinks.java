package com.example.couponactivitygeminitest.model.pdpBlush;

import com.google.gson.annotations.SerializedName;

public class AdditionalDeeplinks {

    @SerializedName("headerText")
    private String headerText;
    @SerializedName("url")
    private String url;
    @SerializedName("deeplink")
    private String deeplink;
    @SerializedName("apiUrl")
    private String apiUrl;

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public void setDeeplink(String deeplink) {
        this.deeplink = deeplink;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
