package com.example.wagubibrian.afl_ug_android.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.wagubibrian.afl_ug_android.domain.data.prefs.PreferencesHelper
import com.example.wagubibrian.afl_ug_android.match.MatchViewModel

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        // TODO: Show Notification

        PreferencesHelper.setTimerState(MatchViewModel.TimerState.Stopped, context)
        PreferencesHelper.setMatchStatus(1, context)
        PreferencesHelper.setAlarmSetTime(0, context)
        PreferencesHelper.setDefaultTeamValues(context)
    }
}
