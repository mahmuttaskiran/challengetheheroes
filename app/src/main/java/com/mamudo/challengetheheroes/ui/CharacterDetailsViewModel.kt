package com.mamudo.challengetheheroes.ui

import androidx.lifecycle.ViewModel
import com.mamudo.challengetheheroes.api.data.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterDetailsViewModel : ViewModel() {
    var onBackPressed: () -> Unit = {}

    private val _state = MutableStateFlow(CharactersViewModel.character!!)
    val state: StateFlow<Character> get() = _state
}
