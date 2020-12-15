package com.mamudo.challengetheheroes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.setContent
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharacterDetailsActivity : AppCompatActivity() {
    private val viewModel: CharacterDetailsViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.onBackPressed = {
            finish()
        }
        setContent {
            CharacterDetailsScreen(viewModel = viewModel)
        }
    }
}
