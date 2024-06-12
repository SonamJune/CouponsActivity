package com.example.couponactivitygeminitest.callback;

public interface BSActivityAdapterEventBus {
    void animateCart(int position);

    void animateLike(int position);

   // void animateFollow(int position);

    void onPause(int position);

    void mute();

    void pause();

    void play();

    void notifyDataChange();
}
