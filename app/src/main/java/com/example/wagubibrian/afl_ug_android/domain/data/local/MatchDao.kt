package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.*
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface MatchDao {
    @Query("SELECT * FROM match")
    fun getAll(): Flowable<List<Match>>

    @Query("SELECT * FROM match WHERE match_id IN (:matchIds)")
    fun loadAllByIds(matchIds: IntArray): List<Match>

    @Query("SELECT * FROM match WHERE home_team LIKE :first AND " +
            "away_team LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): Match

    @Insert
    fun insertAll(vararg matches: Match)

    @Update
    fun update(match: Match)

    @Delete
    fun delete(match: Match)

    @Query("""SELECT * FROM match WHERE match_id= :match_id""")
    fun getMatch(match_id: Int): Single<Match>

}
