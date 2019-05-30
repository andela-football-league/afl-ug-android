package com.example.wagubibrian.afl_ug_android.players

import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.wagubibrian.afl_ug_android.R
import android.view.LayoutInflater
import android.widget.TextView
import com.example.wagubibrian.afl_ug_android.domain.data.local.Players
import kotlinx.android.synthetic.main.player.view.*


class PlayerAdapter(private var playersFragment: PlayersFragment,private var dataSet: List<Players>) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.player, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount() = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTxt.text = dataSet[position].playerName

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var nameTxt: TextView = itemView.nameTxt
        var listener = itemView.setOnClickListener {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setMessage("Please Confirm Player ${it.nameTxt.text}")
                .setPositiveButton("Confirm",
                    DialogInterface.OnClickListener { dialog, id ->
                        playersFragment.addConfirmedActivity(it.nameTxt.text.toString())
                        dialog.dismiss()
                    })
                .setNegativeButton("Cancel",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })
            builder.create()
            builder.show()
        }
    }

    fun setItems(playerList: List<Players>) {
        this.dataSet = playerList
    }

}
