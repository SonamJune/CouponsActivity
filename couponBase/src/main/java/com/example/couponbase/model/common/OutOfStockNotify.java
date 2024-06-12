package com.example.couponbase.model.common;

import com.google.gson.annotations.SerializedName;

public class OutOfStockNotify {

    private String status;
    private int id;
    @SerializedName("product_widget")
    private DataPricing dataPricing;

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public DataPricing getDataPricing() {
        return dataPricing;
    }
}
