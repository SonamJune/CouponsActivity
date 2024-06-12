package com.example.couponbase.activity;

import androidx.appcompat.app.AppCompatActivity;

/**
 * base activity for activities of application so that common code and callbacks can be added here.
 */

public class BaseActivity extends AppCompatActivity {
    //private static AppUpdateManager appUpdateManager;
    private static final int RC_APP_UPDATE=659;

    private static final int RC_APP_UPDATE_FORCE=660;
    public static boolean appInstall=false;
//    @Override
//    protected void onStart() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
////            if (!EventBus.getDefault().isRegistered(this)) {
////                EventBus.getDefault().register(this);
////            }        }
//        super.onStart();
//    }
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(LocaleManager.setLocale(base));
//    }
//    @Override
//    public void onDestroy() {
////        if (EventBus.getDefault().isRegistered(this)) {
////            EventBus.getDefault().unregister(this);
////        }
////        if (appUpdateManager != null) {
////            appUpdateManager.unregisterListener(installStateUpdatedListener);
////            // appUpdated=false;
////        }
//        super.onDestroy();
//    }
//
//    public void setForForceUpdate(){
//        if(appUpdateManager==null) {
//            appUpdateManager = AppUpdateManagerFactory.create(this);
//        }
//        // Returns an intent object that you use to check for an update.
//        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
//        appUpdateInfoTask.addOnSuccessListener(result -> {
//            if(result.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE)
//            {
//                try {
//                    appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.IMMEDIATE,BaseActivity.this,RC_APP_UPDATE_FORCE);
//                } catch (IntentSender.SendIntentException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }).addOnFailureListener(e -> Toast.makeText(BaseActivity.this, getResources().getString(R.string.something_went_wrong)
//                , Toast.LENGTH_SHORT).show());
//
//    }
//    public void setForFlexible() {
//        if(appUpdateManager==null) {
//            appUpdateManager = AppUpdateManagerFactory.create(this);
//        }
//        // Returns an intent object that you use to check for an update.
//        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
//        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
//            @Override
//            public void onSuccess(AppUpdateInfo result) {
//                if(result.updateAvailability()== UpdateAvailability.UPDATE_AVAILABLE
//                        && result.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)){
//                    try {
//                        appUpdateManager.startUpdateFlowForResult(result,AppUpdateType.FLEXIBLE,BaseActivity.this,RC_APP_UPDATE);
//                    } catch (IntentSender.SendIntentException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(Exception e) {
//                Toast.makeText(BaseActivity.this, getResources().getString(R.string.something_went_wrong)
//                        , Toast.LENGTH_SHORT).show();
//            }
//        });
//        appUpdateManager.registerListener(installStateUpdatedListener);
//    }
//    private final InstallStateUpdatedListener installStateUpdatedListener= new InstallStateUpdatedListener() {
//        @Override
//        public void onStateUpdate(@NonNull InstallState state) {
//            if(state.installStatus() == InstallStatus.DOWNLOADED){
//                appInstall=true;
//                // showCompleteUpdate();
//            }
//
//        }
//    };
//    public void showCompleteUpdate() {
//        Snackbar snackbar=Snackbar.make(findViewById(android.R.id.content),getResources().getString(R.string.new_app_ready),
//                Snackbar.LENGTH_INDEFINITE);
//        snackbar.setAction(getResources().getString(R.string.install), new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if(appUpdateManager!=null) {
////                    appUpdateManager.completeUpdate();
////                    appInstall=false;
////                    appUpdateManager=null;
////                }
//            }
//        });
//        snackbar.show();
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(appInstall){
//            showCompleteUpdate();
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==RC_APP_UPDATE_FORCE){
//            if (resultCode != RESULT_OK){
//                setForForceUpdate();
//            }
//
//        }
//    }
}