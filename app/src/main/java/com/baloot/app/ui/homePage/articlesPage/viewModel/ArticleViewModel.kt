package com.baloot.app.ui.homePage.articlesPage.viewModel

import android.app.Application
import androidx.lifecycle.LiveData
import com.core.base.BaseViewModel
import com.core.dto.login.LoginResultDto


abstract class ArticleViewModel(application: Application) : BaseViewModel(application) {

    abstract fun getSecondResponse(): LiveData<LoginResultDto<String>>

    abstract fun requestSecond(promoterId: String)
}