package com.mamudo.challengetheheroes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import com.mamudo.challengetheheroes.ui.CharactersViewModel
import com.mamudo.challengetheheroes.ui.CharactersViewModelFactory
import com.mamudo.challengetheheroes.ui.LatestNewsScreen
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


