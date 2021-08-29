package com.baloot.app.ui.homePage.main.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.core.dto.NetworkState
import com.core.repository.HomeRepository
import com.core.repository.LocalRepository


class MainViewModelImpl(
    application: Application,
    private var localRepository: LocalRepository,
    private var homeRepository: HomeRepository,
) : MainViewModel(
    application
) {


    override fun getNetworkStatus(): LiveData<NetworkState> =
        MediatorLiveData<NetworkState>().apply {

        }


}