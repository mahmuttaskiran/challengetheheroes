package com.mamudo.challengetheheroes.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mamudo.challengetheheroes.R
import com.mamudo.challengetheheroes.databinding.FragmentCharacterListBinding
import com.mamudo.challengetheheroes.ui.models.CharactersViewModel
import com.mamudo.challengetheheroes.ui.models.ViewModelFactoryWithContext
import com.mamudo.challengetheheroes.utils.EndlessRecyclerOnScrollListener


class CharacterListFragment : Fragment(R.layout.fragment_character_list) {
    private val viewModel: CharactersViewModel by viewModels {
        ViewModelFactoryWithContext(requireContext())
    }

    lateinit var mBinding: FragmentCharacterListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentCharacterListBinding.inflate(inflater, container, false)
        mBinding.model = viewModel
        initRecycler()
        return mBinding.root
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(context).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        mBinding.recyclerView.apply {
            layoutManager = linearLayoutManager
        }
        mBinding.recyclerView.addOnScrollListener(object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMore() {
                mBinding.recyclerView.post {
                    viewModel.loadMoreCharacters()
                }
            }
        })
    }
}