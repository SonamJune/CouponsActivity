package com.example.couponactivitygeminitest.utils;

public class VolleyRequestType {
    private String type;
    private int position = -1;
    private int listPosition;

    public VolleyRequestType() {
    }

    public VolleyRequestType(String type) {
        this.type = type;
    }

    public VolleyRequestType(String type, int position) {
        this.type = type;
        this.position = position;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getListPosition() {
        return listPosition;
    }

    public void setListPosition(int listPosition) {
        this.listPosition = listPosition;
    }
}
