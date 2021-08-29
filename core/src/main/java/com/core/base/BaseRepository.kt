package com.core.base


import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.core.R
import com.core.dto.NetworkState

import com.google.gson.JsonSyntaxException

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.EOFException
import java.io.IOException

abstract class BaseRepository {

    data class Response<T>(val onSuccess : LiveData<T>, val networkState: LiveData<NetworkState>)

    val webRequestDefaultValue : Boolean = false

    private val disposables = CompositeDisposable()

    open fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    open fun disposeDisposables() {
        disposables.clear()
    }

    val networkStatus : MutableLiveData<NetworkState> = MutableLiveData()

    open fun showProgressAction(tag : String? = null) : NetworkState{
        val network = NetworkState.loading(tag)
        networkStatus.postValue(network)
        return network
    }

    open fun hideProgressAction(tag : String? = null) : NetworkState {
        val network = NetworkState.loaded(tag)
        networkStatus.postValue(network)
        return network
    }

    fun <T> addExecutorThreads(observable: Maybe<T>, onSuccess: ((T) -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        addDisposable(observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribeOn(Schedulers.io()).subscribe({ result ->
                    onSuccess?.let {
                        onSuccess(result)
                    }
                }, { throwable ->
                    onError?.let {
                        onError(throwable)
                    }
                }))
    }

    fun <T> addExecutorThreads(observable: Single<T>, onSuccess: ((T) -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        addDisposable(observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({result ->
                    onSuccess?.let {
                        onSuccess(result)
                    }
                }, { throwable ->
                    onError?.let {
                        onError(throwable)
                    }
                }))
    }

    fun <T> addExecutorThreads(observable: Observable<T>, onSuccess: ((T) -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        addDisposable(observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({result ->
                    onSuccess?.let {
                        onSuccess(result)
                    }
                }, { throwable ->
                    onError?.let {
                        onError(throwable)
                    }
                }))
    }

    fun addExecutorThreads(observable: Completable, onSuccess: (() -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        addDisposable(observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io()).subscribe({
                    onSuccess?.let {
                        onSuccess()
                    }
                }, { throwable ->
                    onError?.let {
                        onError(throwable)
                    }
                }))
    }

    fun handleError(tag : String? = null, t: Throwable) : NetworkState {
        val network : NetworkState = when (t) {
            is EOFException -> NetworkState.error(R.string.eofException, tag = tag)
            is IOException -> NetworkState.error(R.string.ioException, tag = tag)
            is SQLiteConstraintException -> NetworkState.error(R.string.ioException, tag = tag, msg = t.message)
            is HttpException -> when {
                t.code() == 401 -> NetworkState.error(R.string.authorization, tag = tag)
                t.code() == 403 -> NetworkState.error(R.string.forbidden, tag = tag)
                else -> NetworkState.error(R.string.runtimeException, tag = tag)
            }
            is JsonSyntaxException -> NetworkState.error(R.string.jsonFormat, tag = tag)
            else -> NetworkState.error(R.string.undefine, tag = tag)
        }
        networkStatus.postValue(network)
        return network
    }
}