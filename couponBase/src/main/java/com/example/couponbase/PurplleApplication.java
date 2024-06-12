package com.example.couponbase;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;
//import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
//import com.clevertap.android.sdk.ActivityLifecycleCallback;
//import com.clevertap.android.sdk.CleverTapAPI;
//import com.clevertap.android.sdk.interfaces.NotificationHandler;
import com.datatheorem.android.trustkit.TrustKit;
import com.facebook.FacebookSdk;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.reflect.TypeToken;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.crashlytics.FirebaseCrashlytics;
//import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
//import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.google.gson.Gson;
import com.example.couponbase.helper.ApplicationStatus;
import com.example.couponbase.helper.PurplleTrace;
import com.example.couponbase.helper.Tls12SocketFactory;
import com.example.couponbase.model.EventBusMessage;
import com.example.couponbase.model.remoteConfig.AmplificationResponse;
import com.example.couponbase.model.remoteConfig.Events;
import com.example.couponbase.preference.PreferenceUtil;
import com.example.couponbase.preference.PurpllePrefKey;
import com.example.couponbase.preference.PurpllePrefManager;
import com.example.couponbase.util.localization.LocaleManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import io.branch.referral.Branch;
//import io.flutter.embedding.engine.FlutterEngineGroup;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.TlsVersion;

public class PurplleApplication extends Application implements ApplicationStatus.Listener {

    private static final String TAG = "PurplleApplication";
    private static PurplleApplication mInstance;
    private String mPreviousPage;
    private String mCurrentPage;
    private String mCurrentPageTypeValue;
    private String mPreviousPageTypeValue;
    private String mPageTitle;
    public static long mLastSessionTime;
    private List<String> mBeautyTips;
    private boolean mIsRemoteConfigActivated;
    private List<Events> mAmplificationEvents;
    private static Context context;
//    public FlutterEngineGroup flutterEngineGroup;
    private int isShowArrowButton=0;
    private int isShowTryOnTooltip=0;


    public int getIsShowTryOnTooltip() {
        return isShowTryOnTooltip;
    }

    public void setIsShowTryOnTooltip(int isShowTryOnTooltip) {
        this.isShowTryOnTooltip = isShowTryOnTooltip;
    }

    public  int getIsShowArrowButton() {
        return isShowArrowButton;
    }

    public  void setIsShowArrowButton(int value) {
        isShowArrowButton = value;
    }

    public static PurplleApplication getInstance() {
        return mInstance;
    }

    //get the application context entire the application
    public static Context getAppContext() {
        return PurplleApplication.context;
    }

    public PurplleApplication() {
        super();
    }

    @Override
    public void onCreate() {
        //CleverTapAPI.setNotificationHandler((NotificationHandler) new PushTemplateNotificationHandler());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        super.onCreate();
        TrustKit.initializeWithNetworkSecurityConfiguration(this);

        //set context for application
        PurplleApplication.context = getApplicationContext();
        // Instantiate a FlutterEngine group.
//        flutterEngineGroup = new FlutterEngineGroup(this);
//        flutterEngineGroup.createAndRunDefaultEngine(this);

        ApplicationStatus.init(this).addListener(this);

        // Firebase Crashlytics Settings
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
//        FirebaseCrashlytics crashlytics = FirebaseCrashlytics.getInstance();
//        if (crashlytics != null) {
//            crashlytics.setUserId(PreferenceUtil.getDeviceId(getApplicationContext()));
//            if (getApplicationInfo() != null) {
//                crashlytics.setCustomKey("nativeLibraryDir", getApplicationInfo().nativeLibraryDir);
//            }
//        }else {
//            PurplleTrace.i(TAG, "Crashlytics Initiation failed");
//        }
//        initFirebaseRemoteConfig();

        mInstance = this;

        //init branch
        Branch.getAutoInstance(this);
        Branch.getInstance().disableTracking(false);
        Branch.getAutoInstance(this).enableFacebookAppLinkCheck();

        //init facebook sdk
        FacebookSdk.sdkInitialize(this);

        //set google advertising id
        setGoogleAdvertisingId(this);
    }

    public String getmCurrentPage() {
        return mCurrentPage;
    }

    public String getmCurrentPageTypeValue() {
        return mCurrentPageTypeValue;
    }

