package com.baloot.app.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.core.dto.NetworkState
import com.core.dto.login.LoginResultDto
import com.core.repository.LocalRepository
import com.core.repository.LoginRepository

/**
 * Created by aMiir on 9/2/20
 * Drunk, Fix Later
 */

class MainViewModelImpl (
    application: Application,
private var localRepository: LocalRepository,
private var loginRepository: LoginRepository,
) : MainViewModel(
application
) {

    private val appList: MutableLiveData<String> = MutableLiveData()
    private val second: MutableLiveData<String> = MutableLiveData()

    private val appListRepo = Transformations.map(appList) {
        loginRepository.appList(it)
    }

    private val secondRepo = Transformations.map(second) {
        loginRepository.appList(it)
    }

    override fun getNetworkStatus(): LiveData<NetworkState> =
        MediatorLiveData<NetworkState>().apply {
            this.addSource(Transformations.switchMap(appListRepo) { it.networkState }) {
                this.postValue(it)
            }
            this.addSource(Transformations.switchMap(secondRepo) { it.networkState }) {
                this.postValue(it)
            }
        }

    override fun getUserAppListResponse() = Transformations.switchMap(appListRepo) {
        it.onSuccess
    }

    override fun getSecondResponse(): LiveData<LoginResultDto<String>>  = Transformations.switchMap(secondRepo) {
        it.onSuccess
    }

    override fun requestAppList(promoterId: String) {
        appList.postValue(promoterId)
    }

    override fun requestSecond(promoterId: String) {
        second.postValue(promoterId)
    }

    override fun getUserInfo() = localRepository.getUserInfo()

}