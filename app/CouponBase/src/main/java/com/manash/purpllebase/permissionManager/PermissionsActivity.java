package com.manash.purpllebase.permissionManager;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.manash.purpllebase.R;
import com.manash.purpllebase.util.BaseUtil;

import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class PermissionsActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CODE = 100;
    private static final int SETTING_REQUEST_CODE = 200;
    public static final String EXTRA_PERMISSIONS = "PERMISSIONS";

    public static final String EXTRA_PERMISSIONS_GRANTED = "PERMISSIONS_GRANTED";
    public static final String EXTRA_PERMISSIONS_DENIED = "PERMISSIONS_DENIED";

    private HashSet<String> dontAskPermissions;
    private String msg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] permissions = getIntent().getStringArrayExtra(EXTRA_PERMISSIONS);
        msg = getIntent().getStringExtra(getString(R.string.msg));
        ActivityCompat.requestPermissions(this, permissions, PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length == 0 || permissions.length == 0) {
            PermissionManager.getInstance(this)
                    .removePendingPermissionRequests(asList(getIntent().getStringArrayExtra(EXTRA_PERMISSIONS)));

            finish();
            return;
        }

        sendPermissionResponse(permissions, grantResults);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void sendPermissionResponse(@NonNull String[] permissions, @NonNull int[] grantResults) {
        Set<String> grantedPermissions = new HashSet<>();
        Set<String> deniedPermissions = new HashSet<>();
        dontAskPermissions = new HashSet<>();
        for (int i = 0; i < permissions.length; i++) {
            if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                grantedPermissions.add(permissions[i]);
            } else if (shouldShowRequestPermissionRationale(permissions[i])) {
                deniedPermissions.add(permissions[i]);
            } else {
                dontAskPermissions.add(permissions[i]);
            }
        }
        if (!grantedPermissions.isEmpty() || !deniedPermissions.isEmpty()) {
            new BroadcastService(this).broadcastPermissionRequestResult(grantedPermissions, deniedPermissions);
            finish();
        } else {
            showApplicationSettingDailog();
        }
    }

    private void showApplicationSettingDailog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.permission_required));
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.open_setting, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + getPackageName())), SETTING_REQUEST_CODE);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                        @Override
                                        public void onCancel(DialogInterface dialog) {
                                            Toast.makeText(getBaseContext(), R.string.permission_denied, Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    }
        );
        AlertDialog create = builder.create();
        create.show();
        BaseUtil.setAlertDialogFont(this, create);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (dontAskPermissions != null) {
            ActivityCompat.requestPermissions(this, dontAskPermissions.toArray(new String[dontAskPermissions.size()]), PERMISSIONS_REQUEST_CODE);
        }
    }
}