package com.kotlingithubapi.ui

import android.os.Bundle
import android.view.View
import com.kotlingithubapi.R
import com.kotlingithubapi.ui.contributors.ContributorsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.view.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override fun setToolbarTitle(title: String) {
       toolbar.toolbar.title = title
    }

    override fun setToolbarTitle(resId: Int) {
        toolbar.toolbar.title = resources.getString(resId)
    }

    override fun showProgress(show: Boolean) =
            if(show) progress.visibility = View.VISIBLE
            else progress.visibility =  View.GONE

    override fun initFragmentBackStack() : FragmentBackStack? {
        if(mBackstack == null) {
            mBackstack = FragmentBackStack(supportFragmentManager, R.id.fragment_container)
            mBackstack?.replace(ContributorsFragment.newInstance())
        }
        return mBackstack
    }

    override fun getToolbarTitle(): CharSequence? = toolbar?.toolbar?.title

}
