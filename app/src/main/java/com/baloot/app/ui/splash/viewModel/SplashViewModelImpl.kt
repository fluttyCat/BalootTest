package com.baloot.app.ui.splash.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.core.dto.NetworkState
import com.core.repository.LocalRepository
import com.core.repository.LoginRepository


class SplashViewModelImpl(
    application: Application,
    private var localRepository: LocalRepository,
    private var loginRepository: LoginRepository,
) : SplashViewModel(
    application
) {



    override fun getNetworkStatus(): LiveData<NetworkState> =
        MediatorLiveData<NetworkState>().apply {

        }


}