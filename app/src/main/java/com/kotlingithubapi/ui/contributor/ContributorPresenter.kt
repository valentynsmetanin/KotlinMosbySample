package com.kotlingithubapi.ui.contributor

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
 * Created by Valentyn on 9/25/17.
 */
class ContributorPresenter(private val dao: ContributorsDao, private val api: Api) : BaseRxPresenter<ContributorView>() {

    fun loadContributor(contributor : Contributor?) {
        if(contributor == null) {}

        view?.showContributor(contributor)

        if(contributor?.login.isNullOrEmpty()) {
            showFromDb(contributor?.id)
            return
        }

        val d = api.getContributor(contributor?.login)
                .doOnNext { dao.put(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            view?.showProgress(false)
                            view.showContributor(it)
                            Timber.d("Contributor $it")
                        },

                        onError = {
                            view?.showProgress(false)
                            showFromDb(contributor?.id)
                        }
                )
        view?.showProgress(true)
        mDisposable?.add(d)
    }

    private fun showFromDb(id : Long?) {
        if(id == null)  {
            // TODO show 'try again view'
            view?.toast(R.string.err_something_wrong)
            return
        }

        val contributors = dao.getById<Contributor>(id)
        Timber.d("Contributors from db $contributors")
        if(contributors != null) {
            view.showContributor(contributors)
        } else {
            view?.toast(R.string.err_something_wrong)
        }
    }

}