package com.example.couponactivitygeminitest.activity;

//import static com.example.couponbase.PurplleApplication.mLastSessionTime;
//import static com.example.couponbase.util.BaseUtil.getTimeStamp;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;

import com.example.couponactivitygeminitest.adapter.ProfileMenuItemsAdapter;
//import com.example.couponactivitygeminitest.database.db.AppDatabase;
//import com.example.couponactivitygeminitest.dialog.CustomShareDialog;
//import com.example.couponactivitygeminitest.helper.WebCachingCoroutineHelperKt;
//import com.example.couponactivitygeminitest.model.Webview.resporceData.WebViewResource;
import com.example.couponactivitygeminitest.model.drawer.DrawerResponse;
import com.example.couponactivitygeminitest.utils.Network;
import com.example.couponbase.R;
import com.example.couponbase.activity.BaseActivity;
import com.example.couponbase.helper.PurplleTrace;
import com.example.couponbase.model.common.share.ShareResponse;
import com.example.couponbase.preference.PreferenceUtil;
import com.example.couponbase.preference.PurpllePrefKey;
import com.example.couponbase.preference.PurpllePrefManager;
import com.example.couponbase.util.BaseUtil;
import com.example.couponbase.util.DialogListener;
import com.example.couponbase.util.OnItemClickListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;

import java.util.Arrays;


public class AndroidBaseActivity extends BaseActivity implements View.OnClickListener {
    public static final int SHOP_BAG_REQUEST = 877;
    public static final int RETRY_TIMER_INCREMENTER = 2;

    private ListPopupWindow mPopupWindow;
    private TextView mCartCountTextView;
    private ProfileMenuItemsAdapter mProfileMenuAdapter;
    private boolean mIsShowCartMenu;
    private boolean mIsShowSearchMenu;
    private boolean mIsShowOfferMenu;
    private boolean mIsShowProfileMenu;
    private TextView mCartIcon;
    private String mPageType = "", mPageValue, mPageTitle;
    private boolean mPageViewSend = true;
//    private MaterialProgressBar mProgressBar;
    private String mProductId;
    private BottomSheetDialog mBottomSheet;
//    private List<DrawerItem> rightDrawerItems;
//    private DrawerItem drawerItem;
    private static String mFlutterSearchPageType = "";
    private static String mFlutterSearchPageTypeValue = "";
    private static String mFlutterSearchXId = "";
    public boolean isCurrentHomePage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //updateEncryptedJwt();
        GetDrawerMenuItems();
        //Util.sendSessionDetailsEvent(this instanceof SplashActivity, getIntent());-
    }

//    public void setNewLocales(AppCompatActivity mContext, String language,Boolean branchInitialiseRequired) {
//        LocaleManager.setNewLocale(this, language);
//        Intent resultIntent = new Intent(mContext, MainActivity.class);
//        resultIntent.putExtra(getString(R.string.is_branch_init_required), branchInitialiseRequired);
//        startActivity(resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
//    }
    private void GetDrawerMenuItems() {

        String mDrawerResponseString = PurpllePrefManager.getInstance(this).GLOBAL.getString(PurpllePrefKey.DRAWER_MENU_ITEMS, null);
        if (mDrawerResponseString != null) {
            DrawerResponse drawerResponse = new Gson().fromJson(mDrawerResponseString, DrawerResponse.class);
            //addRightSliderMenu(drawerResponse);
        }

    }
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleManager.setLocale(base));
//    }
//    private void addRightSliderMenu(DrawerResponse drawerResponse) {
//        rightDrawerItems = drawerResponse.getDrawerItems();
//        if (rightDrawerItems != null) {
//
//            Iterator<DrawerItem> iterator = rightDrawerItems.iterator();
//            while (iterator.hasNext()) {
//                DrawerItem checkItem = iterator.next();
//                if (checkItem.getIsSecondaryDrawer() == 0) {
//                    iterator.remove();
//                }
//            }
//
//            if (PreferenceUtil.isUserLogin(this)) {
//                drawerItem = new DrawerItem();
//                drawerItem.setName(getString(R.string.logout));
//                drawerItem.setIsLoggedinRequired(1);
//                drawerItem.setIconCode("e732");
//                drawerItem.setDeepLinkUrl("logout");
//                rightDrawerItems.add(drawerItem);
//            } else {
//                drawerItem = new DrawerItem();
//                drawerItem.setName(getString(R.string.login_or_register));
//                drawerItem.setIconCode("ea29");
//                drawerItem.setIsLoggedinRequired(0);
//                drawerItem.setDeepLinkUrl("purplle.com://login");
//                rightDrawerItems.add(drawerItem);
//            }
//        }
//
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = new MenuInflater(getBaseContext());
//        inflater.inflate(R.menu.main_menu, menu);
//        discoverMenuItems(menu);
//        setupDropDownMenu();
//        return true;
//    }

    public void setFlutterSearchEventData(String pageType, String pageTypeValue, String xId) {
        mFlutterSearchPageType = pageType;
        mFlutterSearchPageTypeValue = pageTypeValue;
        mFlutterSearchXId = xId;
    }

