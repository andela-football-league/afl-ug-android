package com.example.wagubibrian.afl_ug_android.splashscreen

import com.example.wagubibrian.afl_ug_android.domain.views.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject


class SplashScreenViewModel @Inject constructor(var auth: FirebaseAuth): BaseViewModel() {

    fun isUserLoggedIn (): Boolean {
        //Logic for checking if the user is logged in
        return auth.currentUser != null
    }
}
