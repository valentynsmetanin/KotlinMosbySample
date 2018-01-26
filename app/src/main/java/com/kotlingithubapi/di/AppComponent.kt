package com.kotlingithubapi.di

import android.content.Context
import com.kotlingithubapi.KotlinMosbyApplication
import com.kotlingithubapi.di.contributor.ContributorComponent
import com.kotlingithubapi.di.contributor.ContributorModule
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.di.contributors.ContributorsComponent
import com.kotlingithubapi.di.contributors.ContributorsModule
import dagger.Component
import io.objectbox.BoxStore
import javax.inject.Singleton

/**
 * Created by Valentyn on 9/26/17.
 */
@Component(modules = arrayOf(AppModule::class))
@Singleton
interface AppComponent {
    fun context(): Context
    fun boxStore(): BoxStore
    fun api(): Api

    fun inject(application: KotlinMosbyApplication)

    fun plus(contributorsModule: ContributorsModule): ContributorsComponent
    fun plus(contributorModule: ContributorModule): ContributorComponent

}