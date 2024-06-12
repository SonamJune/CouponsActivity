package com.example.couponactivitygeminitest.callback;
import org.json.JSONException;

import javax.annotation.Nullable;

public interface CommonVolleyCallback<T> {
    void onVolleySuccess(Object obj, T type);

    void onVolleyError(String response, String msg, int statusCode, T type, @Nullable String moduleType,@Nullable Object tag);
}

