package com.kotlingithubapi.utils

import android.content.res.Resources

/**
 * Created by User on 13.09.2017.
 */
fun pxToDp(px: Float): Float {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi
    return px / (densityDpi / 160f)
}

fun dpToPx(dp: Float): Int {
    val density = Resources.getSystem().displayMetrics.density
    return Math.round(dp * density)
}
