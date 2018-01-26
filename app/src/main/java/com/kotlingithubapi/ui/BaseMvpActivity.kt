package com.kotlingithubapi.ui

import com.hannesdorfmann.mosby3.mvp.MvpActivity
import com.hannesdorfmann.mosby3.mvp.MvpPresenter

/**
 * Created by Valentyn on 9/25/17.
 */
abstract class BaseMvpActivity<V: BaseMvpView, P: MvpPresenter<V>> : MvpActivity<V, P>()