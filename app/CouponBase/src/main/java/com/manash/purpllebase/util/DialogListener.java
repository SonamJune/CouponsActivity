package com.manash.purpllebase.util;

public interface DialogListener {
    int YES = 0;
    int NO = 1;
    int CANCEL = 2;

    void onAction(int action);
}