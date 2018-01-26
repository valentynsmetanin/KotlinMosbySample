package com.kotlingithubapi.ui

import android.os.Bundle
import android.support.annotation.StringRes
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.viewstate.MvpViewStateFragment
import com.hannesdorfmann.mosby3.mvp.viewstate.ViewState
import com.kotlingithubapi.utils.toast

/**
 * Created by Valentyn on 9/14/17.
 */
abstract class BaseViewStateFragment<V: BaseMvpView, P: MvpBasePresenter<V>, VS: ViewState<V>> : MvpViewStateFragment<V, P, VS>(), BaseMvpView {

    protected lateinit var mActivity: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity
    }

    override fun showProgress(show: Boolean) = mActivity.showProgress(show)
    override fun toast(@StringRes message: Int) = mActivity.toast(message)
    override fun toast(message: String) = mActivity.toast(message)


}