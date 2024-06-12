package com.example.couponactivitygeminitest.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.couponactivitygeminitest.model.home.WidgetItems;

import java.util.List;

public class ListingItem implements Parcelable {
    public static final Creator<ListingItem> CREATOR = new Creator<ListingItem>() {
        @Override
        public ListingItem createFromParcel(Parcel in) {
            return new ListingItem(in);
        }

        @Override
        public ListingItem[] newArray(int size) {
            return new ListingItem[size];
        }
    };
    public int displayType;
//    private List<OfferItem> offerItems;
    private List<WidgetItems> banners;
//    private VisualFilter smartFilters;
//    private List<FilterTag> filterTags;
    private String headerTitle;
    private int index;
    private String deepLinkUrl;
//    private Personalization userPersonalizationData;
    private String xId;
//    private ArrayList<FilterItem> filters;
    private String visualFilterType;
    private String visualFilterTitle;
    private String itemCount;
    private String apiUrl;
    private boolean isAsyncResponseLoaded;
    private boolean isResponseCombined;
    private int subslotPosition;
    private List<ListingItem> subSlotList;
    private int variantType;

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    private boolean isImpressionFired = false;

    public ListingItem() {
    }

    public boolean isImpressionFired() {
        return isImpressionFired;
    }

    public void setImpressionFired(boolean impressionFired) {
        isImpressionFired = impressionFired;
    }


    protected ListingItem(Parcel in) {
        displayType = in.readInt();
        headerTitle = in.readString();
        index = in.readInt();
        deepLinkUrl = in.readString();
        xId = in.readString();
        visualFilterType = in.readString();
        visualFilterTitle = in.readString();
        variantType = in.readInt();
    }

//    public VisualFilter getSmartFilters() {
//        return smartFilters;
//    }
//
//    public void setSmartFilters(VisualFilter smartFilters) {
//        this.smartFilters = smartFilters;
//    }

    public int getDisplayType() {
        return displayType;
    }

    public void setDisplayType(int displayType) {
        this.displayType = displayType;
    }

    public String getHeaderTitle() {
        return headerTitle;
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle = headerTitle;
    }

//    public List<FilterTag> getFilterTags() {
//        return filterTags;
//    }
//
//    public void setFilterTags(List<FilterTag> filterTags) {
//        this.filterTags = filterTags;
//    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

//    public List<OfferItem> getOfferItems() {
//        return offerItems;
//    }
//
//    public void setOfferItems(List<OfferItem> offerItems) {
//        this.offerItems = offerItems;
//    }

    public String getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public void setDeepLinkUrl(String deepLinkUrl) {
        this.deepLinkUrl = deepLinkUrl;
    }

//    public Personalization getUserPersonalizationData() {
//        return userPersonalizationData;
//    }
//
//    public void setUserPersonalizationData(Personalization userPersonalizationData) {
//        this.userPersonalizationData = userPersonalizationData;
//    }

    public void setXId(String experimentalId) {
        xId = experimentalId;
    }

    public String getxId() {
        return xId;
    }

//    public ArrayList<FilterItem> getFilters() {
//        return filters;
//    }
//
//    public void setFilters(ArrayList<FilterItem> filters) {
//        this.filters = filters;
//    }

    public String getVisualFilterType() {
        return visualFilterType;
    }

    public void setVisualFilterType(String visualFilterType) {
        this.visualFilterType = visualFilterType;

    }

    public String getVisualFilterTitle() {
        return visualFilterTitle;
    }

    public void setVisualFilterTitle(String visualFilterTitle) {
        this.visualFilterTitle = visualFilterTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(displayType);
        dest.writeString(headerTitle);
        dest.writeInt(index);
        dest.writeString(deepLinkUrl);
        dest.writeString(xId);
        dest.writeString(visualFilterType);
        dest.writeString(visualFilterTitle);
        dest.writeInt(variantType);
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public boolean isAsyncResponseLoaded() {
        return isAsyncResponseLoaded;
    }

    public void setAsyncResponseLoaded(boolean asyncResponseLoaded) {
        isAsyncResponseLoaded = asyncResponseLoaded;
    }

    public List<WidgetItems> getBanners() {
        return banners;
    }

    public void setBanners(List<WidgetItems> banners) {
        this.banners = banners;
    }

    public boolean isResponseCombined() {
        return isResponseCombined;
    }

    public void setResponseCombined(boolean responseCombined) {
        isResponseCombined = responseCombined;
    }

    public int getSubslotPosition() {
        return subslotPosition;
    }

    public void setSubslotPosition(int subslotPosition) {
        this.subslotPosition = subslotPosition;
    }

    public List<ListingItem> getSubSlotList() {
        return subSlotList;
    }

    public void setSubSlotList(List<ListingItem> subSlotList) {
        this.subSlotList = subSlotList;
    }

    public int getVariantType() {
        return variantType;
    }

    public void setVariantType(int variantType) {
        this.variantType = variantType;
    }
}
