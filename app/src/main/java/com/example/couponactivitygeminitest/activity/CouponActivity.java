package com.example.couponactivitygeminitest.activity;

//import static com.example.couponactivitygeminitest.activity.ShopBagActivity.popupshown;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.couponbase.views.MaterialProgressBar;
import com.google.gson.Gson;
//import com.manash.analytics.AnalyticsHelper;
//import com.manash.analytics.aws.AwsKinesisHelper;
//import com.manash.analytics.models.EventData;
import com.example.couponactivitygeminitest.R;
import com.example.couponactivitygeminitest.adapter.CouponAdapter;
import com.example.couponactivitygeminitest.callback.CommonVolleyCallback;
import com.example.couponactivitygeminitest.callback.OnRetryClick;
import com.example.couponactivitygeminitest.model.cartCoupon.AllCouponResponse;
import com.example.couponactivitygeminitest.model.cartCoupon.CouponItem;
import com.example.couponactivitygeminitest.model.coupon.CouponResponse;
import com.example.couponactivitygeminitest.network.Request;
import com.example.couponactivitygeminitest.utils.APIEndPoints;
import com.example.couponactivitygeminitest.utils.Network;
import com.example.couponactivitygeminitest.utils.Util;
import com.example.couponbase.util.BaseUtil;
import com.example.couponbase.util.OnItemClickListener;
//import com.example.couponBase.views.MaterialProgressBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CouponActivity extends AndroidBaseActivity implements CommonVolleyCallback<String>, OnItemClickListener, View.OnClickListener, OnRetryClick {

    private MaterialProgressBar mProgressBar;
    private RecyclerView mRecyclerView;
    private LinearLayout mNetworkErrorContainer;
    private int mSelectedPosition = -1;
    private List<CouponItem> mCouponList;
    private CouponAdapter mCouponAdapter;
    private String mCouponCode;
    private View mBottomLayout;
    private String mAction;
    private HashMap<String, String> params;
    public static String prevCouponCode = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        getWidget();
        BaseUtil.setStatusBarColor(this);
        BaseUtil.setupActionBar(this);
        applyCustomActionBar();
        mCouponCode = getIntent().getStringExtra("coupon_code");
        requestForCoupon();
        //setPageData(AnalyticsHelper.COUPON_LISTING, "default", getString(R.string.apply_coupon_txt_untranslatable));
        //sendPageViewEvent();
    }

