package com.kotlingithubapi.ui.contributor

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.kotlingithubapi.KotlinMosbyApplication
import com.kotlingithubapi.R
import com.kotlingithubapi.di.contributor.ContributorModule
import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.ui.BaseMvpActivity
import com.kotlingithubapi.utils.toast
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_contributor.*
import kotlinx.android.synthetic.main.contributor_content.*
import javax.inject.Inject

/**
 * Created by Valentyn on 9/25/17.
 */
class ContributorActivity : BaseMvpActivity<ContributorView, ContributorPresenter>(), ContributorView {

    @Inject
    lateinit var mPresenter: ContributorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        KotlinMosbyApplication.appComponent.plus(ContributorModule()).inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contributor)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val contributor = intent.extras?.get(ARG_CONTRIBUTOR) as Contributor?
        setImageTransition(intent.extras?.getString(ARG_TRANSITION_NAME))

        presenter.loadContributor(contributor)
    }

    override fun showContributor(contributor: Contributor?) {
        if(contributor?.login != null) {
            supportActionBar?.title = contributor.login
        }
        loadAvatar(contributor?.avatarUrl)

        if(contributor?.name.isNullOrEmpty()) {
            ll_name.visibility = View.GONE
        } else {
            tv_name.text = contributor?.name
            ll_name.visibility = View.VISIBLE
        }

        if(contributor?.email.isNullOrEmpty()) {
            ll_email.visibility = View.GONE
        } else {
            tvEmail.text = contributor?.email
            ll_email.visibility = View.VISIBLE
        }

        if(contributor?.company.isNullOrEmpty()) {
            ll_company.visibility = View.GONE
        } else {
            tv_company.text = contributor?.company
            ll_company.visibility = View.VISIBLE
        }

        if(contributor?.location.isNullOrEmpty()) {
            ll_location.visibility = View.GONE
        } else {
            tv_location.text = contributor?.location
            ll_location.visibility = View.VISIBLE
        }
    }

    private fun loadAvatar(path: String?) {
        if(path.isNullOrEmpty()) return

        Picasso.with(this).load(path).placeholder(R.drawable.ic_github_placeholder).into(iv_toolbar,
                object : Callback {
                    override fun onSuccess() {
                        supportStartPostponedEnterTransition()
                    }

                    override fun onError() {
                        supportStartPostponedEnterTransition()
                    }

                })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                supportFinishAfterTransition()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun setImageTransition(transitionName: String?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (!transitionName.isNullOrEmpty()) {
                iv_toolbar.transitionName = transitionName
            }
        }
    }

    override fun showProgress(show: Boolean) {
        // TODO not implemented
    }

    override fun toast(message: Int) {
        baseContext.toast(message)
    }

    override fun toast(message: String) {
        baseContext.toast(message)
    }

    override fun createPresenter(): ContributorPresenter = mPresenter

    companion object {
        val ARG_CONTRIBUTOR = "arg_contributor"
        val ARG_TRANSITION_NAME = "arg_transitions_name"

        fun newIntent(activity: Activity, contributor: Contributor, transitionName : String? = null) : Intent {
            val intent = Intent(activity, ContributorActivity::class.java)
            val bundle = Bundle()
            bundle.putParcelable(ARG_CONTRIBUTOR, contributor)
            bundle.putString(ARG_TRANSITION_NAME, transitionName)
            intent.putExtras(bundle)
            return intent
        }
    }

}