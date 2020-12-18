package com.mamudo.challengetheheroes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamudo.challengetheheroes.R
import com.mamudo.challengetheheroes.api.data.Character
import com.mamudo.challengetheheroes.databinding.FragmentComicsBinding
import com.mamudo.challengetheheroes.ui.activities.CharacterDetailsActivity
import com.mamudo.challengetheheroes.ui.models.ComicsViewModel
import com.mamudo.challengetheheroes.ui.models.ViewModelFactoryWithContext

class ComicsFragment: Fragment(R.layout.fragment_comics) {
    private val viewModel: ComicsViewModel by viewModels {
        ViewModelFactoryWithContext(requireContext())
    }

    lateinit var mBinding: FragmentComicsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentComicsBinding.inflate(inflater, container, false)
        mBinding.model = viewModel
        val character: Character = activity?.intent?.extras?.getParcelable(CharacterDetailsActivity.CHARACTER)!!
        viewModel.characterId = character.id
        initRecycler()
        return mBinding.root
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.HORIZONTAL
        }
        mBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
        }
        viewModel.load()
    }
}