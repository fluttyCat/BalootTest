package com.baloot.app.ui.homePage.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.baloot.app.R
import com.baloot.app.databinding.ActivityMainBinding
import com.baloot.app.di.DaggerAppComponent
import com.baloot.app.ui.homePage.main.viewModel.MainViewModel
import com.baloot.app.ui.homePage.main.viewModel.MainViewModelImpl
import com.core.base.ParentActivity
import com.core.repository.HomeRepository
import com.core.repository.LocalRepository
import javax.inject.Inject

class MainActivity : ParentActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var localRepository: LocalRepository

    @Inject
    lateinit var homeRepository: HomeRepository

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHost) as NavHostFragment? ?: return

        navController = host.navController

        dataBinding.bottomNavigation.setupWithNavController(navController)

    }


    override fun getFactory(): ViewModelProvider.Factory {
        return object : ViewModelProvider.NewInstanceFactory() {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                @Suppress("UNCHECKED_CAST")
                return MainViewModelImpl(
                    application = application,
                    localRepository = localRepository,
                    homeRepository = homeRepository
                ) as T
            }
        }
    }

    override fun inject() {
        DaggerAppComponent.builder()
            .app(application)
            .build()
            .inject(this)
    }

    override fun getResourceLayoutId(): Int = R.layout.activity_main

    override fun getViewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun showProgress(tag: String) {
        super.showProgress(tag)

    }

    override fun hideProgress(tag: String) {
        super.hideProgress(tag)
    }

    override fun showError(tag: String, error: String) {
        super.showError(tag, error)
        Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
    }

}
