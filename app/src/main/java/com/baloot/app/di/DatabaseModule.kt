package com.baloot.app.di

import android.app.Application
import androidx.room.Room
import com.core.dao.UserInfoDao
import com.core.db.PnashrDb
import com.core.repository.LocalRepository
import com.core.repository.LocalRepositoryImpl
import com.core.utils.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): PnashrDb {
        return Room
            .databaseBuilder(app, PnashrDb::class.java, "cache.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideUserInfoDao(db: PnashrDb): UserInfoDao{
        return db.userInfoDao()
    }

    @Provides
    fun provideSecurityHelper(): SecurityHelper {
        return SecurityHelperImpl()
    }

    @Provides
    fun providePreference(app: Application, securityHelper: SecurityHelper): Preference {
        return PreferenceImpl(app, securityHelper)
    }

    @Provides
    fun provideSettingManager(preference: Preference): SettingManager {
        return SettingManagerImpl(preference)
    }

    @Singleton
    @Provides
    fun provideLocalRepository(userInfoDao: UserInfoDao): LocalRepository {
        return LocalRepositoryImpl(userInfoDao)
    }



}
