package com.example.couponbase.views;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.KeyEvent;

public class ProgressDialogFragment extends DialogFragment {

    public static ProgressDialogFragment newInstance() {
        return new ProgressDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final ProgressDialog dialog = new ProgressDialog(getActivity());

        String dialogMessage = null;
        if (getArguments() != null)
            dialogMessage = getArguments().getString("dialog_message");

        if (dialogMessage == null)
            dialog.setMessage("Please wait....");
        else
            dialog.setMessage(dialogMessage);

        dialog.setIndeterminate(true);
        dialog.setCancelable(false);

        // Disable the back button
        DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {

                return keyCode == KeyEvent.KEYCODE_BACK;
            }


        };
        dialog.setOnKeyListener(keyListener);
        return dialog;
    }


}