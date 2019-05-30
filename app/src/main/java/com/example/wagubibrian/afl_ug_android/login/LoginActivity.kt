package com.example.wagubibrian.afl_ug_android.login

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import com.example.wagubibrian.afl_ug_android.MyApplication
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelFactory
import com.example.wagubibrian.afl_ug_android.main.MainActivity
import com.google.android.gms.common.api.ApiException
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var signInButton: Button
    private var RCSIGNIN = 9001

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: LoginActivityViewModel

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        MyApplication.component.inject(this)

        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginActivityViewModel::class.java)

        signInButton = findViewById(R.id.sign_in_button)

        signInButton.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        progressBar.visibility = View.VISIBLE
        startActivityForResult(viewModel.getSignInIntent(), RCSIGNIN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RCSIGNIN -> {
                val task = viewModel.getSignedAccount(data!!)
                try {
                    val account = task.getResult(ApiException::class.java)
                    viewModel.firebaseAuthWithGoogle(account!!) {
                        if (it)
                            navigateMainActivity()
                        progressBar.visibility = View.GONE
                    }
                } catch (e: ApiException) {
                    Log.w("SIGN_IN_LOG", "GOOGLE SIGN IN FAILED", e)
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun navigateMainActivity() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}

