package com.mamudo.challengetheheroes.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamudo.challengetheheroes.R
import com.mamudo.challengetheheroes.api.data.Character
import com.mamudo.challengetheheroes.databinding.CharacterItemBinding
import com.mamudo.challengetheheroes.ui.models.CharactersViewModel
import com.mamudo.challengetheheroes.ui.models.LOADING_INDICATOR_CHARACTER
import kotlinx.coroutines.ExperimentalCoroutinesApi

class LoadingViewHolder(view: View) :
    RecyclerView.ViewHolder(view) {
    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): LoadingViewHolder {
            val view = inflater.inflate(R.layout.item_loading, parent, false)
            return LoadingViewHolder(view)
        }
    }
}

typealias OnCharacterClick = (c: Character) -> Unit

class CharacterViewHolder(private val binding: CharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): CharacterViewHolder {
            val binding = CharacterItemBinding.inflate(inflater, parent, false)
            return CharacterViewHolder(binding)
        }
    }

    fun bind(character: Character, onCharacterClick: OnCharacterClick) {
        binding.character = character
        binding.cardView.setOnClickListener { binding.character?.apply {
            onCharacterClick(this)
        } }
    }
}

@ExperimentalCoroutinesApi
class CharacterListAdapter(var viewModel: CharactersViewModel, var onCharacterClick: OnCharacterClick) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val CHARACTER = 2020
        const val LOADING = 2021
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        return if (viewType == CHARACTER) {
            CharacterViewHolder.create(inflater, parent)
        } else {
            LoadingViewHolder.create(inflater, parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CharacterViewHolder) {
            holder.bind(viewModel.state.value.characters[position], onCharacterClick)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (viewModel.state.value.characters[position] == LOADING_INDICATOR_CHARACTER) LOADING else CHARACTER
    }

    override fun getItemCount(): Int =
        viewModel.state.value.characters.size
}