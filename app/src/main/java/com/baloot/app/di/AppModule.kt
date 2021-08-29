package com.baloot.app.di

import dagger.Module

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class AppModule