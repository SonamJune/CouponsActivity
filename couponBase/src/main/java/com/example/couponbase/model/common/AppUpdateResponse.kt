package com.manash.purpllebase.model.common

data class AppUpdateResponse(
    val isForceUpdate: Boolean,
    val isShowAppUpdateAlert: Boolean,
    val newReleaseAppVersion: Int,
    val timeDuration: Int
)