package com.kotlingithubapi.ui

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Valentyn on 9/13/17.
 */
abstract class BaseRxPresenter<V: BaseMvpView> : MvpBasePresenter<V>() {

    protected var mDisposable: CompositeDisposable? = null

    override fun attachView(view: V) {
        super.attachView(view)
        mDisposable = CompositeDisposable()
    }

    override fun detachView(retainInstance: Boolean) {
        super.detachView(retainInstance)
        if (!retainInstance) {
            mDisposable?.clear()
        }
    }

}