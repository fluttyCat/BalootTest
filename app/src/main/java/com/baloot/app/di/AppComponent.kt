package com.baloot.app.di

import android.app.Application
import com.baloot.app.ui.main.MainActivity
import com.baloot.app.ui.profile.ProfileFragment
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

    //Fragment
    fun inject(app: ProfileFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): Builder

        fun build(): AppComponent
    }


}