//    private void sendPageViewEvent() {
//        AnalyticsHelper.sendPageView(getApplicationContext(),
//                AnalyticsHelper.COUPON_LISTING, "default",
//                getString(R.string.apply_coupon_txt_untranslatable), "page", "");
//
//    }


    private void requestForCoupon() {
        if (Network.isConnected(getApplicationContext())) {
            //mProgressBar.setVisibility(View.VISIBLE);
            Request.getRequest(getApplicationContext(), null, APIEndPoints.COUPONS, this);
        } else {
//            Util.showNetworkErrorScreen(getBaseContext(), mNetworkErrorContainer, getString(R.string.network_failure_msg),
//                    APIEndPoints.COUPONS, this);
        }
    }

    private void getWidget() {
        //mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mNetworkErrorContainer = findViewById(R.id.network_error_container);
        mBottomLayout = findViewById(R.id.bottom_layout);
        findViewById(R.id.clear_button).setOnClickListener(this);
        findViewById(R.id.apply_button).setOnClickListener(this);
    }

    @Override
    public void onVolleySuccess(Object obj, String type) {
        //mProgressBar.setVisibility(View.GONE);
        switch (type) {
            case APIEndPoints.COUPONS:
                AllCouponResponse couponResponse = new Gson().fromJson(obj.toString(), AllCouponResponse.class);
                mBottomLayout.setVisibility(View.VISIBLE);
                if (couponResponse != null && couponResponse.getStatus().equalsIgnoreCase(getString(R.string.success))) {
                    prepareList(couponResponse);
                } else {
                    mCouponList = new ArrayList<>();
//                    mCouponList.add(getCouponItem("Apply Coupon", CouponAdapter.VIEW_TYPE_HEADER));
                    mCouponList.add(getCouponItem(null, CouponAdapter.VIEW_TYPE_COUPON));
                    mCouponList.add(getEmptyLayout());
                    setAdapter();
                }
                break;
            case APIEndPoints.COUPON:
                updateCouponResponse((JSONObject) obj);
                break;
        }
    }

    private void setAdapter() {
        if (mCouponAdapter != null) {
            mCouponAdapter.updateData(mCouponList);
        } else {
            mCouponAdapter = new CouponAdapter(this, mCouponList, this, mSelectedPosition);
            if (mSelectedPosition == -1) {
                mCouponAdapter.setCouponCode(mCouponCode);
            }
            mRecyclerView.setAdapter(mCouponAdapter);
        }

    }

    private void applyCustomActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            String title = "Apply Coupon";
            Util.setActionBarTitle(this, title);
        }
    }

    private void updateCouponResponse(JSONObject jsonResponse) {
        CouponResponse couponResponse = new Gson().fromJson(jsonResponse.toString(), CouponResponse.class);
        if (couponResponse != null && couponResponse.getStatus().equalsIgnoreCase(getString(R.string.success))) {
//            Intent intentData = new Intent(CouponActivity.this, ShopBagActivity.class);
//            intentData.putExtra(getString(R.string.coupon_response), couponResponse);
//            setResult(RESULT_OK, intentData);
            finish();
        } else {
            int selectedPosition = mCouponAdapter.getSelectedPosition();
            if (selectedPosition == -1) {
                mCouponAdapter.setCouponError(couponResponse.getMessage());
                mCouponAdapter.notifyItemChanged(0);
            } else {
                Toast.makeText(getApplicationContext(), couponResponse.getMessage(), Toast.LENGTH_SHORT).show();

            }

            //setAddCouponErrorEvent("COUPON_ERROR", couponResponse.getMessage(), null, 200);
        }
    }

    private void prepareList(AllCouponResponse couponResponse) {
        mCouponList = new ArrayList<>();
        mCouponList.add(getCouponItem("Apply Coupon", CouponAdapter.VIEW_TYPE_HEADER));
        mCouponList.add(getCouponItem(null, CouponAdapter.VIEW_TYPE_COUPON));
        List<CouponItem> validCoupon = couponResponse.getCoupons().getValid();
        List<CouponItem> invalidCoupon = couponResponse.getCoupons().getNotValid();
        if ((validCoupon == null || validCoupon.isEmpty())
                && (invalidCoupon == null || invalidCoupon.isEmpty())) {
            mCouponList.add(getEmptyLayout());
        } else {
            if (validCoupon != null && !validCoupon.isEmpty()) {
                mCouponList.add(getCouponItem("Choose a valid coupon", CouponAdapter.VIEW_TYPE_HEADER));
                addCouponList(validCoupon, true);
            }
            if (invalidCoupon != null && !invalidCoupon.isEmpty()) {
                mCouponList.add(getCouponItem("Coupons not valid for this order", CouponAdapter.VIEW_TYPE_HEADER));
                addCouponList(invalidCoupon, false);
            }
        }
        setAdapter();
    }

    private CouponItem getEmptyLayout() {
        return getCouponItem(null, CouponAdapter.VIEW_TYPE_EMPTY_STATE);
    }

    private void addCouponList(List<CouponItem> items, boolean isValid) {
        for (CouponItem item : items) {
            if (isValid && (item.getIsSelected() != null && !item.getIsSelected().equalsIgnoreCase("0"))
                    || (mCouponCode != null &&
                    mCouponCode.equalsIgnoreCase(item.getCouponCode()))) {
                item.setIsSelected("1");
                mSelectedPosition = mCouponList.size();
            }
            item.setViewType(CouponAdapter.VIEW_TYPE_LIST);
            item.setIsValid(isValid);
            mCouponList.add(item);
        }
    }

    private CouponItem getCouponItem(String title, int type) {
        CouponItem couponItem = new CouponItem();
        couponItem.setViewType(type);
        couponItem.setTitle(title);
        return couponItem;
    }

    @Override
    public void onVolleyError(String response, String msg, int statusCode, String type, String moduleType, Object tag) {
        //mProgressBar.setVisibility(View.GONE);
        switch (type) {
            case APIEndPoints.COUPONS:
//                Util.showNetworkErrorScreen(getBaseContext(), mNetworkErrorContainer, msg, type, this);
                break;
            case APIEndPoints.COUPON:
                //setAddCouponErrorEvent(moduleType, msg, response, statusCode);
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                break;
        }
    }

