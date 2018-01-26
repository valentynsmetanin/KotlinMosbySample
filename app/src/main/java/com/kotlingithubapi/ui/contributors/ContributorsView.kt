package com.kotlingithubapi.ui.contributors

import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.ui.BaseMvpView

/**
 * Created by Valentyn on 9/12/17.
 */
interface ContributorsView : BaseMvpView {

    fun showContributors(contributors: List<Contributor>?)

}