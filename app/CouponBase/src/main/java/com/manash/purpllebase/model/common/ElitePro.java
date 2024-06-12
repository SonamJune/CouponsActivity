package com.manash.purpllebase.model.common;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ElitePro implements Parcelable {
    @SerializedName("secondary_text")
    private String secondaryText;
    @SerializedName("primary_text")
    private String primaryText;
    @SerializedName("freegift_title")
    private String freegitTitle;

    @SerializedName("freegift_header")
    private String freegiftHeaderIcon;

    @SerializedName("freegift_button_text")
    private String freegiftButtonText;

    @SerializedName("freegift_added_text")
    private String freegiftAddedText;

    @SerializedName("freegift_text_cart_item")
    private String freegiftTextCartItem;
    @SerializedName("is_plan_available")
    private boolean isPlanAvailable;
    @SerializedName("elite_removal_cart_title")
    private String eliteRemovalCartTitle;
    @SerializedName("elite_removal_cart_text")
    private String eliteRemovalCartText;

    @SerializedName("elite_remova_cart_continuebtn_text")
    private String eliteRemovalCartContinueBtnText;

    @SerializedName("elite_removal_cart_removebtn_text")
    private String eliteRemovalCarRemovalBtnText;

    @SerializedName("offer_id")
    private String offerId;

    @SerializedName("congratulations_text")
    private String congratulationsText;

    @SerializedName("freegift_info")
    private String freegiftInfo;

    @SerializedName("choose_gift")
    private String chooseGift;

    @SerializedName("free_description")
    private String freeDescription;

    @SerializedName("choose_joining_gift")
    private String chooseJoiningGift;

    @SerializedName("submit_button_text")
    private String submitButtonText;

    @SerializedName("freegift_celebration_snackbar")
    private String freegiftCelebrationSnackbar;

    @SerializedName("freegift_crown_celebration")
    private String freegiftCrownCelebration;

    @SerializedName("offer_url")
    private String offerUrl;

    @SerializedName("membership_type")
    private String membershipType;

    @SerializedName("membership_id")
    private String membershipId;

    @SerializedName("elite_product_id")
    private String eliteProductId;
    @SerializedName("freegift_not_available_text")
    private String freeGiftNotAvailable;

    protected ElitePro(Parcel in) {
        secondaryText = in.readString();
        primaryText = in.readString();
        freegitTitle = in.readString();
        freegiftHeaderIcon = in.readString();
        freegiftButtonText = in.readString();
        freegiftAddedText = in.readString();
        freegiftTextCartItem = in.readString();
        isPlanAvailable = in.readByte() != 0;
        eliteRemovalCartTitle = in.readString();
        eliteRemovalCartText = in.readString();
        eliteRemovalCartContinueBtnText = in.readString();
        eliteRemovalCarRemovalBtnText = in.readString();
        offerId = in.readString();
        congratulationsText = in.readString();
        freegiftInfo = in.readString();
        chooseGift = in.readString();
        freeDescription = in.readString();
        chooseJoiningGift = in.readString();
        submitButtonText = in.readString();
        freegiftCelebrationSnackbar = in.readString();
        freegiftCrownCelebration = in.readString();
        offerUrl = in.readString();
        membershipType = in.readString();
        membershipId = in.readString();
        eliteProductId = in.readString();
        freeGiftNotAvailable = in.readString();
    }

    public static final Creator<ElitePro> CREATOR = new Creator<ElitePro>() {
        @Override
        public ElitePro createFromParcel(Parcel in) {
            return new ElitePro(in);
        }

        @Override
        public ElitePro[] newArray(int size) {
            return new ElitePro[size];
        }
    };

    public String getMembershipType() {
        return membershipType;
    }

    public String getEliteProductId() {
        return eliteProductId;
    }

    public String getSecondaryText() {
        return secondaryText;
    }

    public String getPrimaryText() {
        return primaryText;
    }

    public String getFreegitTitle() {
        return freegitTitle;
    }

    public String getFreegiftHeaderIcon() {
        return freegiftHeaderIcon;
    }

    public String getFreegiftButtonText() {
        return freegiftButtonText;
    }

    public String getFreegiftAddedText() {
        return freegiftAddedText;
    }

    public String getFreegiftTextCartItem() {
        return freegiftTextCartItem;
    }

    public boolean isPlanAvailable() {
        return isPlanAvailable;
    }

    public String getEliteRemovalCartTitle() {
        return eliteRemovalCartTitle;
    }

    public String getEliteRemovalCartText() {
        return eliteRemovalCartText;
    }

    public String getEliteRemovalCartContinueBtnText() {
        return eliteRemovalCartContinueBtnText;
    }

    public String getEliteRemovalCarRemovalBtnText() {
        return eliteRemovalCarRemovalBtnText;
    }

    public String getOfferId() {
        return offerId;
    }

    public String getCongratulationsText() {
        return congratulationsText;
    }

    public String getFreegiftInfo() {
        return freegiftInfo;
    }

    public String getChooseGift() {
        return chooseGift;
    }

    public String getChooseJoiningGift() {
        return chooseJoiningGift;
    }

    public String getSubmitButtonText() {
        return submitButtonText;
    }

    public String getFreegiftCelebrationSnackbar() {
        return freegiftCelebrationSnackbar;
    }

    public String getFreegiftCrownCelebration() {
        return freegiftCrownCelebration;
    }
    public String getFreeDescription() {
        return freeDescription;
    }

    public void setSecondaryText(String secondaryText) {
        this.secondaryText = secondaryText;
    }

    public void setPrimaryText(String primaryText) {
        this.primaryText = primaryText;
    }

    public void setFreegitTitle(String freegitTitle) {
        this.freegitTitle = freegitTitle;
    }

    public void setFreegiftHeaderIcon(String freegiftHeaderIcon) {
        this.freegiftHeaderIcon = freegiftHeaderIcon;
    }

    public void setFreegiftButtonText(String freegiftButtonText) {
        this.freegiftButtonText = freegiftButtonText;
    }

    public void setFreegiftAddedText(String freegiftAddedText) {
        this.freegiftAddedText = freegiftAddedText;
    }

    public void setFreegiftTextCartItem(String freegiftTextCartItem) {
        this.freegiftTextCartItem = freegiftTextCartItem;
    }

    public void setPlanAvailable(boolean planAvailable) {
        isPlanAvailable = planAvailable;
    }

    public void setEliteRemovalCartTitle(String eliteRemovalCartTitle) {
        this.eliteRemovalCartTitle = eliteRemovalCartTitle;
    }

    public void setEliteRemovalCartText(String eliteRemovalCartText) {
        this.eliteRemovalCartText = eliteRemovalCartText;
    }

    public void setEliteRemovalCartContinueBtnText(String eliteRemovalCartContinueBtnText) {
        this.eliteRemovalCartContinueBtnText = eliteRemovalCartContinueBtnText;
    }

    public void setEliteRemovalCarRemovalBtnText(String eliteRemovalCarRemovalBtnText) {
        this.eliteRemovalCarRemovalBtnText = eliteRemovalCarRemovalBtnText;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public void setCongratulationsText(String congratulationsText) {
        this.congratulationsText = congratulationsText;
    }

    public void setFreegiftInfo(String freegiftInfo) {
        this.freegiftInfo = freegiftInfo;
    }

    public void setChooseGift(String chooseGift) {
        this.chooseGift = chooseGift;
    }

    public void setFreeDescription(String freeDescription) {
        this.freeDescription = freeDescription;
    }

    public void setChooseJoiningGift(String chooseJoiningGift) {
        this.chooseJoiningGift = chooseJoiningGift;
    }

    public void setSubmitButtonText(String submitButtonText) {
        this.submitButtonText = submitButtonText;
    }

    public void setFreegiftCelebrationSnackbar(String freegiftCelebrationSnackbar) {
        this.freegiftCelebrationSnackbar = freegiftCelebrationSnackbar;
    }

    public void setFreegiftCrownCelebration(String freegiftCrownCelebration) {
        this.freegiftCrownCelebration = freegiftCrownCelebration;
    }

    public String getOfferUrl(){
        return offerUrl;
    }

    public String getFreeGiftNotAvailable() {
        return freeGiftNotAvailable;
    }

    public String getMembershipId() {
        return membershipId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(secondaryText);
        dest.writeString(primaryText);
        dest.writeString(freegitTitle);
        dest.writeString(freegiftHeaderIcon);
        dest.writeString(freegiftButtonText);
        dest.writeString(freegiftAddedText);
        dest.writeString(freegiftTextCartItem);
        dest.writeByte((byte) (isPlanAvailable ? 1 : 0));
        dest.writeString(eliteRemovalCartTitle);
        dest.writeString(eliteRemovalCartText);
        dest.writeString(eliteRemovalCartContinueBtnText);
        dest.writeString(eliteRemovalCarRemovalBtnText);
        dest.writeString(offerId);
        dest.writeString(congratulationsText);
        dest.writeString(freegiftInfo);
        dest.writeString(chooseGift);
        dest.writeString(freeDescription);
        dest.writeString(chooseJoiningGift);
        dest.writeString(submitButtonText);
        dest.writeString(freegiftCelebrationSnackbar);
        dest.writeString(freegiftCrownCelebration);
        dest.writeString(offerUrl);
        dest.writeString(membershipType);
        dest.writeString(membershipId);
        dest.writeString(eliteProductId);
        dest.writeString(freeGiftNotAvailable);
    }
}
