package com.example.couponactivitygeminitest.model.common;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.SpannableStringBuilder;

import androidx.annotation.NonNull;

import com.example.couponbase.model.common.DataPricing;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

public class Items extends ListingItem implements Parcelable {

    @SerializedName("item_type")
    private String itemType;

    @SerializedName("story_type")
    private String storyType;

    private String title;



    @SerializedName("video_id")
    private String videoId;


//    @SerializedName("data_image")
//    private ArrayList<DataImage> dataImage;
    private String category_id;
    @SerializedName("brand_id")
    private String brandId;
    @SerializedName("brand_name")
    private String brandName;

    private String id;

    @SerializedName("data_pricing")
    private DataPricing dataPricing;

    @SerializedName("is_high_match")
    private String isHighMatch;

//    @SerializedName("data_socialaction")
//    private DataSocialAction dataSocialaction;

    private String category_name;

    private String name;

    @SerializedName("stock_status")
    private String stockStatus;

    private boolean isImpressionSent;

    private int isInCart;


    @SerializedName("isliked")
    private int isLiked;

//    @SerializedName("image_url")
//    private Image image;

    @SerializedName("aspect_ratio")
    private String aspectRatio;

    @SerializedName("match_text")
    private String highMatchText;

//    @SerializedName("highlight_text")
//    private HighlightText highlightText;
//
//    @SerializedName("highlight_text_new")
//    private ArrayList<Entities> highlightsNew;

    @SerializedName("match_text_color")
    private String matchTextColor;


    @SerializedName("match_text_type")
    private String matchType;

    private String widgetId;


    private StringBuilder productBenefitsText;
    private boolean idChecked;

//    private ArrayList<QuestionChart> sentiments;
//
//    @SerializedName("product_groups")
//    private ArrayList<ProductGroup> productGroups;

    private int parentPosition;
//    @SerializedName("offers_pd")
//    private OfferPD offerPd;
    private SpannableStringBuilder sentimentsString;
    private SpannableStringBuilder productGroupString;

//    @SerializedName("multiple_variants")
//    private ArrayList<MultipleVariants> variants;

    @SerializedName("product_tag")
    private String producTag;

    @SerializedName("grammage")
    private String grammage;

    @SerializedName("iselite")
    private int isElite;

    @NonNull
    @SerializedName("third_party_recommendation")
    JsonElement thirdPartyEventParams;

    @SerializedName("d_image")
    private String dImage;
    @SerializedName("m_image")
    private String mImage;
    @SerializedName("link_url")
    private String linkUrl;
//    @SerializedName("meta")
//    private EventMetaData eventData;
    @SerializedName("primary_image_url")
    private String primaryImageUrl;
    @SerializedName("thumb_image_url")
    private String thumbImageUrl;
    @SerializedName("x_id")
    private String itemXId;
    @SerializedName("target_entity_tag")
    private String targetEntityTag;
//    private ArrayList<ProductImages> productImages;
    private String experimentalId;

    private int isTryOn;
    @SerializedName("is_show_arrow_button")
    private int isShowArrowButton;
    @SerializedName("arrow_button_text")
    private String arrowButtonText;

    @SerializedName("is_try_on")
    private int checkTryOn;

    @SerializedName("is_try_on_deeplink")
    private String isTryOnDeepLink;

    @SerializedName("is_try_on_show_icon")
    private int isTryOnShowIcon;

    @SerializedName("is_try_on_url")
    private String isTryOnUrl;


    @SerializedName("is_try_on_icon_url")
    private String isTryOnIconUrl;

    @SerializedName("is_try_on_show_tooltip")
    private int isTryOnShowToolTip;

    @SerializedName("is_try_on_tooltip_text")
    private String isTryOnShowToolTipText;



    public int getIsTryOn() {
        return isTryOn;
    }

    public void setIsTryOn(int isTryOn) {
        this.isTryOn = isTryOn;
    }

    public String getExperimentalId() {
        return experimentalId;
    }

    public void setExperimentalId(String experimentalId) {
        this.experimentalId = experimentalId;
    }

//    public ArrayList<ProductImages> getProductImages() {
//        return productImages;
//    }
//
//    public void setProductImages(ArrayList<ProductImages> productImages) {
//        this.productImages = productImages;
//    }

    private boolean isRecommendation;

    public Items() {

    }

