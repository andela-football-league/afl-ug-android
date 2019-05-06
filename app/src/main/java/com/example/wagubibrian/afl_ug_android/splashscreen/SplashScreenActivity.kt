package com.example.wagubibrian.afl_ug_android.splashscreen

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.MyApplication
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.databinding.ActivitySplashScreenBinding
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelFactory
import com.example.wagubibrian.afl_ug_android.domain.views.BaseActivity
import com.example.wagubibrian.afl_ug_android.login.LoginActivity
import javax.inject.Inject

class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding>() {

    override fun layoutId() = R.layout.activity_splash_screen

    private lateinit var viewModel: SplashScreenViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        MyApplication.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashScreenViewModel::class.java)
        Handler().postDelayed({
        if (viewModel.isUserLoggedIn()) {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Please Login", Toast.LENGTH_LONG).show()
        }
        }, 3000)
    }
}
