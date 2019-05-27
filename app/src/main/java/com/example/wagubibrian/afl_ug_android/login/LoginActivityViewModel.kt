package com.example.wagubibrian.afl_ug_android.login

<<<<<<< HEAD
import android.app.Application
import android.content.Intent
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.domain.views.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(var app: Application, var auth: FirebaseAuth, var googleSignInClient: GoogleSignInClient): BaseViewModel() {

    fun getSignedAccount(data: Intent) = GoogleSignIn.getSignedInAccountFromIntent(data)!!

    private fun getCredentials(idToken: String?): AuthCredential = GoogleAuthProvider.getCredential(idToken, null)

    fun getSignInIntent() = googleSignInClient.signInIntent


    private fun checkForAndelaDomain(account: GoogleSignInAccount): Boolean {
        var email = account.email
        var list = email?.split("@")
        var domain = list?.get(1)
        return domain.equals("andela.com")
    }

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount, func: (Boolean) -> Unit) {
        val credential = getCredentials(account.idToken)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    when {
                        !checkForAndelaDomain(account) -> {
                            Toast.makeText(app, "Please use Andela Email", Toast.LENGTH_SHORT).show()
                            auth.signOut()
                            googleSignInClient.signOut()
                            func(false)
                        }
                        else -> {
                            Toast.makeText(app, "Authentication Success.", Toast.LENGTH_SHORT).show()
                            func(true)
                        }
                    }
                } else {
                    Toast.makeText(app, "Authentication Failed.", Toast.LENGTH_LONG).show()
                }
            }
    }
=======
import android.content.Intent
import com.example.wagubibrian.afl_ug_android.domain.views.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class LoginActivityViewModel @Inject constructor(): BaseViewModel() {

    private var RCSIGNIN = 9001

    fun checkForAndelaDomain(account: GoogleSignInAccount): Boolean {
        var email = account.email
        var list = email?.split("@")
        var domain = list?.get(0)
        return domain.equals("andela.com")
    }

    fun getSignedAccount(data: Intent?) = GoogleSignIn.getSignedInAccountFromIntent(data)
    fun getCredentials(idToken: String?): AuthCredential = GoogleAuthProvider.getCredential(idToken, null)
>>>>>>> Setup Login Functionality.
}
