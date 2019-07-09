package com.example.wagubibrian.afl_ug_android.domain.di.modules

import android.arch.lifecycle.ViewModel
import com.example.wagubibrian.afl_ug_android.activity.ActivityViewModel
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelKey
import com.example.wagubibrian.afl_ug_android.home.HomeViewModel
import com.example.wagubibrian.afl_ug_android.login.LoginActivityViewModel
import com.example.wagubibrian.afl_ug_android.match.MatchViewModel
import com.example.wagubibrian.afl_ug_android.players.PlayerViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(MatchViewModel::class)
    abstract fun providesMatchViewModel(matchViewModel: MatchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun providesHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    abstract fun providesPlayersViewModel(playerViewModel: PlayerViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ActivityViewModel::class)
    abstract fun providesActivityViewModel(activityViewModel: ActivityViewModel): ViewModel
}
