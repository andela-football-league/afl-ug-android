package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert

@Dao
interface TeamDao {
    @Insert
    fun insertAll(vararg team: Teams)
}
