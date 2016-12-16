package com.andrestorres.oneup.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import com.andrestorres.oneup.ui.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andrestorres on 3/15/16.
 */
public class UIUtils {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setTranslucentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;

        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setStatusBarPadding(final Context context, final View view) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) return;

        ViewTreeObserver.OnGlobalLayoutListener globalLayoutListener;
        final ViewTreeObserver vto = view.getViewTreeObserver();
        globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                view.setPadding(0, getStatusBarHeight(context), 0, 0);
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        };

        vto.addOnGlobalLayoutListener(globalLayoutListener);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }

        return result;
    }

    private static LoadingDialog loaderDialog;
    private static List<LoadingDialog> loaderDialogList = new ArrayList<>();

    public static void showProgressDialog(FragmentActivity activity) {
        loaderDialog = LoadingDialog.newInstance();

        loaderDialogList.add(loaderDialog);

        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.add(loaderDialog, "InformationAlertDialogFragment");
        ft.commitAllowingStateLoss();
    }

    public static void dismissProgressDialog() {
        for (LoadingDialog ldf: loaderDialogList) {
            ldf.dismissAllowingStateLoss();
        }
    }

}
