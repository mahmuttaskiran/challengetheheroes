package com.mamudo.challengetheheroes.ui.models

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Suppress("UNCHECKED_CAST")
class ViewModelFactoryWithContext constructor(var context: Context) : ViewModelProvider.Factory {
    @ExperimentalCoroutinesApi
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CharactersViewModel::class.java -> {
                CharactersViewModel(this.context) as T
            }
            ComicsViewModel::class.java -> {
                ComicsViewModel(this.context) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }
}