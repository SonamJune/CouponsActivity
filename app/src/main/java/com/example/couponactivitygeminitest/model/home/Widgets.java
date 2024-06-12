package com.example.couponactivitygeminitest.model.home;

import com.google.gson.annotations.SerializedName;
import com.example.couponactivitygeminitest.model.common.Items;

import java.util.ArrayList;


public class Widgets {
    @SerializedName("load_type")
    private String loadType;

    @SerializedName("widget_type")
    private String widgetType;

    private String slot;

    @SerializedName("item_type")
    private String itemType;

    private String title;

    private String description;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName(value = "widget_items", alternate = {"widget_item"})
    private ArrayList<WidgetItems> widgetItems;

    @SerializedName("api_url")
    private String apiUrl;

    @SerializedName("hex_code")
    private String hexCode;

    @SerializedName("deep_link_url")
    private String deepLinkUrl;

    @SerializedName("widget_id")
    private String widgetId;
    @SerializedName("x_id")
    private String exId;

    @SerializedName("border_image")
    private String borderImage;

    @SerializedName("items")
    private ArrayList<Items> items;

    @SerializedName("text")
    private String HeaderText;

    private String deeplink;

    private String style;

    @SerializedName("view_group")
    private int viewGroup;

    @SerializedName("property_m_aspect_ratio")
    private String skeletonAspectRatio;

    @SerializedName("view_type")
    private String viewType;
    @SerializedName("user_group")
    private String userGroup;
    @SerializedName("widget_reload")
    private String widgetReload;
    @SerializedName("margin")
    private String margin;
    @SerializedName("link_text")
    private String linkText;
    @SerializedName("bg_hex")
    private String bgHex;
    @SerializedName("property_d_aspect_ratio")
    private String propertyDAspectRatio;

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getWidgetReload() {
        return widgetReload;
    }

    public void setWidgetReload(String widgetReload) {
        this.widgetReload = widgetReload;
    }

    public String getMargin() {
        return margin;
    }

    public void setMargin(String margin) {
        this.margin = margin;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    public String getBgHex() {
        return bgHex;
    }

    public void setBgHex(String bgHex) {
        this.bgHex = bgHex;
    }

    public String getPropertyDAspectRatio() {
        return propertyDAspectRatio;
    }

    public void setPropertyDAspectRatio(String propertyDAspectRatio) {
        this.propertyDAspectRatio = propertyDAspectRatio;
    }

    public String getWidgetType() {
        return widgetType;
    }

    public String getSlot() {
        return slot;
    }

    public String getItemType() {
        return itemType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ArrayList<WidgetItems> getWidgetItems() {
        return widgetItems;
    }


    public String getApiUrl() {
        return apiUrl;
    }


    public String getHexCode() {
        return hexCode;
    }

    public String getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public String getWidgetId() {
        return widgetId;
    }

    public String getExId() {
        return exId;
    }

    public String getBorderImage() {
        return borderImage;
    }

    public ArrayList<Items> getItems() {
        return items;
    }

    public String getLoadType() {
        return loadType;
    }

    public String getHeaderText() {
        return HeaderText;
    }

    public String getDeeplink() {
        return deeplink;
    }

    public String getStyle() {
        return style;
    }

    public int getViewGroup() {
        return viewGroup;
    }

    public String getSkeletonAspectRatio() {
        return skeletonAspectRatio;
    }
}

