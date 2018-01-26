package com.kotlingithubapi.di.contributors

import com.kotlingithubapi.network.Api
import com.kotlingithubapi.ui.contributors.ContributorsPresenter
import com.kotlinmoxysample.db.ContributorsDao
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import org.jetbrains.annotations.NotNull

/**
 * Created by Valentyn on 9/26/17.
 */
@Module
class ContributorsModule {

    @Provides
    @ForContributors
    @NotNull
    fun providesContributorsPresenter(boxStore: BoxStore, api: Api): ContributorsPresenter =
            ContributorsPresenter(ContributorsDao(boxStore), api)

}