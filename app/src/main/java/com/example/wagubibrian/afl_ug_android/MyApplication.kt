package com.example.wagubibrian.afl_ug_android

import android.app.Application
import com.example.wagubibrian.afl_ug_android.domain.di.component.AppComponent
import com.example.wagubibrian.afl_ug_android.domain.di.component.DaggerAppComponent
import com.example.wagubibrian.afl_ug_android.domain.di.modules.AppModule

class MyApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}