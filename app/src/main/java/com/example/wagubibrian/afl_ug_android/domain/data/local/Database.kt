package com.example.wagubibrian.afl_ug_android.domain.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Match::class, Players::class, Activity::class, Teams::class), version = 1)
abstract class Database : RoomDatabase() {

    abstract fun matchDao(): MatchDao

    abstract fun playerDao(): PlayerDao

    abstract fun activityDao(): ActivityDao

    abstract fun teamDao(): TeamDao

    companion object {
        fun getSeedTeams(): List<Teams> {
            var team = Teams(1,"HOME_3", "")
            var team1 = Teams(2,"AWAY_2", "")
            var team2 = Teams(3,"HOME_1", "")
            var team3 = Teams(4,"AWAY_4", "")
            return listOf(team, team1, team2, team3)
        }


        fun getSeedMatches(): List<Match> {
            var match = Match(1,"HOME_3", "AWAY_2", "10/15/19", "11:00"
                , 0, 0, "TEST", "TEST_2", -1)
            var match1 = Match(2,"HOME_1", "AWAY_2", "`10/15/19", "11:00"
                , 0, 0, "TEST_1", "TEST_3", -1)
            var match2 = Match(3,"HOME_3", "AWAY_4", "`10/15/19", "2:00"
                , 3, 2, "TEST_1", "TEST_3", 1)
            return listOf(match, match1, match2)
        }

        fun getSeedPlayers(): List<Players> {
            var player = Players(1, "Lokalang Steven", "HOME_3")
            var player1 = Players(2, "Hamis Kevin", "AWAY_2")
            var player2 = Players(3, "Luberenga Denis", "HOME_3")
            var player3 = Players(4, "Fernando Okeng ", "AWAY_2")
            var player4 = Players(5, "Kakule Elastus", "HOME_3")
            var player5 = Players(6, "Rasta Waters", "AWAY_2")
            var player6 = Players(7, "Drink Water", "HOME_3")
            var player7 = Players(8, "Lorent UnderWood", "AWAY_4")
            var player8 = Players(9, "Wendy Rhodes", "AWAY_4")
            var player9 = Players(10, "Lokalang Steven", "HOME_1")
            var player10 = Players(11, "Hamis Kevin", "AWAY_2")
            var player11 = Players(12, "Luberenga Denis", "HOME_1")
            var player12 = Players(13, "Fernando Okeng ", "AWAY_2")
            var player13 = Players(14, "Kakule Elastus", "HOME_1")
            var player14 = Players(15, "Rasta Waters", "AWAY_2")
            var player15 = Players(16, "Drink Water", "HOME_1")
            var player16 = Players(17, "Lorent UnderWood", "AWAY_4")
            var player17 = Players(18, "Wendy Rhodes", "AWAY_4")
            return listOf(player, player1, player2, player3, player4, player5, player6, player7, player8,
                player9, player10, player11, player12, player13, player14, player15, player16, player17)
        }
    }

}
