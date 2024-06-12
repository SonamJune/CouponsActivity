package com.manash.purpllebase.util;

public interface OnDataChangeListener {
    int TYPE_FOLLOW = 1;
    int TYPE_CART = 2;
    int TYPE_PRODUCT_WISH_LIST = 3;
    void onDataChanged(String id, Object value, int type);
}
