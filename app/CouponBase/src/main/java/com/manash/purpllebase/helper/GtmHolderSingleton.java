package com.manash.purpllebase.helper;

import android.content.Context;
import androidx.annotation.NonNull;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.tagmanager.ContainerHolder;
import com.google.android.gms.tagmanager.DataLayer;
import com.google.android.gms.tagmanager.TagManager;
import com.manash.purpllebase.R;
import com.manash.purpllebase.model.EventBusMessage;
import com.manash.purpllebase.preference.PreferenceUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

public class GtmHolderSingleton {
    private static ContainerHolder mContainerHolder;
    private static GtmHolderSingleton mInstance;

    private static ContainerHolder getContainerHolder(Context context) {
        if (mContainerHolder == null) {
            loadGTMContainer(context);
        }
        return mContainerHolder;
    }

    private GtmHolderSingleton(Context context) {
        loadGTMContainer(context);
    }

    public static GtmHolderSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new GtmHolderSingleton(context);
        }
        return mInstance;
    }

    public static void setContainerHolder(ContainerHolder containerHolder) {
        mContainerHolder = containerHolder;
    }

    public DataLayer getDataLayer(Context context) {
        return TagManager.getInstance(context).getDataLayer();

    }


    private static void loadGTMContainer(Context context) {
        TagManager tagManager = TagManager.getInstance(context);

        PendingResult pending;

        // Load the container
        if (PreferenceUtil.getBuildVariant(context.getApplicationContext()).equalsIgnoreCase("release")) {
            tagManager.setVerboseLoggingEnabled(false);
            pending = tagManager.loadContainerPreferFresh(context.getString(R.string.ga_containerId), R.raw.gtm_release);
        } else {
            tagManager.setVerboseLoggingEnabled(true);
            pending = tagManager.loadContainerPreferFresh(context.getString(R.string.ga_containerId_test), R.raw.gtm_dev);
        }

        // Define the callback to store the loaded container
        pending.setResultCallback(new ResultCallback<ContainerHolder>() {
            @Override
            public void onResult(@NonNull ContainerHolder containerHolder) {

                PurplleTrace.v("GTM", "loadGTMContainer isSuccess:" + containerHolder.getStatus().isSuccess());

                // If unsuccessful, return
                if (!containerHolder.getStatus().isSuccess()) {
                    return;
                }

                // Set the container holder, only want one per running app
                // We can retrieve it later as needed
                GtmHolderSingleton.setContainerHolder(containerHolder);
                EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.GTM_CONTAINER_UPDATE));
                PurplleTrace.v("GTM", "loadGTMContainer Success");
            }
        }, 2, TimeUnit.SECONDS);
    }
}