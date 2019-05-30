package com.example.wagubibrian.afl_ug_android.domain.data.repository.abstractions

import com.example.wagubibrian.afl_ug_android.domain.data.local.Activity
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.data.local.Players
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface IRepository{
    fun addMatches(matches: List<Match>)

    fun deleteMatches(match: Match)

    fun updateMatch(match: Match): Completable

    fun getMatch(matchId: Int): Single<Match>

    fun getMatches(): Flowable<List<Match>>

    fun getPlayers(team: String): Flowable<List<Players>>

    fun insertActivity(activity: Activity): Completable

    fun getActivities(matchId: Int): Flowable<List<Activity>>
}