    public String getmPageTitle() {
        return mPageTitle;
    }

//    private void initFirebaseRemoteConfig() {
//        if (!FirebaseApp.getApps(this).isEmpty()) {
//            final long time = System.currentTimeMillis();
//            PurplleTrace.i("remote_config", "curr time " + System.currentTimeMillis() + " time with limit " + time);
//            final FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
//            FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
//                    .build();
//
//            firebaseRemoteConfig.setConfigSettingsAsync(configSettings);
//            firebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults);
//            firebaseRemoteConfig.fetch(0)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            PurplleTrace.i("remote_config", "Remote Config onComplete time stamp " + System.currentTimeMillis());
//                            PurplleTrace.i("remote_config", "Remote Config onComplete time diff " + (System.currentTimeMillis() - time));
//                            if (task.isSuccessful()) {
//                                firebaseRemoteConfig.activate();
//                                PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putBoolean(getString(R.string.is_clevertap_enabled), FirebaseRemoteConfig.getInstance().getBoolean(getString(R.string.is_clevertap_enabled)));
//                                downloadBeautyTips();
//                                fetchThirdPartyToggleValues();
//                                initEventAmplification();
//                                if (PreferenceUtil.isFirstTimeAppLaunch(getApplicationContext()) && (System.currentTimeMillis() - time < 5000)) {
//                                    mIsRemoteConfigActivated = true;
//                                    PurplleTrace.i("remote_config", "Remote config activated");
//                                    // After config data is successfully fetched, it must be activated before newly fetched
//                                    // values are returned.
//                                    EventBusMessage eventBusMessage = new EventBusMessage(EventBusMessage.MessageType.REMOTE_CONFIG_ACTIVATED);
//                                    EventBus.getDefault().post(eventBusMessage);
//                                }
//                            } else {
//                                PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putBoolean(getString(R.string.is_clevertap_enabled), FirebaseRemoteConfig.getInstance().getBoolean(getString(R.string.is_clevertap_enabled)));
//                            }
//
////                            if(PurpllePrefManager.getInstance(PurplleApplication.getAppContext()).GLOBAL.getBoolean(PurplleApplication.getAppContext().getString(com.example.couponbase.R.string.is_clevertap_enabled), true)) {
////                                ActivityLifecycleCallback.register(PurplleApplication.this);
////                            }
//                        }
//                    });
//        }
//    }

//    private void fetchThirdPartyToggleValues() {
//        try {
//            JSONObject thirdPartyToggleJson = new JSONObject(FirebaseRemoteConfig.getInstance().getString(getString(R.string.third_party_toggle_android)));
//            PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putBoolean(getString(R.string.bureau_toggle), thirdPartyToggleJson.getBoolean(getString(R.string.bureau_toggle)));
//            PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putBoolean(getString(R.string.truecaller_toggle), thirdPartyToggleJson.getBoolean(getString(R.string.truecaller_toggle)));
//            PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putBoolean(getString(R.string.reblaze_toggle), thirdPartyToggleJson.getBoolean(getString(R.string.reblaze_toggle)));
//            PurpllePrefManager.getInstance(getApplicationContext()).GLOBAL.putBoolean(getString(R.string.trustkit_toggle), thirdPartyToggleJson.getBoolean(getString(R.string.trustkit_toggle)));
//        } catch (Exception ignored) {
//        }
//    }
//
//    private void initEventAmplification() {
//        String resposneString = FirebaseRemoteConfig.getInstance().getString(getString(R.string.ampliifcation_list));
//        if (resposneString != null && !resposneString.isEmpty()) {
//            AmplificationResponse response = new Gson().fromJson(resposneString, AmplificationResponse.class);
//            if (response != null && response.getEvents() != null) {
//                mAmplificationEvents = response.getEvents();
//            }
//        }
//    }
    public OkHttpClient getHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
        List<Protocol> protocols = new ArrayList<>();
        protocols.add(Protocol.HTTP_2);
        protocols.add(Protocol.HTTP_1_1);

        SSLContext sc = SSLContext.getInstance("TLSv1.2");
        sc.init(null, null, null);
        sc.getSupportedSSLParameters();
        SSLParameters param = sc.getSupportedSSLParameters();
        for (int i = 0; i < param.getCipherSuites().length; i++) {
            PurplleTrace.i("PROTOCOL", " ." + param.getCipherSuites()[i]);

        }
        for (int i = 0; i < param.getProtocols().length; i++) {
            PurplleTrace.i("PROTOCOL", " ." + param.getProtocols()[i]);

        }

        ConnectionSpec cs = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2)
                .build();

        List<ConnectionSpec> specs = new ArrayList<>();
        specs.add(cs);
        specs.add(ConnectionSpec.COMPATIBLE_TLS);
        specs.add(ConnectionSpec.CLEARTEXT);

        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        try {
            trustManagerFactory.init((KeyStore) null);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        }
        X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

        return new OkHttpClient.Builder()
                .protocols(protocols)
                .connectionSpecs(specs)
                .sslSocketFactory(new Tls12SocketFactory(sc.getSocketFactory()), trustManager)
                .build();
    }

