package com.example.couponbase.model.common.share;

import android.os.Parcel;
import android.os.Parcelable;

public class ShareResponse implements Parcelable {
    private String type;
    private String status;
    private String message;
    private Url url;
    private String image;
    private String title;
    private String text;

    protected ShareResponse(Parcel in) {
        type = in.readString();
        status = in.readString();
        message = in.readString();
        image = in.readString();
        title = in.readString();
        text = in.readString();
    }

    public static final Creator<ShareResponse> CREATOR = new Creator<ShareResponse>() {
        @Override
        public ShareResponse createFromParcel(Parcel in) {
            return new ShareResponse(in);
        }

        @Override
        public ShareResponse[] newArray(int size) {
            return new ShareResponse[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return message;
    }

    public void setStatusMessage(String statusMessage) {
        this.message = statusMessage;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeString(status);
        dest.writeString(message);
        dest.writeString(image);
        dest.writeString(title);
        dest.writeString(text);
    }
}

