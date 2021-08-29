package com.baloot.app.ui.homePage.main.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.core.dto.NetworkState
import com.core.repository.LocalRepository
import com.core.repository.LoginRepository


class MainViewModelImpl(
    application: Application,
    private var localRepository: LocalRepository,
    private var loginRepository: LoginRepository,
) : MainViewModel(
    application
) {


    override fun getNetworkStatus(): LiveData<NetworkState> =
        MediatorLiveData<NetworkState>().apply {

        }


}