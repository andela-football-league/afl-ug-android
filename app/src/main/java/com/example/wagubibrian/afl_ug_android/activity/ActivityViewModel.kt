package com.example.wagubibrian.afl_ug_android.activity

import android.app.Application
import android.arch.lifecycle.ViewModel
import com.example.wagubibrian.afl_ug_android.domain.data.prefs.PreferencesHelper
import com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations.Repository
import javax.inject.Inject

class ActivityViewModel @Inject constructor(var app: Application, var repository: Repository): ViewModel() {

    private fun getMatchId() = PreferencesHelper.getMatchId(app)

    fun getActivities() = repository.getActivities(getMatchId())

    fun getMatch() = repository.getMatch(getMatchId())
}