package com.kotlinmoxysample.db

import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.model.Contributor_
import io.objectbox.BoxStore
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by Valentyn on 9/21/17.
 */
class ContributorsDao(boxStore: BoxStore) : BaseDao(boxStore) {

    fun getContributors() : List<Contributor>? {
        val contributorBox = boxStore.boxFor(Contributor::class.java)
        val contributors = contributorBox.query().order(Contributor_.contributions).build().find()
        Timber.d("Find contributors $contributors")
        return contributors
    }

    fun getContributorsObservable() : Observable<List<Contributor>>
            = Observable.just(getContributors())
}