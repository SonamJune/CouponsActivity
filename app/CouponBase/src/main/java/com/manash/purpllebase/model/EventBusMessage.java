package com.manash.purpllebase.model;

public class EventBusMessage {
    private MessageType messageType;
    private Object data;
    private String pageType;


    public EventBusMessage(MessageType messageType) {
        this.messageType = messageType;
    }

    public MessageType getType() {
        return messageType;
    }

    public String getPageType() {
        return pageType;
    }

    public void setPageType(String pageType) {
        this.pageType = pageType;
    }

    public void setType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum MessageType {
        UPDATE_REVIEWS,
        GTM_CONTAINER_UPDATE,
        LOG_IN,
        LOGOUT,
        PAYMENT_UPDATED,
        ADDED_TO_WISH_LIST,
        REMOVED_FROM_WISH_LIST,
        PROFILE_UPDATED,
        COMPLEXION_FINDER_DATA,
        REMOTE_CONFIG_ACTIVATED,
        LOGIN_SUCCESS,
        IS_ELITE,
        IN_APP_MESSAGE,
        CART_UPDATED,
        SET_PAGE_DATA,
        DO_LOGOUT,
        TOKEN_UPDATE,
        LIVE_STREAM_MENU,
        BEAUTYSTUDIO_PRODUCT_TAP,
        LIVE_STREAM_LINK,
        IS_DIALOG_OPEN,
        RESET_DEFAULT_FLUTTER_VIEW,
        IS_FLUTTER_VIEW_TAPPED,
        BOTTOM_NAV_RESPONSE_SUCCESS,
        BOTTOM_NAV_RESPONSE_ERROR,
        FOR_YOU_UPDATED,
        OPEN_LOCATION_PICKER
    }
}