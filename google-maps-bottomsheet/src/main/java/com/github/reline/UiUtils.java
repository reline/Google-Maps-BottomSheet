package com.github.reline;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Px;
import android.util.DisplayMetrics;

class UiUtils {

    static @Px int convertDpToPixel(Context context, float dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
