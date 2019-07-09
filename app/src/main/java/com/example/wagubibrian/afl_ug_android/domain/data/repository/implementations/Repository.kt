package com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations

import com.example.wagubibrian.afl_ug_android.domain.data.local.Activity
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.data.local.Players
import com.example.wagubibrian.afl_ug_android.domain.data.repository.abstractions.IOfflineRepository
import com.example.wagubibrian.afl_ug_android.domain.data.repository.abstractions.IRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class Repository(private val offlineRepo: IOfflineRepository) : IRepository {
    override fun getActivities(matchId: Int): Flowable<List<Activity>> = offlineRepo.getActivities(matchId)

    override fun insertActivity(activity: Activity): Completable = offlineRepo.addActivity(activity)

    override fun getPlayers(team: String): Flowable<List<Players>> = offlineRepo.getPlayers(team)

    override fun getMatches() : Flowable<List<Match>> = offlineRepo.getMatches()

    override fun addMatches(matches: List<Match>) = offlineRepo.addMatches(matches)

    override fun deleteMatches(match: Match) = offlineRepo.deleteMatches(match)

    override fun updateMatch(match: Match) = offlineRepo.updateMatch(match)

    override fun getMatch(matchId: Int): Single<Match> = offlineRepo.getMatch(matchId)
}
