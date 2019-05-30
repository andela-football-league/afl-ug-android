package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Flowable

@Dao
interface ActivityDao {
    @Query("SELECT * FROM Activity WHERE match_id= :matchId")
    fun findActivityByGame(matchId: Int): Flowable<List<Activity>>

    @Insert
    fun insertActivity(vararg activity: Activity)
}
