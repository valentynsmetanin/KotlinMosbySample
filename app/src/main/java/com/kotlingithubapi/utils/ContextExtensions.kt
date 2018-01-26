package com.kotlingithubapi.utils

import android.content.Context
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by Valentyn on 16.07.2017.
 */
fun Context.toast(message: CharSequence)
        = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(@StringRes message: Int) = toast(getString(message))

fun Fragment.toast(@StringRes message: Int) = context?.toast(getString(message))
fun Fragment.toast(message: CharSequence) = context?.toast(message)