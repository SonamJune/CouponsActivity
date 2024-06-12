package com.example.couponactivitygeminitest.network.volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;

import androidx.collection.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
//import com.datatheorem.android.trustkit.TrustKit;
import com.example.couponbase.R;
import com.example.couponbase.helper.PurplleTrace;
import com.example.couponbase.helper.TLSSocketFactory;
import com.example.couponbase.preference.PurpllePrefManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;

public class MySingleton {
    private static MySingleton mInstance;
    private Context mCtx;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context.getApplicationContext());
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            if (mCtx != null && mCtx.getApplicationContext() != null) {
                HurlStack stack;

                if (PurpllePrefManager.getInstance(mCtx.getApplicationContext()).GLOBAL.getBoolean(mCtx.getString(R.string.trustkit_toggle), true)) {
                    stack = new HurlStack() {
                        @Override
                        protected HttpURLConnection createConnection(URL url) throws IOException {

                            HttpsURLConnection httpURLConnection = (HttpsURLConnection) super.createConnection(url);
                            try {
                                //httpURLConnection.setSSLSocketFactory(TrustKit.getInstance().getSSLSocketFactory(url.getHost()));
                            } catch (Exception e) {
                            }
                            return httpURLConnection;
                        }
                    };
                } else {
                    try {
                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                            stack = new HurlStack(null, new TLSSocketFactory());
                        } else {
                            stack = new HurlStack();
                        }
                    } catch (KeyManagementException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                        PurplleTrace.v("Your Wrapper Class", "Could not create new stack for TLS v1.2");
                        stack = new HurlStack();
                    }
                }
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext(), stack);
            }
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}