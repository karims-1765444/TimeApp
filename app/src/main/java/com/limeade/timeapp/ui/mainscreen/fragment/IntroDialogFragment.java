package com.limeade.timeapp.ui.mainscreen.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.limeade.timeapp.R;

/**
 *  A DialogFragment used to create an introductory
 *  dialog message on launch of the main activity
 */

public class IntroDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.IntroMessage)
                .setPositiveButton(R.string.confirmIntroMessage, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // nothing to do, dismisses dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}