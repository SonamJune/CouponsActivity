package com.example.couponactivitygeminitest.model;

import com.example.couponactivitygeminitest.callback.ListItem;

public class Header implements ListItem {
    String name;

    public Header(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getItemType() {
        return ListItem.TYPE_HEADER;
    }
}
