package com.kotlingithubapi.ui.contributors

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlingithubapi.R
import com.kotlingithubapi.KotlinMosbyApplication
import com.kotlingithubapi.di.contributors.ContributorsModule
import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.ui.BaseViewStateFragment
import com.kotlingithubapi.ui.contributor.ContributorActivity
import kotlinx.android.synthetic.main.fragment_contributors.*
import javax.inject.Inject

/**
 * Created by Valentyn on 9/13/17.
 */
class ContributorsFragment : BaseViewStateFragment<ContributorsView, ContributorsPresenter, ContributorsViewState>(), ContributorsView {

    @Inject
    lateinit var mPresenter: ContributorsPresenter

    private var mAdapter: ContributorsAdapter? = null

    override fun createPresenter(): ContributorsPresenter = mPresenter

    override fun createViewState(): ContributorsViewState = ContributorsViewState()

    override fun onNewViewStateInstance() {
        presenter.loadContributors()
    }

    override fun onResume() {
        super.onResume()
        mActivity.setToolbarTitle(R.string.contributors)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        KotlinMosbyApplication.appComponent.plus(ContributorsModule()).inject(this)

        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_contributors, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        rv_contributors.setHasFixedSize(true)

        rv_contributors.layoutManager = LinearLayoutManager(mActivity)

        val dividerItemDecoration = DividerItemDecoration(rv_contributors.context,
                LinearLayoutManager.VERTICAL)
        rv_contributors.addItemDecoration(dividerItemDecoration)

        mAdapter = ContributorsAdapter(
                mActivity,
                ArrayList(),
                object : ContributorsAdapter.ContributorClickListener {
                    override fun onContributorClick(contributor: Contributor?, viewAvatar: View?) {
                        onContributorClicked(contributor, viewAvatar)
                    }
                }
        )

        rv_contributors.adapter = mAdapter
    }

    override fun showContributors(contributors: List<Contributor>?) {
        val viewState = (viewState as ContributorsViewState)
        viewState.contributors = contributors as ArrayList<Contributor>?
        mAdapter?.items = contributors
        mAdapter?.notifyDataSetChanged()
    }

    private fun onContributorClicked(contributor: Contributor?, avatarView: View?) {
        if (contributor != null) {
            val transitionName = ViewCompat.getTransitionName(avatarView) ?: null

            val intent = ContributorActivity.newIntent(mActivity, contributor, transitionName)

            if (transitionName.isNullOrEmpty() || avatarView == null) {
                startActivity(intent)
            } else {
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as Activity,
                        avatarView, transitionName!!)
                startActivity(intent, options.toBundle())
            }
        }
    }

    override fun showProgress(show: Boolean) {
        mActivity.showProgress(show)
    }

    companion object {
        fun newInstance(): ContributorsFragment = ContributorsFragment()
    }

}