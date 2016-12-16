package com.andrestorres.oneup.ui.widget;

import android.app.Dialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.andrestorres.oneup.R;

/**
 * Created by andrestorres on 3/15/16.
 */
public class LoadingDialog extends DialogFragment {

    public static LoadingDialog newInstance() {
        LoadingDialog loadingDialog = new LoadingDialog();
        loadingDialog.setCancelable(false);
        return loadingDialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.fragment_loading_dialog, null);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int color = 0;

        view.setBackground(new ColorDrawable(color));

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(color));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.fragment_loading_dialog_progress_bar);

        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAccent), PorterDuff.Mode.MULTIPLY);

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();

        if (getDialog() == null)
            return;

        final float scale = getActivity().getApplicationContext().getResources().getDisplayMetrics().density;
        int dialogWidth = (int) (100 * scale + 0.5f);
        int dialogHeight = (int) (100 * scale + 0.5f);


        if (dialogWidth == 0)
            dialogWidth = LinearLayout.LayoutParams.WRAP_CONTENT;

        if (dialogHeight == 0)
            dialogHeight = LinearLayout.LayoutParams.WRAP_CONTENT;


        getDialog().getWindow().setLayout(dialogWidth, dialogHeight);
    }
}