    protected Items(Parcel in) {
        itemType = in.readString();
        storyType = in.readString();
        title = in.readString();
        videoId = in.readString();
        //dataImage = in.createTypedArrayList(DataImage.CREATOR);
        category_id = in.readString();
        brandId = in.readString();
        brandName = in.readString();
        id = in.readString();
        dataPricing = in.readParcelable(DataPricing.class.getClassLoader());
        isHighMatch = in.readString();
        //dataSocialaction = in.readParcelable(DataSocialAction.class.getClassLoader());
        category_name = in.readString();
        name = in.readString();
        stockStatus = in.readString();
        isInCart = in.readInt();
        isLiked = in.readInt();
        idChecked = in.readByte() != 0;
//        offerPd = in.readParcelable(OfferPD.class.getClassLoader());
//        variants = in.createTypedArrayList(MultipleVariants.CREATOR);
        isElite = in.readInt();
        displayType = in.readInt();
        producTag = in.readString();
        grammage = in.readString();
        thumbImageUrl = in.readString();
        primaryImageUrl = in.readString();
        itemXId = in.readString();
        targetEntityTag = in.readString();
        isShowArrowButton=in.readInt();
        arrowButtonText= in.readString();
        checkTryOn= in.readInt();
        isTryOnDeepLink= in.readString();
        isTryOnShowIcon= in.readInt();
        isTryOnUrl= in.readString();
        isTryOnIconUrl= in.readString();
        isTryOnShowToolTip= in.readInt();
        isTryOnShowToolTipText= in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(itemType);
        dest.writeString(storyType);
        dest.writeString(title);
        dest.writeString(videoId);
//        dest.writeTypedList(dataImage);
        dest.writeString(category_id);
        dest.writeString(brandId);
        dest.writeString(brandName);
        dest.writeString(id);
//        dest.writeParcelable(dataPricing, flags);
        dest.writeString(isHighMatch);
//        dest.writeParcelable(dataSocialaction, flags);
        dest.writeString(category_name);
        dest.writeString(name);
        dest.writeString(stockStatus);
        dest.writeInt(isInCart);
        dest.writeInt(isLiked);
        dest.writeByte((byte) (idChecked ? 1 : 0));
//        dest.writeParcelable(offerPd, flags);
//        dest.writeTypedList(variants);
        dest.writeInt(isElite);
        dest.writeInt(displayType);
        dest.writeString(producTag);
        dest.writeString(grammage);
        dest.writeString(thumbImageUrl);
        dest.writeString(primaryImageUrl);
        dest.writeString(itemXId);
        dest.writeString(targetEntityTag);
        dest.writeInt(isShowArrowButton);
        dest.writeString(arrowButtonText);
        dest.writeInt(checkTryOn);
        dest.writeString(isTryOnDeepLink);
        dest.writeInt(isTryOnShowIcon);
        dest.writeString(isTryOnUrl);
        dest.writeString(isTryOnIconUrl);
        dest.writeInt(isTryOnShowToolTip);
        dest.writeString(isTryOnShowToolTipText);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Items> CREATOR = new Creator<Items>() {
        @Override
        public Items createFromParcel(Parcel in) {
            return new Items(in);
        }

        @Override
        public Items[] newArray(int size) {
            return new Items[size];
        }
    };

    public String getItemType() {
        return itemType;
    }

    public String getStoryType() {
        return storyType;
    }


    public String getTitle() {
        return title;
    }

    public String getVideoId() {
        return videoId;
    }

//    public void setDataImage(ArrayList<DataImage> dataImage) {
//        this.dataImage = dataImage;
//    }

//    public ArrayList<DataImage> getDataImage() {
//        return dataImage;
//    }

    public String getCategory_id() {
        return category_id;
    }

    public String getBrandId() {
        return brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getId() {
        return id;
    }

//    public DataPricing getDataPricing() {
//        return dataPricing;
//    }

    public String getIsHighMatch() {
        return isHighMatch;
    }

//    public DataSocialAction getDataSocialaction() {
//        return dataSocialaction;
//    }

    public String getCategory_name() {
        return category_name;
    }

    public String getName() {
        return name;
    }

    public String getStockStatus() {
        return stockStatus;
    }

    public boolean isImpressionSent() {
        return isImpressionSent;
    }

    public void setImpressionSent(boolean impressionSent) {
        isImpressionSent = impressionSent;
    }

    public int getIsInCart() {
        return isInCart;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }

    public void setIsInCart(int isInCart) {
        this.isInCart = isInCart;
    }

//    public Image getImage() {
//        return image;
//    }

    public String getAspectRatio() {
        return aspectRatio;
    }

    public String getHighMatchText() {
        return highMatchText;
    }

//    public HighlightText getHighlightText() {
//        return highlightText;
//    }
//
//    public ArrayList<QuestionChart> getSentiments() {
//        return sentiments;
//    }
//
//    public ArrayList<ProductGroup> getProductGroups() {
//        return productGroups;
//    }
//
//    public void setProductGroups(ArrayList<ProductGroup> productGroups) {
//        this.productGroups = productGroups;
//    }

    public int getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    public SpannableStringBuilder getSentimentsString() {
        return sentimentsString;
    }

    public void setSentimentsString(SpannableStringBuilder sentimentsString) {
        this.sentimentsString = sentimentsString;
    }

    public SpannableStringBuilder getProductGroupString() {
        return productGroupString;
    }

    public void setProductGroupString(SpannableStringBuilder productGroupString) {
        this.productGroupString = productGroupString;
    }


    public boolean isIdChecked() {
        return idChecked;
    }

    public void setIdChecked(boolean idChecked) {
        this.idChecked = idChecked;
    }

//    public OfferPD getOfferPd() {
//        return offerPd;
//    }

    public String getMatchTextColor() {
        return matchTextColor;
    }

//    public ArrayList<Entities> getHighlightsNew() {
//        return highlightsNew;
//    }

    public StringBuilder getProductBenefitsText() {
        return productBenefitsText;
    }

    public void setProductBenefitsText(StringBuilder productBenefitsText) {
        this.productBenefitsText = productBenefitsText;
    }

    public String getMatchType() {
        return matchType;
    }


//    public ArrayList<MultipleVariants> getVariants() {
//        return variants;
//    }
//
//    public void setVariants(ArrayList<MultipleVariants> variants) {
//        this.variants = variants;
//    }

    public String getWidgetId() {
        return widgetId;
    }

    public void setWidgetId(String widgetId) {
        this.widgetId = widgetId;
    }

    public int getIsElite() {
        return isElite;
    }

    public void setIsElite(int isElite) {
        this.isElite = isElite;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setDataPricing(DataPricing dataPricing) {
//        this.dataPricing = dataPricing;
//    }
//
//    public void setDataSocialaction(DataSocialAction dataSocialaction) {
//        this.dataSocialaction = dataSocialaction;
//    }

    public void setStockStatus(String stockStatus) {
        this.stockStatus = stockStatus;
    }

    public void setId(String id) {
        this.id = id;
    }

    public JsonObject getThirdPartyEventParams() {
        if (!(thirdPartyEventParams instanceof JsonNull)) {
            JsonObject thirdPartyEventParamsObject = (JsonObject) thirdPartyEventParams;
            return thirdPartyEventParamsObject;
        }
        return null;
    }
    /**
     * Functions to get the visibility status
     * @return - True - in case visibility status is GONE
     */
    public boolean getGoneSoldOutStatus() {
        if( stockStatus != null || stockStatus.equalsIgnoreCase("0"))return false;
        return true;
    }

    public boolean getGoneEliteStatus() {
        return getIsElite() == 0;
    }

//    public boolean getGoneRatingStatus() {
//        return dataSocialaction.getRatingAvg()==null || dataSocialaction.getRatingAvg().equalsIgnoreCase("0");
//    }
//
//    public boolean getGoneItemOfferStatus() {
//        if(getOfferPd() == null || getOfferPd().getCount() <= 0)return true;
//        else return false;
//    }

//    public boolean getGoneVariantsStatus() {
//        if(getVariants()==null || getVariants().isEmpty()) return true;
//        else return false;
//    }

//    public EventMetaData getEventData() {
//        return eventData;
//    }
//
//    public void setEventData(EventMetaData eventData) {
//        this.eventData = eventData;
//    }

    public String getProducTag() {
        return producTag;
    }

    public String getGrammage() {
        return grammage;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public String getThumbImageUrl() {
        return thumbImageUrl;
    }

    public String getItemXId() {
        return itemXId;
    }

    public void setItemXId(String itemXId) {
        this.itemXId = itemXId;
    }

    public String getTargetEntityTag() {
        return targetEntityTag;
    }

    public boolean isRecommendation() {
        return isRecommendation;
    }

    public void setRecommendation(boolean recommendation) {
        isRecommendation = recommendation;
    }

    public int getIsShowArrowButton() {
        return isShowArrowButton;
    }

    public String getArrowButtonText() {
        return arrowButtonText;
    }


    public String getIsTryOnDeepLink() {
        return isTryOnDeepLink;
    }

    public void setIsTryOnDeepLink(String isTryOnDeepLink) {
        this.isTryOnDeepLink = isTryOnDeepLink;
    }

    public int getIsTryOnShowIcon() {
        return isTryOnShowIcon;
    }

    public void setIsTryOnShowIcon(int isTryOnShowIcon) {
        this.isTryOnShowIcon = isTryOnShowIcon;
    }

    public String getIsTryOnUrl() {
        return isTryOnUrl;
    }

    public void setIsTryOnUrl(String isTryOnUrl) {
        this.isTryOnUrl = isTryOnUrl;
    }

    public String getIsTryOnIconUrl() {
        return isTryOnIconUrl;
    }

    public void setIsTryOnIconUrl(String isTryOnIconUrl) {
        this.isTryOnIconUrl = isTryOnIconUrl;
    }

    public int getIsTryOnShowToolTip() {
        return isTryOnShowToolTip;
    }

    public void setIsTryOnShowToolTip(int isTryOnShowToolTip) {
        this.isTryOnShowToolTip = isTryOnShowToolTip;
    }

    public String getIsTryOnShowToolTipText() {
        return isTryOnShowToolTipText;
    }

    public void setIsTryOnShowToolTipText(String isTryOnShowToolTipText) {
        this.isTryOnShowToolTipText = isTryOnShowToolTipText;
    }

    public int getCheckTryOn() {
        return checkTryOn;
    }

    public void setCheckTryOn(int checkTryOn) {
        this.checkTryOn = checkTryOn;
    }
}
