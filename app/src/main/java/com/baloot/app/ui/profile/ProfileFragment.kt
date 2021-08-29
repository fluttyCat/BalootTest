package com.baloot.app.ui.profile

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.core.base.ParentFragment
import com.core.repository.LocalRepository
import com.core.repository.LoginRepository
import com.baloot.app.R
import com.baloot.app.databinding.FragmentProfileBinding
import com.baloot.app.di.DaggerAppComponent
import com.baloot.app.ui.profile.viewModel.ProfileViewModel
import com.baloot.app.ui.profile.viewModel.ProfileViewModelImpl
import javax.inject.Inject

/**
 * Created by aMiir on 9/26/20
 * Drunk, Fix Later
 */
class ProfileFragment  : ParentFragment<ProfileViewModel, FragmentProfileBinding>(){

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var loginRepository: LoginRepository

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.requestSecond("a")

    }

    override fun getViewModelClass(): Class<ProfileViewModel> = ProfileViewModel::class.java

    override fun getFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return ProfileViewModelImpl(
                    application = requireActivity().application,
                    localRepository = localRepository,
                    loginRepository = loginRepository
                ) as T
            }
        }
    }

    override fun getResourceLayoutId(): Int =  R.layout.fragment_profile

    override fun inject() {
        DaggerAppComponent.builder()
            .app(requireActivity().application)
            .build()
            .inject(this)
    }


}