//    public void downloadBeautyTips() {
//        String tipsString = FirebaseRemoteConfig.getInstance().getString(getString(R.string.beauty_tips_list));
//        if (tipsString != null && !tipsString.trim().isEmpty()) {
//            Type listType = new TypeToken<List<String>>() {
//            }.getType();
//            mBeautyTips = new Gson().fromJson(tipsString, listType);
//        }
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleManager.setLocale(base));
        MultiDex.install(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleManager.setLocale(this);
    }

    public String getBeautyTip() {
        int randomNo = new Random().nextInt(15);
        if (mBeautyTips != null && randomNo < mBeautyTips.size()) {
            return mBeautyTips.get(randomNo);
        }
        return "";
    }

    public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                    PurplleTrace.i(TAG, "File /data/data/APP_PACKAGE/" + s + " DELETED");
                }
            }
        }
    }

    public boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }


    public String getPreviousPage() {
        return mPreviousPage;
    }

    //set defaults page types in case value is not yet come from API
    //previous page remains the same, hence not assigned here
    public void setDefaultCurrentPage(String pageType, String pageTypeValue, String title) {
        mPreviousPage = mCurrentPage;
        mCurrentPage = pageType;
        mCurrentPageTypeValue = pageTypeValue;
        mPageTitle = title;
    }

    public void setCurrentPage(String currentPage, String pageTypeValue, String pageTitle) {
        if(currentPage != null && pageTypeValue != null) {
            if (currentPage.equalsIgnoreCase("my_profile_reviews")
                    || currentPage.equalsIgnoreCase("listing_thread")
                    || currentPage.equalsIgnoreCase("my_favourites")
                    || currentPage.equalsIgnoreCase("cart")) {

                if (!currentPage.equals(mCurrentPage)) {
                    mPreviousPage = mCurrentPage;
                    mCurrentPage = currentPage;
                }

                if (!pageTypeValue.equals(mCurrentPageTypeValue)) {
                    mPreviousPageTypeValue = mCurrentPageTypeValue;
                    mCurrentPageTypeValue = pageTypeValue;
                }
            } else {
                mPreviousPage = mCurrentPage;
                mCurrentPage = currentPage;
                mPreviousPageTypeValue = mCurrentPageTypeValue;
                mCurrentPageTypeValue = pageTypeValue;
            }
            mPageTitle = pageTitle;
        }
        PurplleTrace.i("PAGE_VIEW", "curr page " + mCurrentPage + " prev page" + mPreviousPage);
        PurplleTrace.i("PAGE_VIEW", "curr page type value " + mCurrentPageTypeValue + " prev page type value" + mPreviousPageTypeValue);
    }

    public String getPreviousPageTypeValue() {
        return mPreviousPageTypeValue;
    }

    public  static void setGoogleAdvertisingId(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);

                    PurplleTrace.i("GoogleAnalytics", "isLimitAdTrackingEnabled " + advertisingIdInfo.isLimitAdTrackingEnabled());

                    if (advertisingIdInfo != null) {
                        String id = advertisingIdInfo.getId();
                        PurpllePrefManager.getInstance(context).GLOBAL.putString(PurpllePrefKey.GA_ID, id);
                    }
                } catch (IllegalStateException | GooglePlayServicesRepairableException | IOException | GooglePlayServicesNotAvailableException e) {
                    // GoogleAnalyticsHelper.pushCatchException(e, getInstance());
                }
            }
        }).start();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        PurplleTrace.i("App_Memory", "onTrimMemory level " + level);
        if (getCacheDir() == null || getCacheDir().listFiles() == null) return;
        for (File f : getCacheDir().listFiles()) {
            if (f != null) {
                String fileName = f.getName();
                PurplleTrace.i("onTrimMemory", "level " + level);
                if (fileName.equalsIgnoreCase("volley") /*|| fileName.equalsIgnoreCase("image_cache")*/
                        || fileName.equalsIgnoreCase("picasso-cache")) {
                    deleteDir(f);
                }
            }
        }
    }

    @Override
    public void onBecameForeground() {
        //SocketConnection.getInstance().open();
    }

    @Override
    public void onBecameBackground() {
        //SocketConnection.getInstance().close();
    }

    public List<Events> getAplificationEvents() {
        return mAmplificationEvents;
    }

    public void addApplicationLifecycleObserver(@NonNull LifecycleObserver lifecycleObserver) {
        ProcessLifecycleOwner.get().getLifecycle().addObserver(lifecycleObserver);
    }

    public void removeApplicationLifecycleObserver(@NonNull LifecycleObserver lifecycleObserver) {
        ProcessLifecycleOwner.get().getLifecycle().removeObserver(lifecycleObserver);
    }
}