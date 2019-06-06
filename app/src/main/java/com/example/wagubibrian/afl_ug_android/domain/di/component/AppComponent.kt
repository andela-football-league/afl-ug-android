package com.example.wagubibrian.afl_ug_android.domain.di.component

import com.example.wagubibrian.afl_ug_android.MyApplication
import com.example.wagubibrian.afl_ug_android.domain.di.modules.AppModule
import com.example.wagubibrian.afl_ug_android.domain.di.modules.ViewModelModule
import com.example.wagubibrian.afl_ug_android.splashscreen.SplashScreenActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ViewModelModule::class))
interface AppComponent {
    fun inject(myApplication: MyApplication)

    fun inject(splashScreenActivity: SplashScreenActivity)
}
