package com.baloot.app.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.baloot.app.R
import com.baloot.app.databinding.FragmentSplashBinding
import com.baloot.app.di.DaggerAppComponent
import com.baloot.app.ui.splash.viewModel.SplashViewModel
import com.baloot.app.ui.splash.viewModel.SplashViewModelImpl
import com.core.base.ParentFragment
import com.core.repository.HomeRepository
import com.core.repository.LocalRepository
import javax.inject.Inject


class SplashFragment : ParentFragment<SplashViewModel, FragmentSplashBinding>() {

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var homeRepository: HomeRepository

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return SplashViewModelImpl(
                    application = requireActivity().application,
                    localRepository = localRepository,
                    homeRepository = homeRepository
                ) as T
            }
        }
    }

    override fun getResourceLayoutId(): Int = R.layout.fragment_splash

    override fun inject() {
        DaggerAppComponent.builder()
            .app(requireActivity().application)
            .build()
            .inject(this)
    }


}