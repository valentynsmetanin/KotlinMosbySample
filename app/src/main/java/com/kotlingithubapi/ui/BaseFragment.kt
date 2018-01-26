package com.kotlingithubapi.ui

import android.os.Bundle
import android.support.annotation.StringRes
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpFragment
import com.kotlingithubapi.utils.toast

/**
 * Created by Valentyn on 9/12/17.
 */
abstract class BaseFragment<V: BaseMvpView, P: MvpBasePresenter<V>> : MvpFragment<V, P>(), BaseMvpView {

    protected lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity
    }

    override fun showProgress(show: Boolean) = mActivity.showProgress(show)
    override fun toast(@StringRes message: Int) = mActivity.toast(message)
    override fun toast(message: String) = mActivity.toast(message)
}