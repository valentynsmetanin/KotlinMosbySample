package com.kotlingithubapi.di.contributor

import com.kotlingithubapi.ui.contributor.ContributorActivity
import com.kotlingithubapi.ui.contributor.ContributorPresenter
import dagger.Subcomponent

/**
 * Created by Valentyn on 9/26/17.
 */
@Subcomponent(modules = arrayOf(ContributorModule::class))
@ForContributor
interface ContributorComponent {
    fun inject(fragment: ContributorActivity)

    fun presenterContributor(): ContributorPresenter
}