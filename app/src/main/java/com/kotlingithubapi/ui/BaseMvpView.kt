package com.kotlingithubapi.ui

import android.support.annotation.StringRes
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Valentyn on 9/25/17.
 */
interface BaseMvpView: MvpView {
    fun showProgress(show: Boolean)
    fun toast(@StringRes message: Int)
    fun toast(message: String)
}