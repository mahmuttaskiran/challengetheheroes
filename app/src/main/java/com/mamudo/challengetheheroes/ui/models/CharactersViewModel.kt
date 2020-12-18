package com.mamudo.challengetheheroes.ui.models

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.mamudo.challengetheheroes.HeroesApplication
import com.mamudo.challengetheheroes.api.MarvelApi
import com.mamudo.challengetheheroes.api.data.Character
import com.mamudo.challengetheheroes.api.data.ComicsHolder
import com.mamudo.challengetheheroes.api.data.Thumbnail
import com.mamudo.challengetheheroes.ui.adapters.CharacterListAdapter
import com.mamudo.challengetheheroes.ui.activities.CharacterDetailsActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val LIMIT_PER_REQ = 30

val LOADING_INDICATOR_CHARACTER = Character(
    -1, "", "", Thumbnail("", ""), ComicsHolder(
        0,
        emptyList()
    )
)

private const val TAG = "CharactersViewModel"

@ExperimentalCoroutinesApi
class CharactersViewModel constructor(var context: Context) : ViewModel() {


    private val _state = MutableStateFlow(CharactersState(arrayListOf()))
    val state: StateFlow<CharactersState> get() = _state
    var api: MarvelApi = (context.applicationContext as HeroesApplication).applicationGraph.api()
    val adapter: CharacterListAdapter = CharacterListAdapter(this) {
        onCharacterClick(it)
    }
    val loading = ObservableInt(View.GONE)

    init {
        loadMoreCharacters()
    }

    fun loadMoreCharacters() {
        if (state.value.characters.isEmpty()) {
            loading.set(View.VISIBLE)
        } else {
            // we should ensure LOADING_INDICATOR_CHARACTER appears in our list only once
            _state.value.characters.remove(LOADING_INDICATOR_CHARACTER)
            _state.value.characters.add(LOADING_INDICATOR_CHARACTER)
            adapter.notifyItemInserted(_state.value.characters.size - 1)
        }
        api.getCharacters(_state.value.characters.size, limit = LIMIT_PER_REQ)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                _state.value.characters.remove(LOADING_INDICATOR_CHARACTER)
                _state.value = _state.value.copy(it.data.results)
                adapter.notifyDataSetChanged()
                if (loading.get() == View.VISIBLE) loading.set(View.GONE)
            }, {
                Log.e(TAG, "exception occurs: ", it)
                _state.value.characters.remove(LOADING_INDICATOR_CHARACTER)
            })
    }


    private fun onCharacterClick(item: Character) {
        context.startActivity(Intent(context, CharacterDetailsActivity::class.java).apply {
            putExtra("character", item)
        })
    }
}


class CharactersState(val characters: ArrayList<Character>) {
    var endReached = false
    var exception: Throwable? = null

    fun copy(characters: List<Character>): CharactersState {
        return CharactersState(this.characters.apply { addAll(characters) }).also {
            it.endReached = characters.size < LIMIT_PER_REQ
            it.exception = exception
        }
    }
}