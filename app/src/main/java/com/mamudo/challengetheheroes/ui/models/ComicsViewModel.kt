package com.mamudo.challengetheheroes.ui.models

import android.content.Context
import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.mamudo.challengetheheroes.HeroesApplication
import com.mamudo.challengetheheroes.api.MarvelApi
import com.mamudo.challengetheheroes.api.data.Character
import com.mamudo.challengetheheroes.ui.adapters.ComicsListAdapter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@ExperimentalCoroutinesApi
class ComicsViewModel constructor(context: Context) : ViewModel() {
    private val _state = MutableStateFlow(arrayListOf<Character>())
    val state: StateFlow<List<Character>> get() = _state
    var api: MarvelApi = (context.applicationContext as HeroesApplication).applicationGraph.api()
    val adapter = ComicsListAdapter(this)
    val loading = ObservableInt(View.GONE)
    var characterId: Int? = null

    fun load() {
        if (state.value.isEmpty()) {
            loading.set(View.VISIBLE)
        }
        api.getCharacterComics(characterId!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _state.value.addAll(it.data.results)
                adapter.notifyDataSetChanged()
                if (loading.get() == View.VISIBLE) loading.set(View.GONE)
            }, {
                Log.e("LoadMore", "loadMoreCharacters: ", it)
                _state.value.remove(LOADING_INDICATOR_CHARACTER)
            })
    }
}
