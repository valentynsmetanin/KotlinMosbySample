package com.kotlingithubapi.ui.contributors

import android.os.Bundle
import com.hannesdorfmann.mosby3.mvp.viewstate.RestorableViewState
import com.kotlingithubapi.model.Contributor

/**
 * Created by Valentyn on 9/14/17.
 */
class ContributorsViewState : RestorableViewState<ContributorsView> {

    private val ARG_CONTRIBUTORS = "arg_contributors"

    var contributors: ArrayList<Contributor>? = null

    override fun apply(view: ContributorsView?, retained: Boolean) {
        view?.showContributors(contributors?.toList())
    }

    override fun restoreInstanceState(bundle: Bundle?): RestorableViewState<ContributorsView> {
        contributors = bundle?.getParcelableArrayList(ARG_CONTRIBUTORS)
        return this
    }

    override fun saveInstanceState(out: Bundle) {
        out.putParcelableArrayList(ARG_CONTRIBUTORS, contributors)
    }

}