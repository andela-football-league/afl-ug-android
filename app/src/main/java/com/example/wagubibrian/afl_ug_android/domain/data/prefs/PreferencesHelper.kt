package com.example.wagubibrian.afl_ug_android.domain.data.prefs

import android.content.Context
import android.preference.PreferenceManager
import com.example.wagubibrian.afl_ug_android.match.MatchViewModel

class PreferencesHelper {
    companion object {
        fun getTimerLength(context: Context): Int {
            return 1
        }

        private const val PREVIOUS_TIMER_LENGTH_SECONDS_ID  = "com.example.wagubibrian.afl_ug_android.previous_timer_length"

        fun getPreviousTimerLengthSeconds(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, 0)
        }

        fun setPreviousTimerLengthSeconds(secs: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOUS_TIMER_LENGTH_SECONDS_ID, secs)
            editor.apply()
        }

        private const val TIMER_LENGTH_STATE_ID  = "com.example.wagubibrian.afl_ug_android.state"

        fun getTimerState(context: Context) : MatchViewModel.TimerState {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_LENGTH_STATE_ID, 0)
            return MatchViewModel.TimerState.values()[ordinal]
        }

        fun setTimerState(state: MatchViewModel.TimerState, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_LENGTH_STATE_ID, ordinal)
            editor.apply()
        }

        private const val SECONDS_REMAINING_ID  = "com.example.wagubibrian.afl_ug_android.seconds_remaining"

        fun getSecondsRemaining(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID, 0)
        }

        fun setSecondsRemaining(secs: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID, secs)
            editor.apply()
        }

        private const val ALARM_SET_TIME_ID  = "com.example.wagubibrian.afl_ug_android.background_time"

        fun getAlarmSetTime(context: Context) : Long {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(ALARM_SET_TIME_ID, 0)
        }

        fun setAlarmSetTime(secs: Long, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(ALARM_SET_TIME_ID, secs)
            editor.apply()
        }

        private const val MATCH_STATUS_ID  = "com.example.wagubibrian.afl_ug_android.match_status"

        fun getMatchStatus(context: Context) : Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(MATCH_STATUS_ID, 0)
        }

        fun setMatchStatus(status: Int, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(MATCH_STATUS_ID, status)
            editor.apply()
        }

        private const val MATCH_ID  = "com.example.wagubibrian.afl_ug_android.match_id"

        fun getMatchId(context: Context) : Int {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getInt(MATCH_ID, 0)
        }

        fun setMatchId(id: Int, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putInt(MATCH_ID, id)
            editor.apply()
        }

        private const val HOME_TEAM_ID = "com.example.wagubibrian.afl_ug_android.home_team"

        fun getHomeTeam(context: Context) : String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(HOME_TEAM_ID, "N/A")
        }

        fun setHomeTeam(team: String, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(HOME_TEAM_ID, team)
            editor.apply()
        }

        private const val AWAY_TEAM_ID  = "com.example.wagubibrian.afl_ug_android.away_team"

        fun getAwayTeam(context: Context) : String {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getString(AWAY_TEAM_ID, "N/A")
        }

        fun setAwayTeam(team: String, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(AWAY_TEAM_ID, team)
            editor.apply()
        }

        fun setDefaultTeamValues(context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(AWAY_TEAM_ID, "N/A")
            editor.putString(HOME_TEAM_ID, "N/A")
            editor.apply()
        }
    }
}
