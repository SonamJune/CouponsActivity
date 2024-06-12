package com.example.couponactivitygeminitest.model.ItemDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Availability implements Parcelable {
    private String mrp;
    private String offerPrice;
    private String ourPrice;
    private String stockStatus;
    private int moq;
    private int allSellerIapoCount;
    private int discountPrice;
    private String discount;
    private String specialOfferPrice;
    private String specialOfferText;
    private int totalDiscount;
    private int offerDiscount;
    @SerializedName("iscomplimentary")
    private int isComplimentary;
    private String complimentaryText;
    private String complimentaryTextColorCode;

    public Availability() {

    }

    protected Availability(Parcel in) {
        mrp = in.readString();
        offerPrice = in.readString();
        ourPrice = in.readString();
        stockStatus = in.readString();
        moq = in.readInt();
        discountPrice = in.readInt();
        discount = in.readString();
        specialOfferPrice = in.readString();
        specialOfferText = in.readString();
        totalDiscount = in.readInt();
        offerDiscount = in.readInt();
        isComplimentary = in.readInt();
        complimentaryText = in.readString();
        complimentaryTextColorCode = in.readString();
        allSellerIapoCount = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mrp);
        dest.writeString(offerPrice);
        dest.writeString(ourPrice);
        dest.writeString(stockStatus);
        dest.writeInt(moq);
        dest.writeInt(discountPrice);
        dest.writeString(discount);
        dest.writeString(specialOfferPrice);
        dest.writeString(specialOfferText);
        dest.writeInt(totalDiscount);
        dest.writeInt(offerDiscount);
        dest.writeInt(isComplimentary);
        dest.writeString(complimentaryText);
        dest.writeString(complimentaryTextColorCode);
        dest.writeInt(allSellerIapoCount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Availability> CREATOR = new Creator<Availability>() {
        @Override
        public Availability createFromParcel(Parcel in) {
            return new Availability(in);
        }

        @Override
        public Availability[] newArray(int size) {
            return new Availability[size];
        }
    };

    public int getMoq() {
        return moq;
    }

    public void setMoq(int moq) {
        this.moq = moq;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }


    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }


    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }
    public String getOurPrice() {
        return ourPrice;
    }

    public void setOurPrice(String ourPrice) {
        this.ourPrice = ourPrice;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public String getSpecialOfferPrice() {
        return specialOfferPrice;
    }

    public String getSpecialOfferText() {
        return specialOfferText;
    }

    public int getTotalDiscount() {
        return totalDiscount;
    }

    public int getOfferDiscount() {
        return offerDiscount;
    }

    public int getIsComplimentary() {
        return isComplimentary;
    }

    public void setIsComplimentary(int isComplimentary) {
        this.isComplimentary = isComplimentary;
    }

    public String getComplimentaryText() {
        return complimentaryText;
    }

    public void setComplimentaryText(String complimentaryText) {
        this.complimentaryText = complimentaryText;
    }

    public String getComplimentaryTextColorCode() {
        return complimentaryTextColorCode;
    }

    public void setComplimentaryTextColorCode(String complimentaryTextColorCode) {
        this.complimentaryTextColorCode = complimentaryTextColorCode;
    }

    public int getAllSellerIapoCount() {
        return allSellerIapoCount;
    }
}
