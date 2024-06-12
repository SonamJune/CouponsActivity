package com.example.couponbase.views;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

public class EditTextWatcher implements TextWatcher {
    private View view;
    private EditTextChangeListener listener;

    public EditTextWatcher(View view, EditTextChangeListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        listener.setonEditTextChanged(view, charSequence);
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    public interface EditTextChangeListener {
        void setonEditTextChanged(View view, CharSequence charSequence);
    }
}
