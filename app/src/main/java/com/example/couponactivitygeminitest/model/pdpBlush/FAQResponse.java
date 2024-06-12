package com.example.couponactivitygeminitest.model.pdpBlush;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FAQResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("productID")
    private String productID;
    @SerializedName("productName")
    private String productName;
    @SerializedName("categoryName")
    private String categoryName;
    @SerializedName("stockStatus")
    private String stockStatus;

    @SerializedName("qna")
    private List<FAQQuestionAnswersResponse> FAQQuestionAnswersResponse;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<FAQQuestionAnswersResponse> getFAQQuestionAnswersResponse() {
        return FAQQuestionAnswersResponse;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getStockStatus() {
        return stockStatus;
    }
}
