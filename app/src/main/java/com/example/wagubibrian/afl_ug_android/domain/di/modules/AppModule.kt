package com.example.wagubibrian.afl_ug_android.domain.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (val app: Application) {

    @Provides
    @Singleton
    fun provideApplication() = app

}