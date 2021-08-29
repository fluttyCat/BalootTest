package com.baloot.app.di

import android.app.Application
import com.baloot.app.ui.homePage.articlesPage.ArticlesFragment
import com.baloot.app.ui.homePage.main.MainActivity
import com.baloot.app.ui.splashPage.splashActivity.SplashActivity
import com.baloot.app.ui.splashPage.splashFragment.SplashFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class]
)
interface AppComponent {

    fun inject(app: Application)

    //Activity
    fun inject(app: MainActivity)
    fun inject(app: SplashActivity)

    //Fragment
    fun inject(app: SplashFragment)
    fun inject(app: ArticlesFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): Builder

        fun build(): AppComponent
    }


}
