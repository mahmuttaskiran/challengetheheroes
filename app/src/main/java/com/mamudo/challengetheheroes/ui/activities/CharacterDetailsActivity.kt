package com.mamudo.challengetheheroes.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.mamudo.challengetheheroes.R
import com.mamudo.challengetheheroes.databinding.ActivityCharacterDetailsBinding


class CharacterDetailsActivity : AppCompatActivity() {
    companion object {
        const val CHARACTER = "character"
    }

    private lateinit var binding: ActivityCharacterDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_character_details)
        binding.character = intent.extras?.getParcelable(CHARACTER)!!
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
