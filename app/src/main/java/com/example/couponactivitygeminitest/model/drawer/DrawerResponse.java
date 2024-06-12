package com.example.couponactivitygeminitest.model.drawer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DrawerResponse {
    private String status;
    @SerializedName("menus")
    private List<DrawerItem> drawerItems;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DrawerItem> getDrawerItems() {
        return drawerItems;
    }

    public void setDrawerItems(List<DrawerItem> drawerItems) {
        this.drawerItems = drawerItems;
    }
}
