package com.mamudo.challengetheheroes.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mamudo.challengetheheroes.R
import com.mamudo.challengetheheroes.ui.models.CharacterDetailsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class CharacterDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
