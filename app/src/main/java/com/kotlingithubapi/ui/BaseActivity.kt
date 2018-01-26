package com.kotlingithubapi.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity

/**
 * Created by Valentyn on 16.07.2017.
 */
@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity() {

    val ARG_TITLE : String = "arg_title"

    private var mTitle : String? = null

    var mBackstack: FragmentBackStack? = null
    private var mRetainer: FragmentBackStack.Retainer? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initRetainBackStack()
        mTitle = savedInstanceState?.getString(ARG_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRetainBackStack()
        mTitle = savedInstanceState?.getString(ARG_TITLE)
    }

    override fun onResume() {
        super.onResume()
        setToolbarTitle(mTitle ?: "")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString(ARG_TITLE, getToolbarTitle().toString())
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putString(ARG_TITLE, getToolbarTitle().toString())
    }

    private fun initRetainBackStack() {
        mRetainer = supportFragmentManager.findFragmentByTag(FragmentBackStack.Retainer::class.java.simpleName) as FragmentBackStack.Retainer?

        // create the retain fragment and back stack the first time
        if (mRetainer == null) {
            initFragmentBackStack()
            // add the retain fragment
            mRetainer = FragmentBackStack.Retainer.newInstance()
            supportFragmentManager.beginTransaction().add(mRetainer, FragmentBackStack.Retainer::class.java.simpleName).commit()

            mRetainer?.retain(mBackstack)
        } else {
            mBackstack = mRetainer?.restore(supportFragmentManager)
            if (mBackstack == null) {
                initFragmentBackStack()
            }
        }
    }

    abstract fun setToolbarTitle(title: String)
    abstract fun setToolbarTitle(@StringRes resId: Int)
    abstract fun getToolbarTitle() : CharSequence?
    abstract fun showProgress(show: Boolean)
    abstract fun initFragmentBackStack(): FragmentBackStack?

}