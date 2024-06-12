package com.example.couponactivitygeminitest.network.volley;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
//import com.manash.analytics.google_analytics.GoogleAnalyticsHelper;
import com.example.couponactivitygeminitest.R;
import com.example.couponactivitygeminitest.callback.CommonVolleyCallback;
import com.example.couponactivitygeminitest.utils.APIEndPoints;
import com.example.couponactivitygeminitest.utils.Network;
import com.example.couponactivitygeminitest.utils.Util;
import com.example.couponactivitygeminitest.utils.VolleyRequestType;
import com.example.couponbase.helper.PurplleTrace;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CustomRequest<T> extends Request<JSONObject> {
    private static final int DEFAULT_TIMEOUT = 10000;
    private static final int DEFAULT_RETRY = 1;
    private static final float DEFAULT_MULT = 2;
    private final int method;
    private Context context;
    private CommonVolleyCallback listener;
    private Map<String, String> params;
    private long startMillis;
    private String url;
    private T requestType;
    private String type;
    private Object tag;

    public CustomRequest(Context context, int method, String url, Map<String, String> params,
                         CommonVolleyCallback responseListener, ErrorListener errorListener, T requestType,Object tag) {
        super(method, url, errorListener);
        startMillis = System.currentTimeMillis();
        this.listener = responseListener;
        this.params = params;
        this.context = context.getApplicationContext();
        this.url = url;
        this.method = method;
        this.requestType = requestType;
        this.tag = tag;
        if (requestType instanceof String) {
            type = (String) requestType;
        } else {
            type = ((VolleyRequestType) requestType).getType();
        }
        setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT, DEFAULT_RETRY, DEFAULT_MULT));
    }

    protected Map<String, String> getParams() {
        return params;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            JSONObject obj = new JSONObject(jsonString);
            if(tag!= null){
                obj.put(context.getString(R.string.request_tag_for_gridCounter),tag);
            }
            return Response.success(obj,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            //GoogleAnalyticsHelper.pushCatchException(e, context);
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            //GoogleAnalyticsHelper.pushCatchException(je, context);
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
//        PurplleTrace.i("PurplleResponse", response.toString());
        long endMillis = System.currentTimeMillis();
        long collapsedTimeMillis = endMillis - startMillis;
        String label;
        if (method == Method.POST) {
            label = "POST_REQUEST";
        } else {
            label = "GET_REQUEST";
        }

        if (listener != null) listener.onVolleySuccess(response, requestType);

        //GoogleAnalyticsHelper.pushTimeEvent("API_RESP_TIME", url, label, collapsedTimeMillis, context);
    }

    @Override
    public Map<String, String> getHeaders() {
        HashMap<String, String> headers;
        headers = Network.getHeader(context, type != null && !type.equalsIgnoreCase(APIEndPoints.GENERATE_TOKEN));
        PurplleTrace.i("VolleyHeader", headers.toString());
        return headers;

    }

}