package com.kotlingithubapi.ui.contributor

import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.ui.BaseMvpView

/**
 * Created by Valentyn on 9/25/17.
 */
interface ContributorView : BaseMvpView {

    fun showContributor(contributor: Contributor?)

}