package com.example.couponactivitygeminitest.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private final List<TextView> mButtonListeners;

    public NetworkChangeReceiver(Context context) {
        mButtonListeners = new ArrayList<>();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this, filter);
    }

    public void addListener(TextView button) {
        mButtonListeners.add(button);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Network.isConnected(context) && mButtonListeners != null) {
            List<TextView> listeners = new ArrayList<>();
            listeners.addAll(mButtonListeners);
            for (TextView retryButton : listeners) {
                retryButton.performClick();
            }
            mButtonListeners.clear();
            Network.unregisterNetworkReceiver(context);
        }
    }
}