package com.core.repository

import androidx.lifecycle.MutableLiveData
import com.core.api.LoginApi
import com.core.base.BaseRepository
import com.core.dto.login.LoginResultDto

abstract class LoginRepository : BaseRepository() {
    abstract fun appList(PromoterID:String): Response<LoginResultDto<String>>
    abstract fun getFakeAppList(): Response<LoginResultDto<String>>
    abstract fun setUserHaveFakeList(userId:String,count : Int): Response<LoginResultDto<String>>
    abstract fun activation(username : String ,pass: String,national:String): Response<LoginResultDto<String>>
}

class LoginRepositoryImpl(private val loginApi: LoginApi) : LoginRepository() {

    override fun appList(PromoterID: String): Response<LoginResultDto<String>> {
        val TAG = "${this::class.java.simpleName}_appList"
        val data = MutableLiveData<LoginResultDto<String>>()
        showProgressAction(TAG)
        addExecutorThreads(loginApi.appList(PromoterID), onSuccess = {
            hideProgressAction(TAG)
            data.postValue(it)
        }, onError = {
            hideProgressAction(TAG)
            handleError(TAG, it)
        })
        return Response(data, networkStatus)
    }

    override fun getFakeAppList(): Response<LoginResultDto<String>> {
        val TAG = "${this::class.java.simpleName}_getFakeAppList"
        val data = MutableLiveData<LoginResultDto<String>>()
        showProgressAction(TAG)
        addExecutorThreads(loginApi.getFakeAppList(), onSuccess = {
            hideProgressAction(TAG)
            data.postValue(it)
        }, onError = {
            hideProgressAction(TAG)
            handleError(TAG, it)
        })
        return Response(data, networkStatus)
    }

    override fun setUserHaveFakeList(userId: String, count: Int): Response<LoginResultDto<String>> {
        val TAG = "${this::class.java.simpleName}_setUserHaveFakeList"
        val data = MutableLiveData<LoginResultDto<String>>()
        showProgressAction(TAG)
        addExecutorThreads(loginApi.setUserHaveFla(userId,count.toString()), onSuccess = {
            hideProgressAction(TAG)
            data.postValue(it)
        }, onError = {
            hideProgressAction(TAG)
            handleError(TAG, it)
        })
        return Response(data, networkStatus)
    }

    override fun activation(username : String ,pass: String,national:String): Response<LoginResultDto<String>> {
        val TAG = "${this::class.java.simpleName}_activation"
        val data = MutableLiveData<LoginResultDto<String>>()
        showProgressAction(TAG)
        addExecutorThreads(loginApi.activation(username,pass,national), onSuccess = {
            hideProgressAction(TAG)
            data.postValue(it)
        }, onError = {
            hideProgressAction(TAG)
            handleError(TAG, it)
        })
        return Response(data, networkStatus)
    }
}