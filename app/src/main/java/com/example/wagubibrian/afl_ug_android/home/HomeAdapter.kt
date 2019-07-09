package com.example.wagubibrian.afl_ug_android.home

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.data.prefs.PreferencesHelper
import com.example.wagubibrian.afl_ug_android.match.MatchFragment
import com.example.wagubibrian.afl_ug_android.match.MatchViewModel
import kotlinx.android.synthetic.main.match_card.view.*


class HomeAdapter(
    var homeFragment: HomeFragment,
    private var myDataset: List<Match>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var context = homeFragment.context

    class MyViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        var awayTeam: TextView = itemView.text_view
        var homeTeam: TextView = itemView.text_vie
        var button : Button = itemView.button
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HomeAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.match_card, parent, false)
        return MyViewHolder(v)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.homeTeam.text = myDataset[position].homeTeam
        holder.awayTeam.text = myDataset[position].awayTeam
        if(myDataset[position].status == 0) {
            holder.button.text = "In Progress"
        } else if (myDataset[position].status == 1) {
            holder.button.text = "See Results"
        }
        holder.button.setOnClickListener{

            var fragment = MatchFragment.newInstance()
            var bundle = Bundle()
            bundle.putString("Team A", holder.homeTeam.text as String)
            bundle.putString("Team B", holder.awayTeam.text as String)
            bundle.putInt("Match id", myDataset[position].matchId)
            bundle.putInt("status", myDataset[position].status)

            var timerStateStopped = context?.let { it1 -> PreferencesHelper.getTimerState(it1) } == MatchViewModel.TimerState.Stopped
            var buttonValueStatus = holder.button.text == "In Progress"
            if (timerStateStopped or buttonValueStatus) {
                fragment.arguments = bundle
                homeFragment.activity!!.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_frame_layout, fragment)
                    .commit()
            }  else {
                Toast.makeText(context, "There is already an ongoing game", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun getItemCount() = myDataset.size

    fun setItems(matches: List<Match>) {
        this.myDataset = matches
    }
}
