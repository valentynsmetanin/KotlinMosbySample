package com.kotlingithubapi.network

import com.kotlingithubapi.model.Contributor
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by User on 16.07.2017.
 */
interface Api {

    @GET("/repos/{owner}/{repo}/contributors")
    fun repoContributors(@Path("owner") owner: String, @Path("repo") repo: String): Observable<List<Contributor>>

    @GET("/users/{login}")
    fun getContributor(@Path("login") login: String?): Observable<Contributor>

}