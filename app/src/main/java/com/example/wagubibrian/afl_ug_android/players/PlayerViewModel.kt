package com.example.wagubibrian.afl_ug_android.players

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.example.wagubibrian.afl_ug_android.domain.data.local.Activity
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.data.prefs.PreferencesHelper
import com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations.Repository
import io.reactivex.Completable

import javax.inject.Inject

class PlayerViewModel @Inject constructor(var app: Application, var repository: Repository): ViewModel() {

    fun getPlayers(team: String) = repository.getPlayers(team)

    fun addActivity(playerName: String, activity: String, homeStatus: Boolean) : Completable {
        var timeInMinutes = "${getSecondsRemaining()/60}"
        return repository.insertActivity(Activity(0,activity, playerName
            , getMatchId(), homeStatus, timeInMinutes))
    }

    fun getMatchId() = PreferencesHelper.getMatchId(app)

    fun getActivities() = repository.getActivities(getMatchId())

    fun updateMatch(match: Match) = repository.updateMatch(match)

    fun getMatch(matchId: Int) = repository.getMatch(matchId)

    fun getSecondsRemaining() =  PreferencesHelper.getSecondsRemaining(app)

}
