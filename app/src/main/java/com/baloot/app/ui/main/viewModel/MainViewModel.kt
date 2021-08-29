package com.baloot.app.ui.main.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import com.core.base.BaseViewModel
import com.core.dto.login.LoginResultDto
import com.core.dto.login.UserInfoDto

/**
 * Created by aMiir on 9/2/20
 * Drunk, Fix Later
 */

abstract class MainViewModel (application: Application) : BaseViewModel(application) {

    abstract fun getUserInfo(): LiveData<UserInfoDto>

    abstract fun getUserAppListResponse(): LiveData<LoginResultDto<String>>
    abstract fun getSecondResponse(): LiveData<LoginResultDto<String>>

    abstract fun requestAppList(promoterId: String)
    abstract fun requestSecond(promoterId: String)

}