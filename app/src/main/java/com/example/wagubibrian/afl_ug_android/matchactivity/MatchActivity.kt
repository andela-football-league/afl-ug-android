package com.example.wagubibrian.afl_ug_android.matchactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.players.PlayersFragment
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val playersFragment = PlayersFragment()
        var bundle = Bundle()

        bundle.putString("team_name", intent.getStringExtra("team"))
        bundle.putString("home_away_status", intent.getStringExtra("home_away"))

        var team = intent.getStringExtra("team")
        var status = intent.getStringExtra("home_away")
        lateinit var activity: String
        button_yellow_card.setOnClickListener {
            activity = "Yellow Card"
            loadFragment(playersFragment, bundle, team, status, activity)
        }

        button_red_card.setOnClickListener{
            activity = "Red card"
            loadFragment(playersFragment, bundle, team, status, activity)
        }

        button_goal.setOnClickListener{
            activity = "Goal"
            loadFragment(playersFragment, bundle, team, status, activity)
        }
    }

    private fun loadFragment(playersFragment: PlayersFragment, bundle:Bundle
                             , team: String, gameStatus: String , activity: String) {
        bundle.putString("team_name", team)
        bundle.putString("home_away_status", gameStatus)
        bundle.putString("activity", activity)
        playersFragment.arguments = bundle
        playersFragment.show(supportFragmentManager, "Players")
    }
}
