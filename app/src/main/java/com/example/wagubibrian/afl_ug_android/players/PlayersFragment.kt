package com.example.wagubibrian.afl_ug_android.players

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.wagubibrian.afl_ug_android.R
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.widget.TextView
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.MyApplication
import com.example.wagubibrian.afl_ug_android.domain.data.local.Players
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PlayersFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PlayersFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PlayersFragment : DialogFragment() {
    var players = listOf<Players>()
    lateinit var rv: RecyclerView
    lateinit var txtView: TextView
    var adapter: PlayerAdapter? = null

    private lateinit var viewModel: PlayerViewModel

    private lateinit var team: String

    private lateinit var activity: String

    private lateinit var homeAwayStatus: String

    private lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.dialog_layout, container)

        MyApplication.component.inject(this)

        compositeDisposable = CompositeDisposable()

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PlayerViewModel::class.java)

        team = arguments?.getString("team_name", "N/A").toString()
        activity = arguments?.getString("activity", "N/A").toString()
        homeAwayStatus = arguments?.getString("home_away_status", "N/A").toString()


        //Dailog Header
        txtView = rootView.findViewById(R.id.activity_text)
        txtView.text = "Award $activity"

        //RECYCLER
        rv = rootView.findViewById(R.id.recycler_view)
        rv.layoutManager = LinearLayoutManager(this.context)


        //ADAPTER
        adapter = PlayerAdapter(this , players)
        rv.adapter = adapter

        this.dialog.setTitle("Players")

        loadPlayers()

        return rootView
    }

    private fun loadPlayers() {
        var observable = viewModel
            .getPlayers(team)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it
                    adapter?.setItems(it)
                    adapter?.notifyDataSetChanged()
                }, {
                    Toast.makeText(this.context,"An error has occurred $it", Toast.LENGTH_LONG).show()
                }
            )
        compositeDisposable.add(observable)
    }

    fun addConfirmedActivity(playerName: String) {
        var homeStatus = homeAwayStatus == "home"
        var obs = viewModel
            .addActivity(playerName,activity, homeStatus)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {}, {
                Toast.makeText(context, "An error occurred $it", Toast.LENGTH_LONG).show()
            })
        if (activity == "Goal") {
            activity = "Assist"
            txtView.text = "Award $activity"
            updateMatchDetails()
        }
        else {
            this.dismiss()
        }
        compositeDisposable.add(obs)
    }

    private fun updateMatchDetails() {
        var observable = viewModel
            .getMatch(viewModel.getMatchId())
            .flatMapCompletable {
                if (homeAwayStatus == "home") it.homeScore += 1 else it.awayScore += 1
                viewModel.updateMatch(it)
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},
                {
                    Toast.makeText(context, "An Error has occurred $it", Toast.LENGTH_LONG).show()
                }
            )
        compositeDisposable.add(observable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
