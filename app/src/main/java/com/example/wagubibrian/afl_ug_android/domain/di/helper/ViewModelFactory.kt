package com.example.wagubibrian.afl_ug_android.domain.di.helper

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = viewModels[modelClass]
        return if (provider != null) {
            provider.get() as T
        } else {
            throw IllegalArgumentException("Can't find provider for ViewModel class ${modelClass.simpleName}")
        }
    }
}
