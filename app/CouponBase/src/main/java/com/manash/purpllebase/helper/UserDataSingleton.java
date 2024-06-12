package com.manash.purpllebase.helper;

import com.google.gson.annotations.SerializedName;
import com.manash.purpllebase.model.EventBusMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.HashSet;


public class UserDataSingleton {
    private static UserDataSingleton userDataSingleton;

    private HashSet<String> mCartIds;
    private HashSet<String> mWishListIds;
    private String mTooltipMessage;
    private int isImageExtraction;

    private UserDataSingleton() {
        mCartIds = new HashSet<>();
        mWishListIds = new HashSet<>();
    }

    public int getIsImageExtraction() {
        return isImageExtraction;
    }

    public void setIsImageExtraction(int isImageExtraction) {
        this.isImageExtraction = isImageExtraction;
    }

    public String getMtooltipMessage() {
        return mTooltipMessage;
    }

    public void setMtooltipMessage(String mtooltipMessage) {
        this.mTooltipMessage = mtooltipMessage;
    }

    public synchronized static UserDataSingleton getInstance() {
        if (userDataSingleton == null) {
            userDataSingleton = new UserDataSingleton();
        }
        return userDataSingleton;
    }

    public HashSet<String> getCartIds() {
        return mCartIds;
    }

    public void addWishListProductId(String id) {
        mWishListIds.add(id);
    }

    public void removeWishListProductId(String id) {
        mWishListIds.remove(id);
    }

    public void addCartProductId(String id) {
        mCartIds.add(id);
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.CART_UPDATED));
    }

    public void removeCartProductId(String id) {
        mCartIds.remove(id);
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.CART_UPDATED));
    }

    public void clearWishListIds() {
        mWishListIds.clear();
    }

    public void clearCartIds() {
        mCartIds.clear();
        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.CART_UPDATED));
    }

    public HashSet<String> getWishListIds() {
        return mWishListIds;
    }

    public void setWishListIds(HashSet<String> wishListIds) {
        this.mWishListIds = wishListIds;
    }


    public void setCartIds(HashSet<String> cartIds) {
        this.mCartIds = cartIds;
    }

    public int getWishListCount() {
        if (!mWishListIds.isEmpty()) {
            return mWishListIds.size();
        }
        return 0;
    }

    public int checkInCart(String id) {
        if (mCartIds.contains(id)) {
            return 1;
        } else {
            return 0;
        }
    }

    public int checkIsLiked(String id) {
        if (mWishListIds.contains(id)) {
            return 1;
        } else {
            return 0;
        }
    }
}
