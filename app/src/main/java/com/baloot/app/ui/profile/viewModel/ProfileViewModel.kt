package com.baloot.app.ui.profile.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import com.core.base.BaseViewModel
import com.core.dto.login.LoginResultDto

/**
 * Created by aMiir on 9/5/20
 * Drunk, Fix Later
 */

abstract class ProfileViewModel  (application: Application) : BaseViewModel(application) {

    abstract fun getSecondResponse(): LiveData<LoginResultDto<String>>

    abstract fun requestSecond(promoterId: String)
}