package com.example.couponactivitygeminitest.model.ItemDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.couponactivitygeminitest.model.home.Views;
import com.example.couponactivitygeminitest.model.pdpBlush.AdditionalDeeplinks;
import com.example.couponactivitygeminitest.model.pdpBlush.GetRatings;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ItemDetails implements Parcelable {
    private String itemType;
    private String itemId;
    private String itemCategory;
    private String itemCategoryId;
    private String itembrand;
    private String itemBrandId;
    private String name;
//    private ArrayList<ProductImages> images;
    private Availability availability;
//    @SerializedName("product_info_label")
//    private ProductInfoLabel productInfoLabel;
//    private Seller seller;
    private String description;
//    private ShippingInfo shippingInfo;
    private String offerLabel;
//    private List<OfferWidget> offersWidget;
//    private ReviewResponse reviews;
    private String title;
    private int iselite;
    private String avgRating;
    private String ratingCount;
    private String reviewCount;

    private String eliteShippingText;
//    private CreaterDetails creationDetails;
    private int enableQnaFlag;
    private int minDateEvent;
    private int maxDateEvent;
    private int pincode;
//    private APIDetail descriptionApi;
//    private APIDetail infiniteScrolling;
//    @SerializedName("genuineProduct")
//    private GenuineProduct genuineProduct;
//    @SerializedName("freeDelivery")
//    private FreeDelivery freeDelivery;
//    @SerializedName("freeCodAbove")
//    private FreeCodAbove freeCodAbove;
//    @SerializedName("returnDays")
//    private ReturnDays returnDays;
    @SerializedName("additionalDeeplinks")
    private List<AdditionalDeeplinks> additionalDeeplinks;
    @SerializedName("abSequenceSectionValue")
    private List<String> abSequenceSectionValue;

    public int getEnableQnaFlag() {
        return enableQnaFlag;
    }

    public void setEnableQnaFlag(int enableQnaFlag) {
        this.enableQnaFlag = enableQnaFlag;
    }

    @SerializedName("avRecoUrlV2")
    private String avRecoUrl;

    @SerializedName("avRecoDeeplinkV2")
    private String avRecoDeeplinkV2;

//    public CreaterDetails getCreationDetails() {
//        return creationDetails;
//    }
//    @SerializedName("product_list")
//    private List<TopRankBestSeller> topRankBestSellers;
//
//    @SerializedName(value = "multiple_variants", alternate = {"multipleVariants"})
//    private List<MultipleVariants> multiVariants;


    private int itemCartCount;
    private int itemWishlistCount;
    private boolean showBenifitsFooter;
    private boolean showLooksFooter;
    private boolean showRecoFooter;
    private boolean showReviewFooter;
    private String elitePageDeeplink;
    private String nonEliteShippingTextPrimary;
    private String StickyBarSmallText;

    @SerializedName(value = "promotions")
    private ArrayList<Views> views;
    private String isTryOnView;
    private String isTryOnDeeplinkV2;
//    private TryOnView isTryOnViewV2;

    @SerializedName("isTryOnRevieve")
    private int isTryOn;
    private String toolTipText;
    @SerializedName("is_add_to_cart")
    private boolean isShowAddToCart;
    @SerializedName("is_add_to_wishlist")
    private boolean isShowAddToWishlist;
    @SerializedName("iscomplimentary")
    private int isComplimentary;
    private String complimentaryText;
    private String complimentaryTextColorCode;
    @SerializedName("getRatings")
    private GetRatings getRatings;
    @SerializedName("fbtRecoUrl")
    private String fbtRecoUrl;


    public ItemDetails() {
        /*Default values*/
        isShowAddToCart = true;
        isShowAddToWishlist = true;
    }


    protected ItemDetails(Parcel in) {
        itemType = in.readString();
        itemId = in.readString();
        itemCategory = in.readString();
        itemCategoryId = in.readString();
        itembrand = in.readString();
        itemBrandId = in.readString();
        name = in.readString();
//        images = in.createTypedArrayList(ProductImages.CREATOR);
        availability = in.readParcelable(Availability.class.getClassLoader());
        isComplimentary = in.readInt();
        complimentaryText = in.readString();
        complimentaryTextColorCode = in.readString();
        offerLabel = in.readString();
        iselite = in.readInt();
        avgRating = in.readString();
        ratingCount = in.readString();
        reviewCount = in.readString();
//        creationDetails = in.readParcelable(CreaterDetails.class.getClassLoader());
//        productInfoLabel = in.readParcelable(ProductInfoLabel.class.getClassLoader());
//        isTryOnViewV2 = in.readParcelable(TryOnView.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(itemType);
        parcel.writeString(itemId);
        parcel.writeString(itemCategory);
        parcel.writeString(itemCategoryId);
        parcel.writeString(itembrand);
        parcel.writeString(itemBrandId);
        parcel.writeString(name);
//        parcel.writeTypedList(images);
        parcel.writeParcelable(availability, i);
        parcel.writeInt(isComplimentary);
        parcel.writeString(complimentaryText);
        parcel.writeString(complimentaryTextColorCode);
        parcel.writeString(offerLabel);
        parcel.writeInt(iselite);
        parcel.writeString(avgRating);
        parcel.writeString(ratingCount);
        parcel.writeString(reviewCount);
//        parcel.writeParcelable(creationDetails, i);
//        parcel.writeParcelable(productInfoLabel, i);
//        parcel.writeParcelable(isTryOnViewV2, i);
    }

    public static final Creator<ItemDetails> CREATOR = new Creator<ItemDetails>() {
        @Override
        public ItemDetails createFromParcel(Parcel in) {
            return new ItemDetails(in);
        }

        @Override
        public ItemDetails[] newArray(int size) {
            return new ItemDetails[size];
        }
    };


//    public ReviewResponse getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(ReviewResponse reviews) {
//        this.reviews = reviews;
//    }
//
//    public List<OfferWidget> getOffersWidget() {
//        return offersWidget;
//    }
//
//
//    public ShippingInfo getShippingInfo() {
//        return shippingInfo;
//    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCategory() {
        return itemCategory;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ArrayList<ProductImages> getImages() {
//        return images;
//    }
//
//    public void setImages(ArrayList<ProductImages> images) {
//        this.images = images;
//    }
//
//    public com.manash.purplle.model.ItemDetail.Availability getAvailability() {
//        return availability;
//    }
//
//    public void setAvailability(com.manash.purplle.model.ItemDetail.Availability availability) {
//        this.availability = availability;
//    }
//
//
//    public List<MultipleVariants> getMultiVariants() {
//        return multiVariants;
//    }
//
//
//    public Seller getSeller() {
//        return seller;
//    }


    public String getItemCategoryId() {
        return itemCategoryId;
    }

    public String getItembrand() {
        return itembrand;
    }

    public String getItemBrandId() {
        return itemBrandId;
    }

    public String getTitle() {
        return title;
    }

    public int getIselite() {
        return iselite;
    }

    public void setIselite(int iselite) {
        this.iselite = iselite;
    }

    public String getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(String avgRating) {
        this.avgRating = avgRating;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getReviewCount() {
        return reviewCount;
    }


    public boolean isShowBenifitsFooter() {
        return showBenifitsFooter;
    }

    public boolean isShowLooksFooter() {
        return showLooksFooter;
    }


    public boolean isShowRecoFooter() {
        return showRecoFooter;
    }

    public boolean isShowReviewFooter() {
        return showReviewFooter;
    }


    public String getEliteShippingText() {
        return eliteShippingText;
    }

    public String getElitePageDeeplink() {
        return elitePageDeeplink;
    }


    public String getNonEliteShippingTextPrimary() {
        return nonEliteShippingTextPrimary;
    }


    public String getStickyBarSmallText() {
        return StickyBarSmallText;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setEliteShippingText(String eliteShippingText) {
        this.eliteShippingText = eliteShippingText;
    }

    public void setElitePageDeeplink(String elitePageDeeplink) {
        this.elitePageDeeplink = elitePageDeeplink;
    }

    public void setNonEliteShippingTextPrimary(String nonEliteShippingTextPrimary) {
        this.nonEliteShippingTextPrimary = nonEliteShippingTextPrimary;
    }

    public ArrayList<Views> getViews() {
        return views;
    }

//    public TryOnView getIsTryOnViewV2() {
//        return isTryOnViewV2;
//    }

    public String getIsTryOnView() {
        return isTryOnView;
    }

    public String getIsTryOnDeeplinkV2() {
        return isTryOnDeeplinkV2;
    }

    public int getIsTryOn() {
        return isTryOn;
    }

    public void setIsTryOnView(String isTryOnView) {
        this.isTryOnView = isTryOnView;
    }

    public void setIsTryOn(int isTryOn) {
        this.isTryOn = isTryOn;
    }

    public String getToolTipText() {
        return toolTipText;
    }

    public boolean isShowAddToCart() {
        return isShowAddToCart;
    }

    public boolean isShowAddToWishlist() {
        return isShowAddToWishlist;
    }

//    public List<TopRankBestSeller> getTopRankBestSellers() {
//        return topRankBestSellers;
//    }

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

//    public ProductInfoLabel getProductInfoLabel() {
//        return productInfoLabel;
//    }
//
//    public void setProductInfoLabel(ProductInfoLabel productInfoLabel) {
//        this.productInfoLabel = productInfoLabel;
//    }

    public String getAvRecoUrl() {
        return avRecoUrl;
    }

    public void setAvRecoUrl(String avRecoUrl) {
        this.avRecoUrl = avRecoUrl;
    }

    public String getLinkDeeplink() {
        return avRecoDeeplinkV2;
    }

    public void setLinkDeeplink(String linkDeeplink) {
        this.avRecoDeeplinkV2 = linkDeeplink;
    }

    public int getMinDateEvent() {
        return minDateEvent;
    }

    public void setMinDateEvent(int minDateEvent) {
        this.minDateEvent = minDateEvent;
    }

    public int getMaxDateEvent() {
        return maxDateEvent;
    }

    public void setMaxDateEvent(int maxDateEvent) {
        this.maxDateEvent = maxDateEvent;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

//    public APIDetail getDescriptionApi() {
//        return descriptionApi;
//    }
//
//    public APIDetail getInfiniteScrolling() {
//        return infiniteScrolling;
//    }
//
//    public void setInfiniteScrolling(APIDetail infiniteScrolling) {
//        this.infiniteScrolling = infiniteScrolling;
//    }
//    public GenuineProduct getGenuineProduct() {
//        return genuineProduct;
//    }
//
//    public FreeDelivery getFreeDelivery() {
//        return freeDelivery;
//    }
//
//    public FreeCodAbove getFreeCodAbove() {
//        return freeCodAbove;
//    }
//
//    public ReturnDays getReturnDays() {
//        return returnDays;
//    }
    public List<AdditionalDeeplinks> getAdditionalDeeplinks() {
        return additionalDeeplinks;
    }

    public GetRatings getGetRatings() {
        return getRatings;
    }

    public List<String> getAbSequenceSectionValue() {
        return abSequenceSectionValue;
    }

    public void setAbSequenceSectionValue(List<String> abSequenceSectionValue) {
        this.abSequenceSectionValue = abSequenceSectionValue;
    }

    public String getFbtRecoUrl() {
        return fbtRecoUrl;
    }

}
