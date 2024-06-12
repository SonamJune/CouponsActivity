package com.example.couponactivitygeminitest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatData {
    @SerializedName("chat_init_msg")
    @Expose
    private String chatInitMessage;
    @SerializedName("is_forceful")
    @Expose
    private boolean isForceful;
    @SerializedName("chat_button_text")
    @Expose
    private String buttonMessage;
    @SerializedName("personalized_txt")
    @Expose
    private String personalizedText;
    @SerializedName("personalized_bg_img")
    @Expose
    private String personalizedImage;
    @SerializedName("personalized_title")
    @Expose
    private String personalizedTitle;

    public ChatData() {
    }

    public String getChatInitMessage() {
        return chatInitMessage;
    }

    public void setChatInitMessage(String chatInitMessage) {
        this.chatInitMessage = chatInitMessage;
    }

    public boolean isForceful() {
        return isForceful;
    }

    public void setForceful(boolean forceful) {
        isForceful = forceful;
    }

    public String getButtonMessage() {
        return buttonMessage;
    }

    public void setButtonMessage(String buttonMessage) {
        this.buttonMessage = buttonMessage;
    }

    public String getPersonalizedText() {
        return personalizedText;
    }

    public void setPersonalizedText(String personalizedText) {
        this.personalizedText = personalizedText;
    }

    public String getPersonalizedImage() {
        return personalizedImage;
    }

    public void setPersonalizedImage(String personalizedImage) {
        this.personalizedImage = personalizedImage;
    }

    public String getPersonalizedTitle() {
        return personalizedTitle;
    }

    public void setPersonalizedTitle(String personalizedTitle) {
        this.personalizedTitle = personalizedTitle;
    }
}
