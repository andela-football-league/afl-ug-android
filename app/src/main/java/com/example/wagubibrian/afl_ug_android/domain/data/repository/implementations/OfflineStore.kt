package com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations

import com.example.wagubibrian.afl_ug_android.domain.data.local.Activity
import com.example.wagubibrian.afl_ug_android.domain.data.local.Database
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.data.local.Players
import com.example.wagubibrian.afl_ug_android.domain.data.repository.abstractions.IOfflineRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class OfflineStore(var database: Database): IOfflineRepository {
    override fun getActivities(matchId: Int): Flowable<List<Activity>> = database.activityDao().findActivityByGame(matchId)

    override fun addActivity(activity: Activity): Completable = Completable.fromAction{
            database.activityDao().insertActivity(activity)
            Completable.complete()
        }

        override fun getPlayers(team: String): Flowable<List<Players>> = database.playerDao().findPlayersByTeam(team)

        override fun getMatches(): Flowable<List<Match>> = database.matchDao().getAll()

        override fun addMatches(matches: List<Match>) {
            for(match in matches) {
                database.matchDao().insertAll(match)
            }
        }

        override fun deleteMatches(match: Match) = database.matchDao().delete(match)

        override fun updateMatch(match: Match): Completable = Completable.fromAction{
            database.matchDao().update(match)
        }

        override fun getMatch(matchId: Int): Single<Match>  = database.matchDao().getMatch(matchId)
}
