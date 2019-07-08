package com.example.wagubibrian.afl_ug_android

import android.app.Application
import com.example.wagubibrian.afl_ug_android.domain.di.component.AppComponent
import com.example.wagubibrian.afl_ug_android.domain.di.component.DaggerAppComponent
import com.example.wagubibrian.afl_ug_android.domain.di.modules.AppModule
import com.example.wagubibrian.afl_ug_android.domain.di.modules.GoogleSignInModule

class MyApplication: Application() {

    companion object {
         lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        initDi()
    }

    private fun initDi() {
        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .googleSignInModule(GoogleSignInModule())
            .build()
    }
}