//    public void setPageData(String pageType, String pageTypeValue, String pageTitle) {
//        mPageType = pageType;
//        mPageValue = pageTypeValue;
//        mPageTitle = pageTitle;
//        mPageViewSend = true;
//        PurplleTrace.i("PAGE_VIEW", "setpage data page type" + mPageType);
//        PurplleApplication.getInstance().setCurrentPage(mPageType, mPageValue, mPageTitle);
//
//    }

    public void setPageData(String pageType, String pageTypeValue, String pageTitle, boolean isPageViewSend) {
        mPageType = pageType;
        mPageValue = pageTypeValue;
        mPageTitle = pageTitle;
        mPageViewSend = isPageViewSend;
        PurplleTrace.i("PAGE_VIEW", "setpage data page type" + mPageType);
    }

//    public void setmPageViewSend(boolean mPageViewSend) {
//        this.mPageViewSend = mPageViewSend;
//        PurplleApplication.getInstance().setCurrentPage(mPageType, mPageValue, mPageTitle);
//    }

    private void setupDropDownMenu() {
        mPopupWindow = new ListPopupWindow(this);

//        if (rightDrawerItems == null || rightDrawerItems.size() == 0) {
//            GetDrawerMenuItems();
//        }
//        mProfileMenuAdapter = new ProfileMenuItemsAdapter(getApplicationContext(), rightDrawerItems);
        mPopupWindow.setAdapter(mProfileMenuAdapter);
        mPopupWindow.setWidth(((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 185, getResources().getDisplayMetrics())));
        mPopupWindow.setModal(true);
        mPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //DrawerDeepLinkClick(position);
            }
        });
    }

