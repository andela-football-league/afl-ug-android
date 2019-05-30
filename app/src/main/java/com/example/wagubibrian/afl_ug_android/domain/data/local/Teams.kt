package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Teams", indices = [Index(value =arrayOf("team_name"), unique = true)])
data class Teams (
    @PrimaryKey
    @ColumnInfo(name = "team_id")
    val teamId: Int,
    @ColumnInfo(name = "team_name")
    val teamName: String?,
    @ColumnInfo(name = "logo_url")
    val logoUrl: String?
)
