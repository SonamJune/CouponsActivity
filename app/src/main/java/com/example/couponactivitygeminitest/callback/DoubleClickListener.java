package com.example.couponactivitygeminitest.callback;

import android.content.Context;
import android.view.View;

public abstract class DoubleClickListener implements View.OnClickListener {


    private boolean isRunning = false;
    private int resetInTime = 500;
    private int counter = 0;


    @Override
    public void onClick(View v) {
        if (isRunning) {
            if (counter == 1) //<-- makes sure that the callback is triggered on double click
                onDoubleClick(v);
        }
        counter++;
        if (!isRunning) {
            isRunning = true;
            new Thread(() -> {
                try {
                    Thread.sleep(resetInTime);
                    isRunning = false;
                    counter = 0;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }


    public abstract void onDoubleClick(View v);
}
