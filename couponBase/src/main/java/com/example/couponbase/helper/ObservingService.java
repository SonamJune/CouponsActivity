package com.example.couponbase.helper;

import com.example.couponbase.util.OnDataChangeListener;

import java.util.HashSet;
import java.util.Set;

public class ObservingService {
    private static final Object lock = new Object();
    private static ObservingService mInstance;
    private Set<OnDataChangeListener> observables;

    public ObservingService() {
        observables = new HashSet<>();
    }

    public static ObservingService getInstance() {
        if (mInstance == null) {
            synchronized (lock) {
                if (mInstance == null) {
                    mInstance = new ObservingService();
                }
            }
        }
        return mInstance;
    }

    public void addObserver(OnDataChangeListener observer) {
        observables.add(observer);
    }

    public void removeAllObserver() {
        if (observables != null) {
            observables.clear();
        }
    }

    public void post(String id, Object value, int type) {
        for (OnDataChangeListener observer : observables) {
            observer.onDataChanged(id, value, type);
        }
    }
}