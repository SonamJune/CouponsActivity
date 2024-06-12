package com.manash.purpllebase.permissionManager;

import androidx.annotation.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;

class PermissionHandler {
    private PermissionManager manager;
    private HashMap<String, PermissionManager.PermissionRequestListener> requiredPermissionsMap = new HashMap<>();
    private Set<String> pendingPermissionRequests = new HashSet<>();

    PermissionHandler(PermissionManager manager) {
        this.manager = manager;

    }

    public void checkPermissions(String[] permissions, String msg, PermissionManager.PermissionRequestListener listener) {
        Set<String> permissionsToRequest = filterGrantedPermissions(permissions, listener);

        if (permissionsToRequest.isEmpty()) {
            listener.onPermissionGranted("all");
        } else {
            registerForBroadcastIfNeeded(BroadcastService.IntentAction.ACTION_PERMISSIONS_REQUEST);
            if (!permissionsToRequest.isEmpty()) {
                requestPermissions(permissionsToRequest, msg);
            }
        }
    }

    protected void onPermissionsResult(String[] grantedPermissions, String[] deniedPermissions) {
        informPermissionsDenied(deniedPermissions);
        informPermissionsGranted(grantedPermissions);
        pendingPermissionRequests.removeAll(asList(grantedPermissions));
        pendingPermissionRequests.removeAll(asList(deniedPermissions));
        if (pendingPermissionRequests.isEmpty()) {
            manager.unregisterBroadcastReceiver();
        }
    }


    private void requestPermissions(Set<String> permissions, String msg) {
        pendingPermissionRequests.addAll(permissions);
        manager.startPermissionActivity(permissions, msg);
    }

    void invalidatePendingPermissionRequests(Collection<String> permissions) {
        pendingPermissionRequests.removeAll(permissions);
        informPermissionsDenied(permissions.toArray(new String[permissions.size()]));

        if (pendingPermissionRequests.isEmpty()) {
            manager.unregisterBroadcastReceiver();
        }
    }

    private void informPermissionsDenied(String[] deniedPermissions) {

        for (String deniedPermission : deniedPermissions) {
            if (requiredPermissionsMap.containsKey(deniedPermission)) {
                requiredPermissionsMap.get(deniedPermission).onPermissionDenied(deniedPermission);
                requiredPermissionsMap.remove(deniedPermission);
            }
        }
    }

    private void informPermissionsGranted(String[] grantedPermissions) {
        for (String grantedPermission : grantedPermissions) {
            if (requiredPermissionsMap.containsKey(grantedPermission)) {
                requiredPermissionsMap.get(grantedPermission).onPermissionGranted(grantedPermission);
                requiredPermissionsMap.remove(grantedPermission);
            }
        }
    }

    private void registerForBroadcastIfNeeded(String action) {
        if (pendingPermissionRequests.isEmpty()) {
            manager.registerBroadcastReceiver(action);
        }
    }

    @NonNull
    private Set<String> filterGrantedPermissions(String[] permissions, PermissionManager.PermissionRequestListener listener) {
        Set<String> permissionsToRequest = new HashSet<>();
        for (String permission : permissions) {
            if (permission != null && !manager.permissionAlreadyGranted(permission)) {
                permissionsToRequest.add(permission);
                requiredPermissionsMap.put(permission, listener);
            }
        }
        return permissionsToRequest;
    }

    private void filterPendingPermissions(Set<String> permissionsToRequest) {
        permissionsToRequest.removeAll(pendingPermissionRequests);
    }
}