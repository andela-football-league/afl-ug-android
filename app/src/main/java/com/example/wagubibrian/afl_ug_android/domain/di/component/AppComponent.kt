package com.example.wagubibrian.afl_ug_android.domain.di.component

import com.example.wagubibrian.afl_ug_android.MyApplication
import com.example.wagubibrian.afl_ug_android.domain.di.modules.AppModule
import com.example.wagubibrian.afl_ug_android.domain.di.modules.GoogleSignInModule
import com.example.wagubibrian.afl_ug_android.domain.di.modules.ViewModelModule
import com.example.wagubibrian.afl_ug_android.home.HomeFragment
import com.example.wagubibrian.afl_ug_android.login.LoginActivity
import com.example.wagubibrian.afl_ug_android.match.MatchFragment
import com.example.wagubibrian.afl_ug_android.players.PlayersFragment
import com.example.wagubibrian.afl_ug_android.splashscreen.SplashScreenActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class, GoogleSignInModule::class])
interface AppComponent {
    fun inject(myApplication: MyApplication)

    fun inject(splashScreenActivity: SplashScreenActivity)

    fun inject(loginActivity: LoginActivity)

    fun inject(matchFragment: MatchFragment)

    fun inject(homeFragment: HomeFragment)

    fun inject(playersFragment: PlayersFragment)
}
