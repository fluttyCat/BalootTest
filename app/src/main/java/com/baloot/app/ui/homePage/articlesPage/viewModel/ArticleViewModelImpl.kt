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



    override fun getNetworkStatus(): LiveData<NetworkState> =
        MediatorLiveData<NetworkState>().apply {

        }


}