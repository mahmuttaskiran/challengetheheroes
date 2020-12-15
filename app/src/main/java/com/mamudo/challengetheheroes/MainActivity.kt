package com.mamudo.challengetheheroes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.mamudo.challengetheheroes.ui.models.CharactersViewModel
import com.mamudo.challengetheheroes.ui.models.CharactersViewModelFactory
import com.mamudo.challengetheheroes.ui.composables.LatestNewsScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModels {
        CharactersViewModelFactory(this)
    }

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LatestNewsScreen(viewModel)
        }
    }
}