//    private void setAddCouponErrorEvent(String moduleType, String message, String response, int statusCode) {
//        EventData addCouponErrorEventData = AnalyticsHelper.getApplicationErrorData(AnalyticsHelper.COUPON_LISTING,
//                "default", getString(R.string.apply_coupon_txt_untranslatable), 0, "page", "api/shop/coupon",
//                moduleType, message, params, response, statusCode);
//        AwsKinesisHelper.saveEventRecord(getApplicationContext(), AwsKinesisHelper.ACTIVITY_RESPONSE, addCouponErrorEventData);
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onItemClick(View view, int position, Object object) {
//        switch (view.getId()) {
//            case R.id.description:
//                final CouponItem couponItem = (CouponItem) object;
//                int noOfButton = couponItem.getCouponDetailDeeplink() != null &&
//                        !couponItem.getCouponDetailDeeplink().isEmpty() ? 2 : 1;
//                BaseUtil.showAlertDialog(this, noOfButton, getString(R.string.details),
//                        Html.fromHtml(couponItem.getCouponMessageDetail()), true, getString(R.string.ok_translatable), getString(R.string.show_products), new DialogListener() {
//                            @Override
//                            public void onAction(int action) {
//                                if (action == DialogListener.NO) {
////                                    EventData eventData = AnalyticsHelper.getClickStreamEventData(null, null, null,
////                                            AnalyticsHelper.COUPON_LISTING, null, null, "get_products", couponItem.getCouponDetailDeeplink(), null, null);
////                                    AnalyticsHelper.trackCleverTapAnalytics(getApplicationContext(), AnalyticsHelper.CLICK_STREAM, eventData);
//                                    Route.startActivityByDeepLink(getApplicationContext(), couponItem.getCouponDetailDeeplink());
//                                }
//                            }
//                        });
//                break;
//        }
//    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.clear_button:
//
//                callApplyCoupon(false);
//                break;
//            case R.id.apply_button:
//                callApplyCoupon(true);
//                break;
//        }
//    }

    private void callApplyCoupon(boolean isApplied) {
        String couponCode;
        int selectedPosition = mCouponAdapter.getSelectedPosition();
        if (selectedPosition >= 0) {
            couponCode = mCouponList.get(selectedPosition).getCouponCode();
        } else {
            couponCode = mCouponAdapter.getCouponCode();
        }
        if (!isApplied && prevCouponCode !=null && !prevCouponCode.isEmpty()
                && couponCode != null && couponCode.isEmpty()) {
            couponCode = prevCouponCode;
        }
        if (couponCode != null && !couponCode.isEmpty()) {
            requestForApplyCoupon(couponCode, isApplied);
            //popupshown = false;
        } else if (isApplied) {
            Toast.makeText(getApplicationContext(), "Please select or type the coupon", Toast.LENGTH_SHORT).show();

        } else {
            onBackPressed();
        }
    }

    private void requestForApplyCoupon(String code, boolean isApplied) {
        if (Network.isConnected(getApplicationContext())) {
            BaseUtil.hideSoftKeyboard(getBaseContext(), mRecyclerView);
            //mProgressBar.setVisibility(View.VISIBLE);
            params = new HashMap<>();
            mCouponCode = code;
            params.put(getString(R.string.coupon_code), code);
            String iconSelect;
            if (isApplied) { //apply coupon
                params.put(getString(R.string.action), getString(R.string.add));
                mAction = "add";
                iconSelect = "apply_coupon";
            } else { //remove coupon
                params.put(getString(R.string.action), getString(R.string.remove_untranslatable));
                mAction = "remove";
                iconSelect = "remove_coupon";

            }

//            EventData eventData = AnalyticsHelper.getClickStreamEventData(null, null, null,
//                    AnalyticsHelper.COUPON_LISTING, null, null, iconSelect, code, null, null);
//            AnalyticsHelper.trackCleverTapAnalytics(getApplicationContext(), AnalyticsHelper.CLICK_STREAM, eventData);
            Request.getRequest(getApplicationContext(), params, APIEndPoints.COUPON, this);
        } else {
            //Toast.makeText(getApplicationContext(), getString(R.string.network_failure_msg), Toast.LENGTH_SHORT).show();
            //mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setOnRetry(String type) {
        switch (type) {
            case APIEndPoints.COUPONS:
                requestForCoupon();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change, R.anim.slide_down);
    }

    @Override
    public void onItemClick(View view, int position, Object object) {
        
    }
}
