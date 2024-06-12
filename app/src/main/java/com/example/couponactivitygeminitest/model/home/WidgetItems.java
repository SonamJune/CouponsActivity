package com.example.couponactivitygeminitest.model.home;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.couponactivitygeminitest.model.common.ListingItem;

public class WidgetItems extends ListingItem implements Parcelable {
    @SerializedName("item_type")
    private String itemType;

    @SerializedName("end_date")
    private String endDate;

    private String text;
    private String id;

    @SerializedName("promoid")
    private String promoId;

    private String description;

    private String name;

    @SerializedName("web_url")
    private String webUrl;


    @SerializedName("visibility_id")
    private String visibilityId;


    private String slot;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName(value = "aspect_ratio", alternate = {"m_aspect_ratio"})
    private String aspectRatio;

    @SerializedName(value = "deep_link_url", alternate = {"link", "target","link_deeplink"})
    private String deepLinkUrl;

    @SerializedName("tag")
    private String tag;

    @SerializedName("title")
    private String title;


    @SerializedName(value = "image_url", alternate = {"primary_image_url", "image", "m_image"})
    private String imageUrl;
    private boolean isImpressionSent;


    private boolean idChecked;
    @SerializedName("heading")
    @Expose
    private String heading;
    @SerializedName("is_applied")
    @Expose
    private int isApplied;
    @SerializedName("tnc")
    @Expose
    private String tnc;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("discount_image")
    @Expose
    private String discountImage;
    @SerializedName("discount_code")
    @Expose
    private String discountCode;

    @SerializedName("is_app")
    private int isApp;

//    @SerializedName("meta")
//    private EventMetaData eventData;

    private String featureType;

    @SerializedName("d_image")
    private String dImage;
    @SerializedName("link_url")
    private String linkUrl;
    @SerializedName("is_desktop")
    private int isDesktop;
    @SerializedName("is_mobile")
    private int isMobile;
    @NonNull
    @SerializedName("third_party_recommendation")
    JsonElement thirdPartyEventParams;
    @SerializedName("d_aspect_ratio")
    private String dAspectRatio;

    public WidgetItems() {

    }

    protected WidgetItems(Parcel in) {
        itemType = in.readString();
        endDate = in.readString();
        text = in.readString();
        id = in.readString();
        promoId = in.readString();
        description = in.readString();
        name = in.readString();
        webUrl = in.readString();
        visibilityId = in.readString();
        slot = in.readString();
        startDate = in.readString();
        aspectRatio = in.readString();
        deepLinkUrl = in.readString();
        tag = in.readString();
        title = in.readString();
        imageUrl = in.readString();
        isImpressionSent = in.readByte() != 0;
        heading = in.readString();
        isApplied = in.readInt();
        tnc = in.readString();
        expiryDate = in.readString();
        discountImage = in.readString();
        discountCode = in.readString();
        featureType = in.readString();
    }

    public static final Creator<WidgetItems> CREATOR = new Creator<WidgetItems>() {
        @Override
        public WidgetItems createFromParcel(Parcel in) {
            return new WidgetItems(in);
        }

        @Override
        public WidgetItems[] newArray(int size) {
            return new WidgetItems[size];
        }
    };

    public String getEndDate() {
        return endDate;
    }

    public String getText() {
        return text;
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public String getVisibilityId() {
        return visibilityId;
    }

    public String getSlot() {
        return slot;
    }


    public String getAspectRatio() {
        return aspectRatio;
    }

    public String getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public String getTag() {
        return tag;
    }

    public String getTitle() {
        return title;
    }


    public boolean isImpressionSent() {
        return isImpressionSent;
    }

    public void setImpressionSent(boolean impressionSent) {
        isImpressionSent = impressionSent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemType() {
        return itemType;
    }

    public String getId() {
        return id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemType);
        parcel.writeString(endDate);
        parcel.writeString(text);
        parcel.writeString(id);
        parcel.writeString(promoId);
        parcel.writeString(description);
        parcel.writeString(name);
        parcel.writeString(webUrl);
        parcel.writeString(visibilityId);
        parcel.writeString(slot);
        parcel.writeString(startDate);
        parcel.writeString(aspectRatio);
        parcel.writeString(deepLinkUrl);
        parcel.writeString(tag);
        parcel.writeString(title);
        parcel.writeString(imageUrl);
        parcel.writeByte((byte) (isImpressionSent ? 1 : 0));
        parcel.writeString(heading);
        parcel.writeInt(isApplied);
        parcel.writeString(tnc);
        parcel.writeString(expiryDate);
        parcel.writeString(discountImage);
        parcel.writeString(discountCode);
        parcel.writeString(featureType);
    }

    public String getdImage() {
        return dImage;
    }

    public void setdImage(String dImage) {
        this.dImage = dImage;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public int getIsDesktop() {
        return isDesktop;
    }

    public void setIsDesktop(int isDesktop) {
        this.isDesktop = isDesktop;
    }

    public int getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(int isMobile) {
        this.isMobile = isMobile;
    }

    public JsonObject getThirdPartyEventParams() {
        if (!(thirdPartyEventParams instanceof JsonNull)) {
            JsonObject thirdPartyEventParamsObject = (JsonObject) thirdPartyEventParams;
            return thirdPartyEventParamsObject;
        }
        return null;
    }

    public String getdAspectRatio() {
        return dAspectRatio;
    }

    public void setdAspectRatio(String dAspectRatio) {
        this.dAspectRatio = dAspectRatio;
    }

    public String getHeading() {
        return heading;
    }

    public int getIsApplied() {
        return isApplied;
    }

    public void setIsApplied(int isApplied) {
        this.isApplied = isApplied;
    }

    public String getTnc() {
        return tnc;
    }

    public String getDiscountImage() {
        return discountImage;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

//    public EventMetaData getEventData() {
//        return eventData;
//    }

    public int getIsApp() {
        return isApp;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }
}
