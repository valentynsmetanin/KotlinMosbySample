package com.kotlingithubapi.ui.contributors

import android.view.View
import com.kotlingithubapi.R
import com.kotlingithubapi.model.Contributor
import com.kotlingithubapi.network.Api
import com.kotlingithubapi.ui.BaseRxPresenter
import com.kotlinmoxysample.db.ContributorsDao
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

/**
 * Created by Valentyn on 9/13/17.
 */
class ContributorsPresenter(private val dao: ContributorsDao,
                            private val api: Api) : BaseRxPresenter<ContributorsView>() {

    fun loadContributors() {
        view?.showProgress(true)
        val d = api.repoContributors("square", "retrofit")
                .doOnNext { dao.put(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy (
                        onNext = {
                            view?.showProgress(false)
                            view?.showContributors(it)
                            Timber.d("Contributors $it")
                        },

                        onError = {
                            view?.showProgress(false)
                            Timber.e(it)

                            val contributors = dao.getContributors()
                            Timber.d("Contributors from db $contributors")
                            if(contributors != null) {
                                view?.showContributors(contributors)
                            } else {
                                view?.toast(R.string.err_something_wrong)
                            }

                        }
                )
        mDisposable?.add(d)
    }

}