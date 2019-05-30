package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Players", foreignKeys = [ForeignKey(entity = Teams::class,
    parentColumns = arrayOf("team_name"),
    childColumns = arrayOf("team_name"),
    onDelete = ForeignKey.CASCADE)]
)
data class Players (
    @PrimaryKey
    @ColumnInfo(name = "player_id")
    var playerId: Int,
    @ColumnInfo(name = "player_name")
    var playerName: String,
    @ColumnInfo(name = "team_name")
    var teamName: String
)
