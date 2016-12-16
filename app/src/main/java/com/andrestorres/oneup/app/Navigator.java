package com.andrestorres.oneup.app;

/**
 * Created by Oscar on 11/8/15.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class Navigator {

    private static Intent intent;

    private Navigator() {}

    public static void startActivity(Context packageContext, Class<?> cls) {
        intent = new Intent(packageContext, cls);
        packageContext.startActivity(intent);
    }

    public static void startActivity(Context packageContext, Class<?> cls, int flags) {
        intent = new Intent(packageContext, cls);
        intent.setFlags(flags);
        packageContext.startActivity(intent);
    }

    public static void startActivity(Context packageContext, Class<?> cls, Bundle extras) {
        intent = new Intent(packageContext, cls);
        intent.putExtras(extras);
        packageContext.startActivity(intent);
    }

    public static void startActivity(Context packageContext, Class<?> cls, int flags, Bundle extras) {
        intent = new Intent(packageContext, cls);
        intent.setFlags(flags);
        intent.putExtras(extras);
        packageContext.startActivity(intent);
    }


}
