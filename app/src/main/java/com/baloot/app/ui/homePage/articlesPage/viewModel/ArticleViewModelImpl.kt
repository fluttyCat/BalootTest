package com.baloot.app.ui.homePage.articlesPage.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.core.dto.NetworkState
import com.core.dto.login.LoginResultDto
import com.core.repository.LocalRepository
import com.core.repository.LoginRepository


class ArticleViewModelImpl(
    application: Application,
    private var localRepository: LocalRepository,
    private var loginRepository: LoginRepository,
) : ArticleViewModel(
    application
) {

    private val second: MutableLiveData<String> = MutableLiveData()

    private val appListRepo = Transformations.map(second) {
        loginRepository.appList(it)
    }

    override fun getNetworkStatus(): LiveData<NetworkState> =
        MediatorLiveData<NetworkState>().apply {
            this.addSource(Transformations.switchMap(appListRepo) { it.networkState }) {
                this.postValue(it)
            }
        }

    override fun getSecondResponse(): LiveData<LoginResultDto<String>> =
        Transformations.switchMap(appListRepo) {
            it.onSuccess
        }

    override fun requestSecond(promoterId: String) {
        second.postValue(promoterId)
    }

}