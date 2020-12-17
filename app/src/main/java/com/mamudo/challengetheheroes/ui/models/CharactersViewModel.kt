package com.mamudo.challengetheheroes.ui.models

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mamudo.challengetheheroes.MainApplication
import com.mamudo.challengetheheroes.api.MarvelApi
import com.mamudo.challengetheheroes.api.data.Character
import com.mamudo.challengetheheroes.ui.CharacterDetailsActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private const val LIMIT_PER_REQ = 30

class CharactersViewModel constructor(var context: Context) : ViewModel() {

    companion object {
        var character: Character? = null
    }

    private val _state = MutableStateFlow(CharactersState(arrayListOf()))
    val state: StateFlow<CharactersState> get() = _state
    var api: MarvelApi = (context.applicationContext as MainApplication).applicationGraph.api()

    init {
        loadMoreCharacters()
    }

    fun loadMoreCharacters() {
        api.getCharacters(state.value.characters.size, limit = LIMIT_PER_REQ)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _state.value = _state.value.copy(it.data.results)
            }, {
                _state.value = _state.value.copy(it)
            })
    }

    fun onCharacterClick(item: Character) {
        character = item
        context.startActivity(Intent(context, CharacterDetailsActivity::class.java))
    }
}

class CharactersViewModelFactory constructor(var context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CharactersViewModel::class.java)) {
            CharactersViewModel(this.context) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
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

    fun copy(exception: Throwable): CharactersState {
        return CharactersState(characters).also {
            it.endReached = endReached
            it.exception = exception
        }
    }
}