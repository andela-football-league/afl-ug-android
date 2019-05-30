package com.example.wagubibrian.afl_ug_android.home

import android.arch.lifecycle.ViewModel;
import com.example.wagubibrian.afl_ug_android.domain.data.local.Match
import com.example.wagubibrian.afl_ug_android.domain.data.repository.implementations.Repository
import io.reactivex.Flowable
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    //Get the fixtures for the database.
    fun getFixtures(): Flowable<List<Match>>{
        return repository.getMatches()
    }
}
