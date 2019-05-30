package com.example.wagubibrian.afl_ug_android.match

import android.app.Application
import android.arch.lifecycle.ViewModel;
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations.Repository
import com.example.wagubibrian.afl_ug_android.domain.data.prefs.PreferencesHelper
import javax.inject.Inject

class MatchViewModel @Inject constructor(app: Application,var repository: Repository) : ViewModel() {

    enum class TimerState {
        Stopped, Paused, Running
    }

    private var app = app

    var timerLengthSeconds = 0L

    var timerState = TimerState.Stopped

    var secondsRemaining = 0L

    fun startTimerCount() {
        startTimer()
        timerState = TimerState.Running
    }

    fun pauseTimerCount() {
        timerState = TimerState.Paused
    }

    fun endTimerCount() {
        onTimerFinish()
    }

    fun onTimerFinish() {
        timerState = TimerState.Stopped
        setNewTimerLength()
        secondsRemaining = timerLengthSeconds
        PreferencesHelper.setSecondsRemaining(timerLengthSeconds, app)
        PreferencesHelper.setDefaultTeamValues(app)
    }

    private fun startTimer() {
        timerState = TimerState.Running
    }

    fun onPauseInvoke() {
//        if (timerState == TimerState.Running) {
//        } else if (timerState == TimerState.Paused) {
//        }
        PreferencesHelper.setTimerState(timerState, app)
        PreferencesHelper.setPreviousTimerLengthSeconds(timerLengthSeconds, app)
        PreferencesHelper.setSecondsRemaining(secondsRemaining, app)

    }

    fun getTimerStatePref(): TimerState {
        if (PreferencesHelper.getTimerState(app) != timerState) timerState = PreferencesHelper.getTimerState(app)
        return PreferencesHelper.getTimerState(app)
    }

    fun setNewTimerLength() {
        val lengthInMinutes = PreferencesHelper.getTimerLength(app)
        timerLengthSeconds =  (lengthInMinutes * 60L)
    }

    fun getNewTimerLength(): Long {
        val lengthInMinutes = PreferencesHelper.getTimerLength(app)
        return (lengthInMinutes * 60L)
    }

    fun setMatchStatus(status: Int) {
       PreferencesHelper.setMatchStatus(status, app)
    }

    fun getMatchStatus(): Int {
        val status = PreferencesHelper.getMatchStatus(app)
        return status
    }

    fun setMatchId(id: Int) {
        PreferencesHelper.setMatchId(id, app)
    }

    fun getMatchId(): Int {
        val status = PreferencesHelper.getMatchId(app)
        return status
    }

    fun setHomeTeam(team: String) {
        PreferencesHelper.setHomeTeam(team, app)
    }

    fun getHomeTeam(): String {
        val team = PreferencesHelper.getHomeTeam(app)
        return team
    }

    fun setAwayTeam(team: String) {
        PreferencesHelper.setAwayTeam(team, app)
    }

    fun getAwayTeam(): String {
        val team = PreferencesHelper.getAwayTeam(app)
        return team
    }

    fun setSecondsRemaining() {
        secondsRemaining = if (timerState  == TimerState.Running || timerState  == TimerState.Paused) {
            PreferencesHelper.getSecondsRemaining(app)
        }
        else {
            timerLengthSeconds
        }
    }

    fun setPreviousTimerLength(): Long {
        return PreferencesHelper.getPreviousTimerLengthSeconds(app)
    }

    fun getMatch(matchId: Int) = repository.getMatch(matchId)

    fun updateMatch(match: Match) = repository.updateMatch(match)

}
