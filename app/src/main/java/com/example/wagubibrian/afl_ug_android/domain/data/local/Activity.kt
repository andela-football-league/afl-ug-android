package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Activity", foreignKeys = [ForeignKey(entity = Match::class,
    parentColumns = arrayOf("match_id"),
    childColumns = arrayOf("match_id"),
    onDelete = ForeignKey.CASCADE)]
)
data class Activity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name= "activity_id")
    var activityId: Int,
    @ColumnInfo(name=  "action")
    var action: String,
    @ColumnInfo(name= "player_name")
    var playerName: String,
    @ColumnInfo(name= "match_id")
    var matchId: Int,
    @ColumnInfo(name= "home_status")
    var homeStatus: Boolean,
    @ColumnInfo(name= "time")
    var time: String
)
