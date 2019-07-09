package com.example.wagubibrian.afl_ug_android.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.MyApplication
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.domain.data.local.Activity
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_activity_list.*
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class ActivityFragment : Fragment() {

    companion object {
        fun newInstance() = ActivityFragment()
    }

    lateinit var rv: RecyclerView
    var adapter: ActivityAdapter? = null
    var activities = listOf<Activity>()
    lateinit var hometxtView: TextView
    lateinit var homeScoretxtView: TextView
    lateinit var awaytxtView: TextView
    lateinit var awayScoretxtView: TextView

    private lateinit var viewModel: ActivityViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var compositeDisposable: CompositeDisposable

    private lateinit var progressBarActivity: ProgressBar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_activity_list, container, false)

        MyApplication.component.inject(this)

        progressBarActivity = rootView.findViewById(R.id.progressBarActivity)

        compositeDisposable = CompositeDisposable()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ActivityViewModel::class.java)

        //RECYCLER
        rv = rootView.findViewById(R.id.list_recycler)
        rv.layoutManager = LinearLayoutManager(this.context)


        //ADAPTER
        adapter = ActivityAdapter(activities)
        rv.adapter = adapter

        progressBarActivity.visibility = View.VISIBLE
        loadMatchDetails(rootView)
        loadActivities()

        return  rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun loadMatchDetails(rootView: View) {
        var obs = viewModel
            .getMatch()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                hometxtView = rootView.findViewById(R.id.textView10) as TextView
                hometxtView.text = "${it.homeTeam}"
                homeScoretxtView = rootView.findViewById(R.id.textView11) as TextView
                homeScoretxtView.text = "${it.homeScore}"
                awaytxtView = rootView.findViewById(R.id.textView12) as TextView
                awaytxtView.text = "${it.awayTeam}"
                awayScoretxtView = rootView.findViewById(R.id.textView14) as TextView
                awayScoretxtView.text = "${it.awayScore}"
                progressBarActivity.visibility = View.GONE
            }, {
                Toast.makeText(context, "An error occurred $it", Toast.LENGTH_LONG).show()
                progressBarActivity.visibility = View.GONE
            })
        compositeDisposable.add(obs)
    }

    private fun loadActivities() {
        var obs2 = viewModel
            .getActivities()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.isEmpty()) {
                    no_activity.visibility = View.VISIBLE
                } else {
                    no_activity.visibility = View.GONE
                    adapter?.setItems(it)
                    adapter?.notifyDataSetChanged()
                }
                progressBarActivity.visibility = View.GONE
            }, {
                Toast.makeText(context, "An error occurred $it", Toast.LENGTH_LONG).show()
                progressBarActivity.visibility = View.GONE
            })
        compositeDisposable.add(obs2)
    }

}
