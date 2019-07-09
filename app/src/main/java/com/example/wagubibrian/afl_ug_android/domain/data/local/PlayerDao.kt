package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface PlayerDao {
    @Query("SELECT * FROM Players WHERE team_name= :teamName")
    fun findPlayersByTeam(teamName: String): Flowable<List<Players>>

    @Insert
    fun insertAll(vararg player: Players)
}
