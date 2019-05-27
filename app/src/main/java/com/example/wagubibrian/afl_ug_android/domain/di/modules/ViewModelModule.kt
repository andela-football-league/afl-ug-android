package com.example.wagubibrian.afl_ug_android.domain.di.modules

import android.arch.lifecycle.ViewModel
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelKey
import com.example.wagubibrian.afl_ug_android.login.LoginActivityViewModel
import com.example.wagubibrian.afl_ug_android.splashscreen.SplashScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SplashScreenViewModel::class)
    abstract fun providesSplashScreenViewModel(splashScreenViewModel: SplashScreenViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginActivityViewModel::class)
    abstract fun providesLoginActivityViewModel(loginActivityViewModel: LoginActivityViewModel): ViewModel
}
