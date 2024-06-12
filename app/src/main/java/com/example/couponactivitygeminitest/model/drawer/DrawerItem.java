package com.example.couponactivitygeminitest.model.drawer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrawerItem {
    private String name;
    @SerializedName(value = "is_drawer", alternate = "if_drawer")
    private int isDrawer;
    @SerializedName(value = "is_slider", alternate = "if_slider")
    private int isSlider;
    @SerializedName("icon_code")
    private String iconCode;
    @SerializedName("deeplink_by_url")
    private String deepLinkUrl;
    @SerializedName("view_type")
    private String viewType;
    @SerializedName("font_type")
    private String fontType;
    @SerializedName("font_color")
    private String fontColor;
    @SerializedName("is_secondary_drawer")
    private int isSecondaryDrawer;
    @SerializedName("is_loggedin_required")
    private int isLoggedinRequired;
    private List<DrawerItem> child;
    private String id;
    private boolean isToolTipShown;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsDrawer() {
        return isDrawer;
    }

    public void setIsDrawer(int isDrawer) {
        this.isDrawer = isDrawer;
    }

    public int getIsSlider() {
        return isSlider;
    }

    public void setIsSlider(int isSlider) {
        this.isSlider = isSlider;
    }

    public String getIconCode() {
        return iconCode;
    }

    public int getIsLoggedinRequired() {
        return isLoggedinRequired;
    }

    public void setIsLoggedinRequired(int isLoggedinRequired) {
        this.isLoggedinRequired = isLoggedinRequired;
    }

    public void setIconCode(String iconCode) {
        this.iconCode = iconCode;
    }

    public String getDeepLinkUrl() {
        return deepLinkUrl;
    }

    public void setDeepLinkUrl(String deepLinkUrl) {
        this.deepLinkUrl = deepLinkUrl;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getFontType() {
        return fontType;
    }


    public String getFontColor() {
        return fontColor;
    }


    public List<DrawerItem> getChild() {
        return child;
    }

    public void setChild(List<DrawerItem> child) {
        this.child = child;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIsSecondaryDrawer() {
        return isSecondaryDrawer;
    }

    public void setIsSecondaryDrawer(int isSecondaryDrawer) {
        this.isSecondaryDrawer = isSecondaryDrawer;
    }

    public boolean isToolTipShown() {
        return isToolTipShown;
    }

    public void setToolTipShown(boolean toolTipShown) {
        isToolTipShown = toolTipShown;
    }
}
