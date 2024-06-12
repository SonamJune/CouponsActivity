package com.manash.purpllebase.permissionManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.manash.purpllebase.R;

import java.util.List;
import java.util.Set;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static androidx.core.content.ContextCompat.checkSelfPermission;
import static com.manash.purpllebase.permissionManager.PermissionsActivity.EXTRA_PERMISSIONS_DENIED;
import static com.manash.purpllebase.permissionManager.PermissionsActivity.EXTRA_PERMISSIONS_GRANTED;


public class PermissionManager extends BroadcastReceiver {

    private Context context;
    private static PermissionManager permissionManager;
    private PermissionHandler permissionHandler;

    private PermissionManager(Context context) {
        this.context = context;
        this.permissionHandler = new PermissionHandler(this);
    }

    public static PermissionManager getInstance(Context context) {
        if (permissionManager == null) {
            permissionManager = new PermissionManager(context.getApplicationContext());
        }
        return permissionManager;
    }

    public void checkPermissions(@NonNull String[] permissions, String msg, @NonNull final PermissionRequestListener listener) {
        permissionHandler.checkPermissions(permissions, msg, listener);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String[] grantedPermissions = intent.getStringArrayExtra(EXTRA_PERMISSIONS_GRANTED);
        String[] deniedPermissions = intent.getStringArrayExtra(EXTRA_PERMISSIONS_DENIED);

        permissionHandler.onPermissionsResult(grantedPermissions, deniedPermissions);
    }

    protected void startPermissionActivity(Set<String> permissions, String msg) {
        Intent intent = new Intent(context, PermissionsActivity.class);
        intent.putExtra(PermissionsActivity.EXTRA_PERMISSIONS, permissions.toArray(new String[permissions.size()]));
        intent.putExtra(context.getString(R.string.msg), msg);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    protected boolean permissionAlreadyGranted(String permission) {
        return checkSelfPermission(context, permission) == PERMISSION_GRANTED;
    }

    protected void registerBroadcastReceiver(String action) {
        LocalBroadcastManager.getInstance(context).registerReceiver(this, new IntentFilter(action));
    }

    protected void unregisterBroadcastReceiver() {
        LocalBroadcastManager.getInstance(context).unregisterReceiver(this);
    }

    protected void removePendingPermissionRequests(List<String> permissions) {
        permissionHandler.invalidatePendingPermissionRequests(permissions);
    }

    public interface PermissionRequestListener {
        void onPermissionGranted(String permission);

        void onPermissionDenied(String permission);
    }
}