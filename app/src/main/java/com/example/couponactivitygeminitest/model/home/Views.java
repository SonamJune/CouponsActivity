package com.example.couponactivitygeminitest.model.home;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Rahul D on 7/4/17.
 */

public class Views {
    @SerializedName("view_type")
    private String viewType;

    @SerializedName("view_position")
    private String viewPosition;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("image_url")
    private String imageUrl;


    @SerializedName("widgets")
    private ArrayList<Widgets> widgets;

    @SerializedName("view_group")
    private int viewGroup;

    public String getViewType() {
        return viewType;
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

    public ArrayList<Widgets> getWidgets() {
        return widgets;
    }

    public int getViewGroup() {
        return viewGroup;
    }
}
