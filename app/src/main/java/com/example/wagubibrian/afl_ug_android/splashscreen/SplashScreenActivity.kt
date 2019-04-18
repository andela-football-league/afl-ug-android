package com.example.wagubibrian.afl_ug_android.splashscreen

import android.os.Bundle
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.databinding.ActivitySplashScreenBinding
import com.example.wagubibrian.afl_ug_android.domain.views.BaseActivity

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {
    override fun layoutId() = R.layout.activity_splash_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }
}
