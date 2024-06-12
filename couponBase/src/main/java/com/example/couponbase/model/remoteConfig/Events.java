package com.example.couponbase.model.remoteConfig;

import com.google.gson.annotations.SerializedName;

public class Events {

    @SerializedName("base_event")
    private String baseEvent;

    @SerializedName("destination_event")
    private String destinationEvent;

    private int multiplier;

    public void setBaseEvent(String baseEvent) {
        this.baseEvent = baseEvent;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public String getBaseEvent() {
        return baseEvent;
    }

    public String getDestinationEvent() {
        return destinationEvent;
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && ((Events) obj).baseEvent.equalsIgnoreCase(this.baseEvent);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + (this.baseEvent != null ? this.baseEvent.hashCode() : 0);
        return hash;
    }
}
