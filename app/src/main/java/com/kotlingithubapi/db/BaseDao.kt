package com.kotlinmoxysample.db

import io.objectbox.BoxStore
import io.reactivex.Observable
import timber.log.Timber

/**
 * Created by Valentyn on 9/21/17.
 */
open class BaseDao(val boxStore: BoxStore) {

    inline fun <reified T> put(entity: T?) {
        if (entity == null) return
        val box = boxStore.boxFor(T::class.java)
        box?.put(entity)
        Timber.d("Saving entity $entity")
    }

    inline fun <reified T> put(list: List<T>?) {
        if (list == null) return
        val box = boxStore.boxFor(T::class.java)
        box?.put(list)
        Timber.d("Saving list $list")
    }

    inline fun <reified T> getById(id: Long): T? {
        val entity = boxStore.boxFor(T::class.java)?.get(id)
        Timber.d("Getting entity $entity")
        return entity
    }

    inline fun <reified T> getByIdObservable(id: Long): Observable<T> = Observable.just(getById(id))

    inline fun <reified T> getAll(): List<T>? {
        val all = boxStore.boxFor(T::class.java)?.all
        Timber.d("Getting collection $all")
        Observable.just(all)
        return all
    }

    inline fun <reified T> getAllObservable(): Observable<List<T>> = Observable.just(getAll())

    inline fun <reified T> remove(list: List<T>?) {
        if (list == null) return
        val box = boxStore.boxFor(T::class.java)
        box?.remove(list)
        Timber.d("Saving list $list")
    }

    inline fun <reified T> removeAll() {
        boxStore.boxFor(T::class.java).removeAll()
    }

    fun clearDb(): Boolean = boxStore.deleteAllFiles()

}