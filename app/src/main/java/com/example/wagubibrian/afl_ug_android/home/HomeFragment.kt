package com.example.wagubibrian.afl_ug_android.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.example.wagubibrian.afl_ug_android.MyApplication
import com.example.wagubibrian.afl_ug_android.R
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.di.helper.ViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.home_fragment.*
import javax.inject.Inject

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    lateinit var rv: RecyclerView

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    var adapter: HomeAdapter? = null

    private var matches:List<Match> = listOf()

    lateinit var compositeDisposable: CompositeDisposable

    private lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.home_fragment, container, false)

        compositeDisposable = CompositeDisposable()

        MyApplication.component.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        //RECYCLER
        rv = rootView.findViewById(R.id.recycler_view_home)
        rv.layoutManager = LinearLayoutManager(this.activity)

        //Adapter
        adapter = HomeAdapter(this, matches)
        rv.adapter = adapter

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar = view?.findViewById(R.id.progressBar) as ProgressBar

        loadFixtures()
        isMatchListEmpty()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun isMatchListEmpty() {
        textView4.visibility = if (matches.isEmpty()) View.VISIBLE else View.GONE
    }

    private fun loadFixtures() {
        var observable = viewModel
            .getFixtures()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progressBar.visibility = View.VISIBLE
                matches = it
                adapter?.setItems(it)
                adapter?.notifyDataSetChanged()
                isMatchListEmpty()
                progressBar.visibility = View.GONE
            },{
                Toast.makeText(activity?.applicationContext ,  "An Error has occured $it", Toast.LENGTH_LONG).show()
                progressBar.visibility = View.GONE
            })
        compositeDisposable.add(observable)
    }
}
