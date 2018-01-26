package com.kotlingithubapi.di.contributor

import com.kotlingithubapi.network.Api
import com.kotlingithubapi.ui.contributor.ContributorPresenter
import com.kotlinmoxysample.db.ContributorsDao
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import org.jetbrains.annotations.NotNull

/**
 * Created by Valentyn on 9/26/17.
 */
@Module
class ContributorModule {

    @Provides
    @ForContributor
    @NotNull
    fun providesContributorPresenter(boxStore: BoxStore, api: Api): ContributorPresenter =
            ContributorPresenter(ContributorsDao(boxStore), api)

}