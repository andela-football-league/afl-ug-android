package com.example.wagubibrian.afl_ug_android.activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.domain.data.local.Activity
import kotlinx.android.synthetic.main.fragment_activity.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class ActivityAdapter(
    private var mValues: List<Activity>
) : RecyclerView.Adapter<ActivityAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        if (item.homeStatus) {
            holder.mIdView2.text = item.playerName
            holder.mContentView2.text = item.action
            holder.mIdView.visibility = View.GONE
            holder.mContentView.visibility = View.GONE
            holder.txtView6.text =  "${item.time}'"
            holder.txtView5.visibility = View.GONE
            when {
                item.action == "Goal" -> {
                    holder.imageView.setImageResource(R.mipmap.ball_logo)
                    holder.imageView.visibility = View.VISIBLE
                    holder.mContentView2.visibility  = View.GONE
                }
                item.action == "Yellow Card" -> {
                    holder.imageView.setImageResource(R.mipmap.yellow_card)
                    holder.imageView.visibility = View.VISIBLE
                    holder.mContentView2.visibility  = View.GONE
                }
                item.action == "Red card" -> {
                    holder.imageView.setImageResource(R.mipmap.red_card)
                    holder.imageView.visibility = View.VISIBLE
                    holder.mContentView2.visibility  = View.GONE
                }
            }
        } else {
            holder.mIdView.text = item.playerName
            holder.mContentView.text = item.action
            holder.mIdView2.visibility = View.GONE
            holder.mContentView2.visibility = View.GONE
            holder.txtView5.text =  "${item.time}'"
            holder.txtView6.visibility = View.GONE
            when {
                item.action == "Goal" -> {
                    holder.imageView2.setImageResource(R.mipmap.ball_logo)
                    holder.imageView2.visibility = View.VISIBLE
                    holder.mContentView.visibility  = View.GONE
                }
                item.action == "Yellow Card" -> {
                    holder.imageView2.setImageResource(R.mipmap.yellow_card)
                    holder.imageView2.visibility = View.VISIBLE
                    holder.mContentView.visibility  = View.GONE
                }
                item.action == "Red card" -> {
                    holder.imageView2.setImageResource(R.mipmap.red_card)
                    holder.imageView2.visibility = View.VISIBLE
                    holder.mContentView.visibility  = View.GONE
                }
            }
        }

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdView: TextView = mView.item_number
        val mContentView: TextView = mView.content
        val mIdView2: TextView = mView.item_number2
        val mContentView2: TextView = mView.content2
        val imageView: ImageView = mView.imageView4
        val imageView2: ImageView = mView.imageView3
        val txtView5: TextView = mView.textView5
        val txtView6: TextView = mView.textView6

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

    fun setItems(items: List<Activity>) {
        this.mValues = items
    }
}
