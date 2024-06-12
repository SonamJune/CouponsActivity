package com.manash.purpllebase.model.common;

import com.google.gson.annotations.SerializedName;

public class DataPricing {
    private String id;
    @SerializedName("price")
    private String mrp;
    @SerializedName("our_price")
    private String ourPrice;
    @SerializedName("offer_price")
    private String offerPrice;
    @SerializedName("stock_status")
    private String stockStatus;
    @SerializedName("is_elite_membership")
    private int isEliteMembership;
    @SerializedName("category_name")
    private String categoryName;
    @SerializedName("brand_id")
    private String brandId;
    @SerializedName("brand_name")
    private String brandName;
    @SerializedName("category_id")
    private String categoryId;

    public String getId() {
        return id;
    }

    public String getMrp() {
        return mrp;
    }

    public String getOurPrice() {
        return ourPrice;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public int getIsEliteMembership() {
        return isEliteMembership;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }
    public void setOurPrice(String ourPrice) {
        this.ourPrice = ourPrice;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
