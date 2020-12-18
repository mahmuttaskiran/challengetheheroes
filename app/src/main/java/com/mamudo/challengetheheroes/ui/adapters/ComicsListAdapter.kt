package com.mamudo.challengetheheroes.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamudo.challengetheheroes.api.data.Character
import com.mamudo.challengetheheroes.databinding.ComicItemBinding
import com.mamudo.challengetheheroes.ui.models.ComicsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

class Holder(private val binding: ComicItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun create(inflater: LayoutInflater, parent: ViewGroup): Holder {
            val binding = ComicItemBinding.inflate(inflater, parent, false)
            return Holder(binding)
        }
    }

    fun bind(character: Character) {
        binding.character = character
    }
}

@ExperimentalCoroutinesApi
class ComicsListAdapter(var viewModel: ComicsViewModel) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder.create(inflater, parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is Holder) {
            holder.bind(viewModel.state.value[position])
        }
    }

    override fun getItemCount(): Int =
        viewModel.state.value.size
}