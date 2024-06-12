package com.example.couponactivitygeminitest.model.ItemDetail;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Images implements Parcelable {
    private String num;
    private String thumbImage;
    private String smallImage;
    private String primaryImage;

    private String name;
    @SerializedName("image_url")
    private String imageUrl;
    private int viewType;
    private String descriptionUrl;

    public Images() {
    }

    protected Images(Parcel in) {
        num = in.readString();
        thumbImage = in.readString();
        smallImage = in.readString();
        primaryImage = in.readString();
        name = in.readString();
        imageUrl = in.readString();
        viewType = in.readInt();
    }

    public static final Creator<Images> CREATOR = new Creator<Images>() {
        @Override
        public Images createFromParcel(Parcel in) {
            return new Images(in);
        }

        @Override
        public Images[] newArray(int size) {
            return new Images[size];
        }
    };

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(num);
        parcel.writeString(thumbImage);
        parcel.writeString(smallImage);
        parcel.writeString(primaryImage);
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeInt(viewType);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }


    public String getDescriptionUrl() {
        return descriptionUrl;
    }

    public void setDescriptionUrl(String descriptionUrl) {
        this.descriptionUrl = descriptionUrl;
    }
}