//    private void DrawerDeepLinkClick(int position) {
//
//        String iconSelectValue;
//        if (Network.isConnected(AndroidBaseActivity.this)) {
//
//            mPopupWindow.dismiss();
//
////            if ((rightDrawerItems.get(position).getIsLoggedinRequired() == 1 && PreferenceUtil.isUserLogin(this)) || rightDrawerItems.get(position).getIsLoggedinRequired() == 0) {
////
////                iconSelectValue = rightDrawerItems.get(position).getName();
////
////                if (rightDrawerItems.get(position).getDeepLinkUrl().equalsIgnoreCase("logout")) {
////                    showLogOutDialog();
////                } else if (rightDrawerItems.get(position).getDeepLinkUrl().equalsIgnoreCase("purplle.com://login")) {
////                    Intent deepLinkIntent = new Intent(this, AuthenticationActivity.class);
////                    deepLinkIntent.putExtra(getString(R.string.page_type), mPageType);
////                    startActivity(deepLinkIntent);
////                } else {
////                    Route.startActivityByDeepLink(AndroidBaseActivity.this, rightDrawerItems.get(position).getDeepLinkUrl());
////                }
//////                EventData eventData = AnalyticsHelper.getClickStreamEventData(null, null, null, mPageType, mPageValue, mPageTitle, IconSelectType.PROFILE_DROP_DOWN_LIST, iconSelectValue, null, null);
//////                AnalyticsHelper.trackCleverTapAnalytics(this, AnalyticsHelper.CLICK_STREAM, eventData);
////
////            } else {
////                Intent deepLinkIntent = new Intent(this, AuthenticationActivity.class);
////                deepLinkIntent.putExtra(getString(R.string.deeplink_url), rightDrawerItems.get(position).getDeepLinkUrl());
////                deepLinkIntent.putExtra(getString(R.string.page_type), mPageType);
////                startActivity(deepLinkIntent);
////            }
////
////        } else {
////            Toast.makeText(getApplicationContext(), getString(R.string.network_failure_msg), Toast.LENGTH_SHORT).show();
////        }
//    }

    private void showLogOutDialog() {
        if (Network.isConnected(getApplicationContext())) {
            BaseUtil.showAlertDialog(this, 2, getString(R.string.confirm_logout), getString(R.string.logout_confirm_text), new DialogListener() {
                @Override
                public void onAction(int action) {
                    if (action == YES) {
//                                requestForNewToken();
                        //requestDeleteToken();
//                        EventData eventData = AnalyticsHelper.getClickStreamEventData(null, null, null, mPageType, mPageValue, mPageTitle, IconSelectType.LOGOUT_CLICK, null, null, null);
//                        AnalyticsHelper.trackCleverTapAnalytics(getApplicationContext(), AnalyticsHelper.CLICK_STREAM, eventData);
                    }
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.network_failure_msg), Toast.LENGTH_SHORT).show();
        }
    }


//    public void requestDeleteToken() {
//        //send interaction event when logout
////        EventData loginEventData = AnalyticsHelper.getUserInteractionEventData(mPageType, mPageValue, "", "", getString(R.string.click_str), getString(R.string.login), getString(R.string.logout_event_str), "", "", getString(R.string.is_fragment));
////        AwsKinesisHelper.saveEventRecord(PurplleApplication.getAppContext(), AwsKinesisHelper.USER_INTERACTION, loginEventData);
//        if (Network.isConnected(getApplicationContext())) {
//            showProgressBar();
//            final HashMap<String, String> map = new HashMap<>();
//            map.put(getString(R.string.device_id), PreferenceUtil.getDeviceId(this));
//            map.put(getString(R.string.type), getString(R.string.mode_device_android));
//            map.put(getString(R.string.mode_device_type), getString(R.string.mode_device_android));//optional
//            map.put(getString(R.string.token), PreferenceUtil.getJWTToken(this));
//            Request.postRequest(this, map, APIEndPoints.DELETE_TOKEN, new VolleyCallBack());
//        }
//    }

//    public void callWebViewResourceAPI() {
//        if (Network.isConnected(getApplicationContext())) {
//            final HashMap<String, String> map = new HashMap<>();
//            Request.getRequest(getApplicationContext(), map, PurplleConstants.WEBVIEW_RESOURCE, new CommonVolleyCallback<Object>() {
//                @Override
//                public void onVolleySuccess(Object obj, Object type) {
//                    WebViewResource webViewResource = new Gson().fromJson(obj.toString(), WebViewResource.class);
//                    //check from local
//                    if (AppDatabase.getAppDatabase(getApplicationContext()).webViewResourceDAO().getWebViewData() != null) {
//                        //get the data from local
//                        WebViewResource oldWebviewData = AppDatabase.getAppDatabase(getApplicationContext()).webViewResourceDAO().getWebViewData();
//                        //change new value
//                        assert oldWebviewData != null;
//                        if (!oldWebviewData.getVersion().equalsIgnoreCase(PreferenceUtil.getWebCacheVersion())) {
//                            // delete the old data and update the database with new URL
//                            WebCachingCoroutineHelperKt.deleteUnwantedFile(oldWebviewData, webViewResource);
//                        }
//                    } else {
//                        //insert all urls in database
//                        WebCachingCoroutineHelperKt.insertWebViewResourceData(webViewResource);
//                    }
//                }
//
//                @Override
//                public void onVolleyError(String response, String msg, int statusCode, Object type, @Nullable String moduleType, @Nullable Object tag) {
//                    PurplleTrace.e("WebResourceApiError", msg);
//                }
//            });
//        }
//    }

//    public void requestForShare(String type, String typeId) {
//        if (Network.isConnected(getBaseContext())) {
//            showProgressBar();
//            HashMap<String, String> params = new HashMap<>();
//            params.put(getString(R.string.type), type.equalsIgnoreCase(getString(R.string.share_product)) ? getString(R.string.product) : type);
//            params.put(getString(R.string.type_id), typeId);
//            Request.getRequest(getApplicationContext(), params, APIEndPoints.SOCIAL_SHARE, new CommonVolleyCallback<Object>() {
//                @Override
//                public void onVolleySuccess(Object obj, Object objectType) {
//                    hideProgressBar();
//                    ShareResponse shareResponse = new Gson().fromJson(obj.toString(), ShareResponse.class);
//                    if (shareResponse != null && shareResponse.getStatus().equalsIgnoreCase(getString(R.string.success))) {
//                        if (type.equalsIgnoreCase(getString(R.string.share_product)) || type.equalsIgnoreCase(getString(R.string.story))) {
//                            CustomShareDialog shareDialog = new CustomShareDialog();
//                            Bundle args = new Bundle();
//                            args.putParcelable(getString(R.string.share_product), shareResponse);
//                            args.putString(getString(R.string.product_id), mProductId);
//                            args.putString(getString(R.string.page_title), mPageTitle);
//                            if (type.equalsIgnoreCase(getString(R.string.story))) {
//                                args.putString(getString(R.string.page_type), AnalyticsHelper.VIDEO_VIEW);
//                            } else {
//                                args.putString(getString(R.string.page_type), AnalyticsHelper.PRODUCT_DETAIL);
//                            }
//                            shareDialog.setArguments(args);
//                            getSupportFragmentManager().beginTransaction().add(shareDialog, null).commitAllowingStateLoss();
//                        } else {
//                            shareItem(shareResponse);
//                        }
//                    } else {
//                        Toast.makeText(getBaseContext(), shareResponse.getStatusMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onVolleyError(String response, String msg, int statusCode, Object type, @javax.annotation.Nullable String moduleType, @javax.annotation.Nullable Object tag) {
//
//                }
//            });
//            mProductId = typeId;
//            //AnalyticsHelper.trackCleverTapAnalytics(this, AnalyticsHelper.CLICK_STREAM, AnalyticsHelper.getClickStreamEventData(AnalyticsHelper.EVENT_MODULE_PRODUCT, typeId, null, mPageType, mPageValue, mPageTitle, IconSelectType.SHARE_CLICK, "share", null, null));
//
//        } else {
//            Toast.makeText(getApplicationContext(), getString(R.string.network_failure_msg), Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    protected void onStart() {
        super.onStart();
        //Util.sendSessionDetailsEvent(this instanceof SplashActivity, getIntent());
//        if (!EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().register(this);
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (EventBus.getDefault().isRegistered(this)) {
//            EventBus.getDefault().unregister(this);
//        }
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(EventBusMessage event) {
//        EventBusMessage.MessageType type = event.getType();
//        if (type == EventBusMessage.MessageType.BOTTOM_NAV_RESPONSE_SUCCESS) {
//            requestForYouAPI();
//        }
//    }

//    public void requestForNewToken() {
//        showProgressBar();
//        final HashMap<String, String> map = new HashMap<>();
//        map.put(getString(R.string.device_id), PreferenceUtil.getDeviceId(this));
//        map.put(getString(R.string.mac_id), DeviceData.getInstance(this).macId);
//        map.put(getString(R.string.version), DeviceData.getInstance(this).appVersion);
//        Request.getRequest(this, map, APIEndPoints.GENERATE_TOKEN, new VolleyCallBack());
//    }

//    private void showProgressBar() {
//        if (mProgressBar != null) {
//            mProgressBar.setVisibility(View.VISIBLE);
//        }
//    }

//    private void hideProgressBar() {
//        if (mProgressBar != null) {
//            mProgressBar.setVisibility(View.GONE);
//        }
//    }


//    private void callShopBagActivity() {
//        Intent intent = new Intent(this, ShopBagActivity.class);
//        startActivityForResult(intent, SHOP_BAG_REQUEST);
//        overridePendingTransition(R.anim.slide_up, R.anim.no_change);
//    }

    public void showMenuItem(boolean isShowCartMenu, boolean isShowSearchMenu, boolean isShowProfileMenu, boolean isShowoffersMenu) {
        mIsShowCartMenu = isShowCartMenu;
        mIsShowSearchMenu = isShowSearchMenu;
        mIsShowProfileMenu = isShowProfileMenu;
        mIsShowOfferMenu = isShowoffersMenu;
        invalidateOptionsMenu();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        PurplleTrace.i("PAGE_VIEW", "onResume page type" + mPageType);
//        if (!mPageType.isEmpty() && mPageViewSend) {
//            PurplleApplication.getInstance().setCurrentPage(mPageType, mPageValue, mPageTitle);
//        }
//        updateCartCount();
//    }

    @Override
    protected void onPause() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
        super.onPause();
    }

//    private void discoverMenuItems(Menu menu) {
//        MenuItem shopCartItem = menu.findItem(R.id.shop_cart);
//        MenuItem searchItem = menu.findItem(R.id.search);
//        MenuItem offerItem = menu.findItem(R.id.my_offers);
//        MenuItem overFlowItem = menu.findItem(R.id.overflow_menu);
//
//        RelativeLayout menuCartBagLayout = (RelativeLayout) shopCartItem.getActionView();
//        mCartCountTextView = menuCartBagLayout.findViewById(R.id.cart_counts);
//        mCartCountTextView.setVisibility(View.INVISIBLE);
//        mCartIcon = menuCartBagLayout.findViewById(R.id.shop_cart);
//
//        menuCartBagLayout.setOnClickListener(AndroidBaseActivity.this);
//        updateCartCount();
//
//        RelativeLayout menuOverflowLayout = (RelativeLayout) overFlowItem.getActionView();
//        menuOverflowLayout.setOnClickListener(AndroidBaseActivity.this);
//
//        RelativeLayout searchLayout = (RelativeLayout) searchItem.getActionView();
//        searchLayout.setOnClickListener(AndroidBaseActivity.this);
//        overFlowItem.setVisible(mIsShowProfileMenu);
//        searchItem.setVisible(mIsShowSearchMenu);
//        offerItem.setVisible(mIsShowOfferMenu);
//        shopCartItem.setVisible(mIsShowCartMenu);
//    }

    public void updateCartCount() {
        if (mCartCountTextView != null) {
            mCartCountTextView.setVisibility(View.INVISIBLE);
            final String count = PreferenceUtil.getCartCount(getApplicationContext());
            if (TextUtils.equals(count, "0")) {
                mCartCountTextView.setVisibility(View.INVISIBLE);
            } else {
                mCartCountTextView.setVisibility(View.VISIBLE);
                mCartCountTextView.setText(count);
                //Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.pop_anim);
                //mCartCountTextView.setAnimation(bounce);
            }
        }
    }

//    @Override
//    public void onClick(View view) {
//        int id = view.getId();
//        //EventData eventData;
//        switch (id) {
//            case R.id.menu_cart_bag:
//                if (Network.isConnected(this)) {
//                    callShopBagActivity();
////                    eventData = AnalyticsHelper.getClickStreamEventData(null, null, null, mPageType, mPageValue, mPageTitle, IconSelectType.ACTION_BAR_MENU_CLICK, "cart", null, null);
////                    AnalyticsHelper.trackCleverTapAnalytics(this, AnalyticsHelper.CLICK_STREAM, eventData);
//                } else {
//                    Toast.makeText(getApplicationContext(), getString(R.string.network_failure_msg), Toast.LENGTH_SHORT).show();
//                }
//                break;
//            case R.id.profile_overflow:
//                if (mPopupWindow.isShowing()) {
//                    mPopupWindow.dismiss();
//                } else {
//                    if (PreferenceUtil.isUserLogin(this)) {
//                        drawerItem = new DrawerItem();
//                        drawerItem.setName(getString(R.string.logout));
//                        drawerItem.setIsLoggedinRequired(1);
//                        drawerItem.setIconCode("e732");
//                        drawerItem.setDeepLinkUrl("logout");
//                    } else {
//                        drawerItem = new DrawerItem();
//                        drawerItem.setName(getString(R.string.login_or_register));
//                        drawerItem.setIconCode("ea29");
//                        drawerItem.setIsLoggedinRequired(0);
//                        drawerItem.setDeepLinkUrl("purplle.com://login");
//                    }
//
//                    if (rightDrawerItems != null && rightDrawerItems.size() > 0) {
//                        rightDrawerItems.set(rightDrawerItems.size() - 1, drawerItem);
//                        mProfileMenuAdapter.notifyDataSetChanged();
//                    }
//
//                    mPopupWindow.setAnchorView(view);
//                    mPopupWindow.show();
////                    eventData = AnalyticsHelper.getClickStreamEventData(null, null, null, mPageType, mPageValue, mPageTitle, IconSelectType.ACTION_BAR_MENU_CLICK, "profile_drop_down", null, null);
////                    AnalyticsHelper.trackCleverTapAnalytics(this, AnalyticsHelper.CLICK_STREAM, eventData);
//                }
//                break;
//            case R.id.search:
//                //AnalyticsHelper.trackCleverTapAnalytics(getApplicationContext(), AnalyticsHelper.FEATURE_CLICK, AnalyticsHelper.getFeatureClickEventData(getString(R.string.search_untranslatable), "default", getString(R.string.search_untranslatable), "symbol", 0, mPageType, mPageValue != null ? mPageValue : "default", mPageTitle != null ? mPageTitle : "", "", null, null));
//                Intent intent = new Intent(this, SearchActivity.class);
//                intent.putExtra(getString(R.string.is_fragment), false);
//                startActivity(intent);
//                break;
//        }
//    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        overridePendingTransition(R.anim.no_change, R.anim.slide_down);
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void setProgressBar(MaterialProgressBar progressBar) {
//        mProgressBar = progressBar;
//    }

    public TextView getCartIcon() {
        return mCartIcon;
    }


//    private void shareItem(final ShareResponse shareResponse) {
//        LayoutInflater inflater = LayoutInflater.from(this);
//        final View view = inflater.inflate(R.layout.bottom_sheet_recycler_layout, null);
//        mBottomSheet = new BottomSheetDialog(this);
//        mBottomSheet.setContentView(view);
//
//        BottomSheetBehavior mBehavior = BottomSheetBehavior.from((View) view.getParent());
//        Resources resources = this.getResources();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//        mBehavior.setPeekHeight(displayMetrics.heightPixels);
//        RecyclerView recyclerView = view.findViewById(R.id.options_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
//        view.findViewById(R.id.bottom_sheet_title).setVisibility(View.VISIBLE);
//
//        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//        sharingIntent.setType("text/plain");
//        PackageManager pm = getPackageManager();
//        List<ResolveInfo> activityList = pm.queryIntentActivities(sharingIntent, 0);
//        List<ResolveInfo> filteredActivityList = new ArrayList<>();
//        if (!activityList.isEmpty()) {
//            for (ResolveInfo resolveInfo : activityList) {
//                String packageName = resolveInfo.activityInfo.packageName;
//                if (isMatch(packageName)) {
//                    filteredActivityList.add(resolveInfo);
//                }
//            }
//        }
//        final ShareIntentListAdapter objShareIntentListAdapter = new ShareIntentListAdapter(getApplicationContext(), filteredActivityList, shareClickListener(shareResponse), ShareIntentListAdapter.VIEW_TYPE_DEFAULT);
//        recyclerView.setAdapter(objShareIntentListAdapter);
//        if (!isFinishing()) {
//            mBottomSheet.show();
//        }
//    }

    private OnItemClickListener shareClickListener(final ShareResponse shareResponse) {
        return new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Object object) {
                ResolveInfo info = (ResolveInfo) object;
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
                intent.setType("text/plain");
                String iconSelectValue = null;
                if (isMatch(info.activityInfo.packageName)) {
                    if (getString(R.string.twitter_package).equals(info.activityInfo.packageName)) {
                        intent.putExtra(Intent.EXTRA_TEXT, shareResponse.getTitle() + " " + shareResponse.getUrl().getTwitterShareUrl());
                        iconSelectValue = "twitter";
                    } else if (getString(R.string.google_plus_package).equalsIgnoreCase(info.activityInfo.packageName)) {
                        intent.putExtra(Intent.EXTRA_SUBJECT, shareResponse.getTitle());
                        intent.putExtra(Intent.EXTRA_TEXT, shareResponse.getTitle() + "\n" + shareResponse.getUrl().getGplusShareUrl());
                        iconSelectValue = "google_plus";
                    } else if (getString(R.string.gmail_package).equalsIgnoreCase(info.activityInfo.packageName)) {
                        intent.putExtra(Intent.EXTRA_SUBJECT, shareResponse.getTitle());
                        intent.putExtra(Intent.EXTRA_TEXT, shareResponse.getTitle() + "\n" + shareResponse.getUrl().getGplusShareUrl());
                        iconSelectValue = "gmail";
                    } else if (getString(R.string.facebook_package).equals(info.activityInfo.packageName)) {
                        intent.putExtra(Intent.EXTRA_TEXT, shareResponse.getTitle() + " " + shareResponse.getUrl().getFbShareUrl());
                        iconSelectValue = "facebook";
                    } else if (getString(R.string.watsapp_package).equals(info.activityInfo.packageName)) {
                        intent.putExtra(Intent.EXTRA_TEXT, shareResponse.getTitle() + " " + shareResponse.getUrl().getWhatsappShareUrl());
                        iconSelectValue = "whatsapp";
                    } else if (getString(R.string.pinterest_package).equals(info.activityInfo.packageName)) {
                        intent.putExtra(Intent.EXTRA_TEXT, shareResponse.getTitle() + " " + shareResponse.getUrl().getWhatsappShareUrl());
                        iconSelectValue = "pinterest";
                    } else if (getString(R.string.hangout_package).equalsIgnoreCase(info.activityInfo.packageName)) {
                        intent.putExtra(Intent.EXTRA_TEXT, shareResponse.getTitle() + " " + shareResponse.getUrl().getGplusShareUrl());
                        iconSelectValue = "hangout";
                    }
//                    EventData eventData = AnalyticsHelper.getClickStreamEventData(AnalyticsHelper.EVENT_MODULE_PRODUCT, mProductId, mPageTitle, AnalyticsHelper.PRODUCT_DETAIL, mProductId, mPageTitle, "share", iconSelectValue, null, null);
//                    AnalyticsHelper.trackCleverTapAnalytics(getBaseContext(), AnalyticsHelper.CLICK_STREAM, eventData);
                }
                startActivity(intent);
                mBottomSheet.dismiss();
            }
        };
    }


    private boolean isMatch(String name) {
        return Arrays.asList(getResources().getStringArray(R.array.share_actions)).contains(name);
    }

//    public void requestPreliminaryState() {
//        Request.getRequest(getApplicationContext(), null, APIEndPoints.PRELIMINARY_STATE, new VolleyCallBack());
//    }

//    private class VolleyCallBack implements CommonVolleyCallback<String> {
//
//        @Override
//        public void onVolleySuccess(Object obj, String type) {
//            if (obj == null) return;
//            switch (type) {
//                case APIEndPoints.DELETE_TOKEN:
//                    try {
//                        JSONObject outputJson = (JSONObject) obj;
//                        if (outputJson.has("status") && outputJson.getString("status").equals("success"))
//                            requestForNewToken();
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                case APIEndPoints.GENERATE_TOKEN:
//                    hideProgressBar();
//                    TokenResponse tokenResponse = new Gson().fromJson(obj.toString(), TokenResponse.class);
//                    if (mPageType.trim().isEmpty() && PurplleApplication.getInstance().getmCurrentPage() != null && !PurplleApplication.getInstance().getmCurrentPage().trim().isEmpty()) {
//                        mPageType = PurplleApplication.getInstance().getmCurrentPage();
//                    }
//                    Util.logoutUser(getApplicationContext(), mPageType);
//                    Util.fblogout();
//                    requestPreliminaryState();
//                    updateCartCount();
//                    PreferenceUtil.saveJWToken(getApplicationContext(), tokenResponse.getToken());
//                    Toast.makeText(getApplicationContext(), getString(R.string.logout_success_message), Toast.LENGTH_SHORT).show();
//                    if (mPageType != null && (mPageType.contains("Listing_") || mPageType.equalsIgnoreCase(AnalyticsHelper.CART) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_HOME))) {
//                    }
//                    if (mPopupWindow != null) {
//                        mPopupWindow.dismiss();
//                    }
//                    break;
//                case APIEndPoints.PRELIMINARY_STATE:
//                    hideProgressBar();
//                    PreliminaryState preliminaryState = new Gson().fromJson(obj.toString(), PreliminaryState.class);
//                    String status = preliminaryState.getStatus();
//                    if (status != null && status.equalsIgnoreCase(getString(R.string.success))) {
//                        BaseUtil.saveProp(preliminaryState.getProps(), getApplicationContext());
//                        EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.LOGOUT));
//                        if (preliminaryState.getLogo() != null) {
//                            PurpllePrefManager mPreferenceManager = PurpllePrefManager.getInstance(getApplicationContext());
//                            mPreferenceManager.USER.putInt(PurpllePrefKey.IS_ELITE, 0);
//                            mPreferenceManager.GLOBAL.putString(PurpllePrefKey.IS_ELITE_DEEPLINK, preliminaryState.getLogo().getDeepLinkElite());
//                            mPreferenceManager.GLOBAL.putString(PurpllePrefKey.IS_ELITE_ADDITIONAL_DEEPLINK, preliminaryState.getLogo().getDeepLinkAdditional());
//                            mPreferenceManager.GLOBAL.putString(PurpllePrefKey.PURPLLE_LOGO, preliminaryState.getLogo().getPurplleLogo());
//                            mPreferenceManager.GLOBAL.putString(PurpllePrefKey.PURPLLE_ELITE_LOGO, preliminaryState.getLogo().getPurplleEliteLogo());
//                            mPreferenceManager.GLOBAL.putString(PurpllePrefKey.PURPLLE_ADDITIONAL_LOGO, preliminaryState.getLogo().getPurplleAdditionalLogo());
//                            EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.IS_ELITE));
//                        }
//                        if (mPageType != null && (mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_SETTINGS) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_MY_PROFILE) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_LIST_FAV_PRODUCT) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_WALLET_PASSBOOK) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_MY_ORDERS) || mPageType.equalsIgnoreCase(AnalyticsHelper.THANK_YOU_PAGE) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_MY_ACCOUNTS) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_ACCOUNT_OPTIONS) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_PROFILE_REVIEWS) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_ORDER_DETAILS) || mPageType.equalsIgnoreCase(AnalyticsHelper.PAGE_REFER_EARN))) {
//                            Route.startActivityByDeepLink(getBaseContext(), "purplle.com://offers");
//                        }
//                        if (preliminaryState.getAbTesting() != null) {
//                            //Storing  AB Experiment value for PDP screen
//                            if (preliminaryState.getAbTesting().getAbExperiment() != null) {
//                                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.AB_EXPERIMENT_PDP, preliminaryState.getAbTesting().getAbExperiment());
//                            }
//
//                            //Storing  AB Experiment value for Listing screen for Variant PopUp
//                            if (preliminaryState.getAbTesting().getPdpAbListingPage() != null) {
//                                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.LISTING_VARIANT_AB_TESTING, preliminaryState.getAbTesting().getPdpAbListingPage());
//                            }
//
//                            if (preliminaryState.getAbTesting().getPricePerUnitAbTesting() != null) {
//                                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.PRICE_PER_UNIT_AB, preliminaryState.getAbTesting().getPricePerUnitAbTesting());
//                            }
//                            //AB flag for For you section Bottom Nav
//                            if (preliminaryState.getAbTesting().getForYouAbExperiment() != null && !preliminaryState.getAbTesting().getForYouAbExperiment().trim().isEmpty()) {
//                                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.FORYOU_AB_TESTING, preliminaryState.getAbTesting().getForYouAbExperiment());
//                            }
//
//                            //Storing  AB Experiment value for Choose Freebie on Cart Page
//                            if (preliminaryState.getAbTesting().getChooseFreebieAbTesting() != null) {
//                                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.AB_CHOOSE_FREEBIE, preliminaryState.getAbTesting().getChooseFreebieAbTesting());
//                            }
//                            //Storing  AB Experiment value for Bogo offers on Cart Page
//                            if (preliminaryState.getAbTesting().getBogoAbTesting() != null) {
//                                PurpllePrefManager.getInstance(PurplleApplication.getAppContext())
//                                        .GLOBAL.putString(PurpllePrefKey.BOGO_OFFERS_AB,
//                                                preliminaryState.getAbTesting().getBogoAbTesting());
//                            }
//                            if (preliminaryState.getAbTesting().getAtcPopupRecosExpFlag() != null) {
//                                PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.putString(PurpllePrefKey.ATC_POPUP_RECOS_AB_EXPERIMENT, preliminaryState.getAbTesting().getAtcPopupRecosExpFlag());
//                            }
//                        }
//                        Util.setABTestingScreenPreference(new Gson(), preliminaryState.getScreenABTesting());
//                        requestForYouAPI();
//                    }
//                    break;
//            }
//        }
//
////        @Override
////        public void onVolleyError(String response, String msg, int statusCode, String type, String moduleType, Object tag) {
////            hideProgressBar();
////            if (type != null && type.equalsIgnoreCase(APIEndPoints.PRELIMINARY_STATE)) {
////                EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.LOGOUT));
////            }
////            if (msg != null && !msg.trim().isEmpty()) {
////                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
////            }
////        }
//    }

//    protected void requestForYouAPI() {
//        HashMap<String, String> params = new HashMap<>();
//        if (PreferenceUtil.getUserId(getApplicationContext()) != null) {
//            String encodedUserId = Base64.encodeToString(PreferenceUtil.getUserId(getApplicationContext()).getBytes(), Base64.DEFAULT);
//            params.put(getString(R.string.user_Id), encodedUserId);
//        } else {
//            params.put("", "");
//        }
//        Request.postRequest(getApplicationContext(), params, PreferenceUtil.getForYouAPIUrl(), new CommonVolleyCallback() {
//            @Override
//            public void onVolleySuccess(Object obj, Object type) {
//                ForYouResponse forYouResponse = new Gson().fromJson(obj.toString(), ForYouResponse.class);
//                if (forYouResponse.getData() != null) {
//                    PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putString(PurpllePrefKey.FOR_YOU_IMAGE, forYouResponse.getData().getImageUrl());
//                    PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putString(PurpllePrefKey.GRATIFICATION_DEEPLINK, forYouResponse.getData().getDeepLink());
//                }
//                //EventBus.getDefault().post(new EventBusMessage(EventBusMessage.MessageType.FOR_YOU_UPDATED));
//            }
//
////            @Override
////            public void onVolleyError(String response, String msg, int statusCode, Object type, @Nullable String moduleType, @Nullable Object tag) {
////
////            }
//        });
//    }

//    @Override
//    public void onUserInteraction() {
//        super.onUserInteraction();
//        Util.sendSessionDetailsEvent(this instanceof SplashActivity, getIntent());
//    }

//    public void setLoginErrorEvent(String message, String moduleType, HashMap<String, String> params, String request, String response, int statusCode) {
//        EventData errorEventData = AnalyticsHelper.getApplicationErrorData(AnalyticsHelper.PAGE_LOGIN, "default", "", 0, "page", request, moduleType, message, params, response, statusCode);
//        AwsKinesisHelper.saveEventRecord(getApplicationContext(), AwsKinesisHelper.ACTIVITY_RESPONSE, errorEventData);
//    }

    public static String getFlutterSearchPageType() {
        return mFlutterSearchPageType;
    }

    public static String getFlutterSearchPageTypeValue() {
        return mFlutterSearchPageTypeValue;
    }

    public static String getFlutterSearchXId() {
        return mFlutterSearchXId;
    }

    @Override
    public void onClick(View view) {

    }

//    private void updateEncryptedJwt() {
//
//        String encryptedJwt = PurpllePrefManager.getInstance(this).ENCRYPTED.getEncryptedString(PurpllePrefKey.JWTTOKEN, "");
//        // default is empty string for encryptedJwt
//
//        //For migration
//        /*Check if encrypted preference contains any non empty jwt token
//        if the encrypted token is non empty then no need to store the jwt in encrypted preference
//        */
//        if (!encryptedJwt.trim().isEmpty()) {
//            return;
//        }
//
//        /* check for shared pref jwt token
//        if the shared pref jwt token is also empty, there is no jwt token saved on device,
//         */
//
//        String jwtToken = PurpllePrefManager.getInstance(this).GLOBAL.getString(PurpllePrefKey.JWTTOKEN, "");
//        if (jwtToken.trim().isEmpty()) {
//            return;
//        }
//
//        // if jwtToken is not empty, store the jwt in Encrypted preference
//        PurpllePrefManager.getInstance(this).ENCRYPTED.saveEncryptedString(PurpllePrefKey.JWTTOKEN, jwtToken);
////        PurpllePrefManager.getInstance(this).GLOBAL.putString(PurpllePrefKey.JWTTOKEN, "");
//    }

    }

