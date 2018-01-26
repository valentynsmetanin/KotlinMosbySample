package com.kotlingithubapi.di

import android.content.Context
import com.kotlingithubapi.KotlinMosbyApplication
import com.kotlingithubapi.model.MyObjectBox
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.network.RestClient
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import org.jetbrains.annotations.NotNull
import javax.inject.Singleton

/**
 * Created by Valentyn on 9/26/17.
 */
@Module
class AppModule(val app: KotlinMosbyApplication) {

    @Provides
    @NotNull
    @Singleton
    fun providesContext() : Context = app

    @Provides
    @NotNull
    @Singleton
    fun providesBoxStore(context: Context): BoxStore = MyObjectBox.builder().androidContext(context).build()

    @Provides
    @NotNull
    @Singleton
    fun providesApiClient(): Api = RestClient().createService(Api::class.java)

}