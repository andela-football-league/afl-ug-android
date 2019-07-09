package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "match")
data class Match(
    @PrimaryKey
    @ColumnInfo(name = "match_id")
    val matchId: Int,
    @ColumnInfo(name = "home_team")
    val homeTeam: String,
    @ColumnInfo(name = "away_team")
    val awayTeam: String,
    @ColumnInfo(name = "date")
    val date: String?,
    @ColumnInfo(name = "time")
    val time: String?,
    @ColumnInfo(name = "home_score")
    var homeScore: Int = 0,
    @ColumnInfo(name = "away_score")
    var awayScore: Int = 0,
    @ColumnInfo(name = "referee")
    val referee: String?,
    @ColumnInfo(name = "assist_referee")
    val assistReferee: String?,
    // Where -1 is not started, 1 is Done, 0 is in progress
    @ColumnInfo(name = "status")
    var status: Int = -1


)
